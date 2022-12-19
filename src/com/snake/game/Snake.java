package com.snake.game;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Snake extends SnakeField {
	
	private int x[]=new int[AREA];
	private int y[]=new int[AREA];
	private int snakeLength=3;
	private Rectangle[] bodyPart= new Rectangle[AREA];
	private Timeline timeline;
	static char direction='R';
	private Rectangle fruit;
	private int fruitX;
	private int fruitY;
	private Random random;
	boolean isRunning;
	int points=0;
	
	public Snake() {
		isRunning=false;
		this.getStyleClass().add("Snake");
		random=new Random();
		timeline=new Timeline(new KeyFrame(Duration.seconds(0.15), new RenderMovement()));
		timeline.setCycleCount(Animation.INDEFINITE);
		
		this.drawSnake();
		this.generateFruit();
	}

	public void drawSnake() {//1º first step: draw snake and set in constructor only once
		for(int i=0;i<snakeLength;i++) {
			bodyPart[i]=new Rectangle(SIZE,SIZE);
			this.getChildren().add(bodyPart[i]);
		}
	}
	public void setCoordinates() {//2º bring the previous position to the next position, eg:x[1]=x[0]
		for(int i=snakeLength;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
			case 'R':	x[0]+=SIZE; break;
			case 'D': y[0]+=SIZE; break;
			case 'L':	x[0]-=SIZE; break;
			case 'U': y[0]-=SIZE; break;
		}
	}
	
	public void moveSnake() {//3º set X and Y positions to bodyparts
		for(int i=0;i<snakeLength;i++) {
			bodyPart[i].setTranslateX(x[i]);
			bodyPart[i].setTranslateY(y[i]);
		}
	}
	public void generateFruit() {//4º generate fruit only once on constructor, then move it with translateFruit() method
		fruitX=random.nextInt(WIDTH/SIZE)*SIZE;
		fruitY=random.nextInt(HEIGHT/SIZE)*SIZE;
		fruit=new Rectangle(fruitX,fruitY,SIZE,SIZE);
		fruit.setId("fruit");
		this.getChildren().add(fruit);
		
	}
	public void translateFruit() {//5º move and check positions between fruit and parts of the snake to avoid the fruit respawn in snake body
		fruitX=random.nextInt(WIDTH/SIZE)*SIZE;
		fruitY=random.nextInt(HEIGHT/SIZE)*SIZE;
		
		int xPos= CheckPosition.findFruit(this.x,0,snakeLength-1,fruitX);
		int yPos=CheckPosition.findFruit(this.y,0,snakeLength-1,fruitY);
		if(xPos != -1 && yPos != -1) {
			if(x[xPos]==fruitX && y[yPos]==fruitY) {
				System.out.println("fruit respawned in body, changing fruit position");
				fruitX = random.nextInt(WIDTH/SIZE)*SIZE;
				fruitY = random.nextInt(HEIGHT/SIZE)*SIZE;
				fruit.setX(fruitX);
				fruit.setY(fruitY);
			}
		}else {
			fruit.setX(fruitX);
			fruit.setY(fruitY);	
		}
	}
	public void growSnake() {//6º grow the snake if the x[0] position match with fruit_x coordinate
		snakeLength++;
		points+=SIZE;
		for(int i=0;i<snakeLength;i++) {
			if(i==snakeLength-1) {
				bodyPart[i]=new Rectangle(SIZE,SIZE);
				bodyPart[i].setTranslateX(x[i]);
				bodyPart[i].setTranslateY(y[i]);
				getChildren().add(bodyPart[i]);
			}
		}
	}
	public boolean ifSnakeCollides() {//7º check if snake collides
		for(int i=snakeLength;i>0;i--) {// check if the snake head touch itself
			if(x[0]==x[i] && y[0]==y[i])return true;
		}
		if(x[0] < 0) return true;// check if the head touch left border
		if(x[0] >= WIDTH) return true;// check if the head touch right border
		if(y[0] < 0) return true;//check if the head touch upper border
		if(y[0] >= HEIGHT) return true; //check if the head touch the bottom border
		
		return false;
	}
	public void scoringSnake() {//8º
		ScoringSnake.points.setText("Score: "+points);
		if(isRunning()==false) {
			ScoringSnake.gameOver.setText("Game over");
			System.out.println(ScoringSnake.gameOver.getText());
		}
		System.out.println(points);
	}
	class RenderMovement implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event){
			if(isRunning()==true) {
				setCoordinates();
				moveSnake();	
				
				if(x[0]==fruitX && y[0]==fruitY) {
					growSnake();
					translateFruit();
					scoringSnake();
				}
				ifSnakeCollides();
			}
			if(ifSnakeCollides()==true) {
				isRunning=false;
				timeline.stop();
				scoringSnake();
			}
		}
	}
	public Timeline getTimeline(){return timeline;}
	public boolean isRunning(){return isRunning;}
	public void setRunning(boolean isRunning){this.isRunning= isRunning;}
}


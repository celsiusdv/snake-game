package com.snake.game;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScoringSnake extends HBox{
	Button play;
	static Label gameOver;
	static Label points;
	Button replay;
	
	public ScoringSnake() {
		super(20);
		this.setFocused(false);
		this.getStyleClass().add("PointsPane");
		this.setPadding(new Insets(15));

		play=new Button("Play");
		
		play.setFocusTraversable(false);
		
		gameOver=new Label("game status: ");
		gameOver.setAlignment(Pos.BASELINE_CENTER);
		
		points=new Label();
		points.setText("Score:");
		
		points.setAlignment(Pos.BASELINE_CENTER);
		
		replay=new Button("Restart");
	
		replay.setFocusTraversable(false);
		
		this.getChildren().addAll(play,replay,gameOver,points);
	}
	
	public Button getPlay(){return play;}
	public Button getRePlay(){return replay;}

}

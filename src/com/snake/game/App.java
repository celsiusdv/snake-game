package com.snake.game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) {
		
		stage.setMinHeight(600);
		stage.setMinWidth(700);
		MainPane mainPane=new MainPane(stage);
		Scene scene=new Scene(mainPane);
		scene.addEventFilter(KeyEvent.KEY_PRESSED,new HandleSnake() );
		scene.getStylesheets().add(getClass().getResource("/resources/SnakeStyles.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}
class MainPane extends VBox{
	ScoringSnake scorepane;
	Snake snakepane;
	public MainPane(Stage stage) {
		this.setAlignment(Pos.CENTER);
		this.setId("main");
		
		scorepane=new ScoringSnake();
		snakepane=new Snake();
		scorepane.getPlay().setOnAction(e->{
			snakepane.setRunning(true);
			snakepane.getTimeline().play();
			});
		scorepane.getRePlay().setOnAction(e->{
			System.out.println("clicked in reload");
			App main=new App();
			main.start(stage);
		});
		
		VBox.setMargin(snakepane, new Insets(20));
		this.getChildren().addAll(scorepane,snakepane);
	}
}
class HandleSnake implements EventHandler<KeyEvent>{

	public void handle(KeyEvent event){
		KeyCode code=event.getCode();
		switch(code) {
			case RIGHT: Snake.direction='R';break;
			case LEFT:  Snake.direction='L';break;
			case DOWN:  Snake.direction='D';break;
			case UP:    Snake.direction='U';break;
			default:
				break;
		}
	}
}

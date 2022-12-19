package com.snake.game;

import javafx.scene.layout.Pane;

public class SnakeField extends Pane{
	protected final int HEIGHT=600;
	protected final int WIDTH=700;
	final int SIZE=20;
	final int AREA=((WIDTH*HEIGHT)/SIZE)/SIZE;
	public SnakeField() {
		this.getStyleClass().add("SnakeField");
		this.setPrefHeight(HEIGHT);
		this.setPrefWidth(WIDTH);
	}
}

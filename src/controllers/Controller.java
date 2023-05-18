package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.View;

public class Controller {
	private View view;
	
	
	public Controller(View view) {
		this.view = view;
		
	}
	public void start() {
		// người chơi ấn "Space" khi ở giao diện startView
		view.run();
	}
	public void restart() {
		// người chơi ấn "Space" ở giao diện endView
		view.restart();
	}
	public void endGame() {
		// khi pacman bị ghost bắt
		view.endGame();
	}
	public void exit() {
		// người chơi ấn "Esc" khi ở giao diện endView
		view.exit();
	}
}

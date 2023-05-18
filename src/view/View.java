package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class View extends JFrame implements KeyListener {
	MainView mainView;
	StartView startView;
	EndView endView;
	Controller controller;
	String pathFile;
	public View(String pathString) {
		this.pathFile = pathString;
		this.controller = new Controller(this);
		this.mainView = new MainView(pathFile, controller);
		startView = new StartView(controller);
		endView = new EndView(controller);
//		this.setContentPane(s);
		this.setContentPane(startView);
		mainView.requestFocus();
		startView.setFocusable(true);
		repaint();
		this.setTitle("PacMan");
		this.setSize(600,600);
		this.addKeyListener(this);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void run() { // bắt đầu chơi
		this.setContentPane(mainView);
		mainView.requestFocus();
		this.setVisible(true);
		mainView.run();
	}
//	public boolean endGame() {
//		return mainView.endGame();
//	}
	public void endGame() { // pacman bị ghost bắt
		this.setContentPane(endView);
//		startView.setFocusable(false);
		endView.requestFocus();
		this.setVisible(true);
	}
	public void exit() { // thoát game
		System.exit(0);
	}
	public void restart() {// chơi lại
		mainView = new MainView(pathFile, controller);
		this.setContentPane(startView);
		startView.requestFocus();
		this.setVisible(true);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
//			run();
////			repaint();
//		}
		mainView.movePacMan(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

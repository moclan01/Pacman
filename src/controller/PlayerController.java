package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.MainView;

public class PlayerController implements KeyListener {

	private MainView mainView;

	public PlayerController(MainView mainView) {
		this.mainView = mainView;
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	public void movePacman(int direction) {

		int[][] matrix = mainView.getMatrix();
		int rowPac = mainView.getRowPac();
		int colPac = mainView.getColPac();
		JLabel[][] map = mainView.getMap();
		ImageIcon pacmanU = mainView.getPacmanU();
	 	ImageIcon pacmanD = mainView.getPacmanD();
	 	ImageIcon pacmanL = mainView.getPacmanL();
	 	ImageIcon pacmanR = mainView.getPacmanR();

		if (direction == KeyEvent.VK_UP) {
			// direction la nut nguoi dung nhan, UP <=> so dong giam
			if (matrix[rowPac - 1][colPac] != 0) {
				map[rowPac][colPac].setIcon(null);
				rowPac--;
				mainView.setRowPac(rowPac);
				map[rowPac][colPac].setIcon(pacmanU);
			}
		}

		else if (direction == KeyEvent.VK_DOWN) {
			// so dong tang
			if (matrix[rowPac + 1][colPac] != 0) {
				map[rowPac][colPac].setIcon(null);
				rowPac++;
				mainView.setRowPac(rowPac);
				map[rowPac][colPac].setIcon(pacmanD);
			}
		}

		else if (direction == KeyEvent.VK_LEFT) {
			// so cot giam
			if (matrix[rowPac][colPac - 1] != 0) {
				map[rowPac][colPac].setIcon(null);
				colPac--;
				mainView.setColPac(colPac);
				map[rowPac][colPac].setIcon(pacmanL);
			}
		}

		else {
			// so cot tang
			if (matrix[rowPac][colPac + 1] != 0) {
				map[rowPac][colPac].setIcon(null);
				colPac++;
				mainView.setColPac(colPac);
				map[rowPac][colPac].setIcon(pacmanR);
			}
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		int direction = e.getKeyCode();
		if (direction == KeyEvent.VK_LEFT || direction == KeyEvent.VK_RIGHT
				|| direction == KeyEvent.VK_UP || direction == KeyEvent.VK_DOWN) {
					mainView.setDirection(direction);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}

package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.MainView;

public class Pacman implements KeyListener {

	private MainView mainView;
	private int[][] matrix;
	private int rowPac;
	private int colPac;

	public Pacman(MainView mainView) {
		this.mainView = mainView;
	}

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void movePacman(int direction) {

		matrix = mainView.getMatrix();
		rowPac = mainView.getRowPac();
		colPac = mainView.getColPac();
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
		if ((direction == KeyEvent.VK_LEFT && checkLeft()) || (direction == KeyEvent.VK_RIGHT && checkRight())
				|| (direction == KeyEvent.VK_UP && checkUp()) || (direction == KeyEvent.VK_DOWN && checkDown())) {
			mainView.setDirection(direction);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public int getRowPac() {
		return rowPac;
	}

	public int getColPac() {
		return colPac;
	}

	public boolean checkLeft() {
		return matrix[rowPac][colPac - 1] != 0;
	}

	public boolean checkRight() {
		return matrix[rowPac][colPac + 1] != 0;
	}

	public boolean checkUp() {
		return matrix[rowPac - 1][colPac] != 0;
	}

	public boolean checkDown() {
		return matrix[rowPac + 1][colPac] != 0;
	}
}

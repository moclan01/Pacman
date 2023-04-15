package controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.KeyEvent;

import model.map.LoadMap;

public class Player extends JFrame{
    private final ImageIcon pacman = new ImageIcon("resources/image/pacman.png"); // Pacman khi bat dau game
    private final ImageIcon pacmanL = new ImageIcon("resources/image/left.gif"); // Pacman khi re trai
	private final ImageIcon pacmanR = new ImageIcon("resources/image/right.gif"); // Pacman re phai
	private final ImageIcon pacmanU = new ImageIcon("resources/image/up.gif"); // Pacman di len
	private final ImageIcon pacmanD = new ImageIcon("resources/image/down.gif"); // Pacman di xuong
	private int direction = KeyEvent.VK_RIGHT; // lay su kien tu ban phim

    private static LoadMap loadMap;
	private static int[][] matrix; //
	private static JLabel[][] map = new JLabel[100][100];;

    int rowPac = 11; // Dong cua Pacman trong map (vi tri cua pacman theo toa do)
	int colPac = 11;// Cot cua PamMan trong map
	int huongDi = 0; // chua dung

    

    public int getRowPac() {
        return rowPac;
    }

    public void setRowPac(int rowPac) {
        this.rowPac = rowPac;
    }

    public int getColPac() {
        return colPac;
    }

    public void setColPac(int colPac) {
        this.colPac = colPac;
    }

    public int getDirection() {
		return this.direction;
	}

	public void setDirection(int e) {
		this.direction = e;
	}

    public void movePacman(int direction) {
		if (direction == KeyEvent.VK_UP) {
			// direction la nut nguoi dung nhan, UP <=> so dong giam
			if (matrix[rowPac - 1][colPac] != 0) {
				map[rowPac][colPac].setIcon(null);
				rowPac--;
				map[rowPac][colPac].setIcon(pacmanU);
			}
		}

		else if (direction == KeyEvent.VK_DOWN) {
			//so dong tang
			if (matrix[rowPac + 1][colPac] != 0) {
				map[rowPac][colPac].setIcon(null);
				rowPac++;
				map[rowPac][colPac].setIcon(pacmanD);
			}
		}

		else if (direction == KeyEvent.VK_LEFT) {
			//so cot giam
			if (matrix[rowPac][colPac - 1] != 0) {
				map[rowPac][colPac].setIcon(null);
				colPac--;
				map[rowPac][colPac].setIcon(pacmanL);
			}
		}

		else {
			//so cot tang
			if (matrix[rowPac][colPac + 1] != 0) {
				map[rowPac][colPac].setIcon(null);
				colPac++;
				map[rowPac][colPac].setIcon(pacmanR);
			}
		}
	}

	private void changeDirectionPacman(KeyEvent e) {
		this.setDirection(e.getKeyCode());
	}

    public static void main(String[] args) {
        
    }

}

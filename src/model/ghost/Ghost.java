package model.ghost;

import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;

import model.map.LoadMap;

public class Ghost extends JLabel{
    private final ImageIcon ghost = new ImageIcon("ghost.gif");

    private static int[][] matrix;
	private static JLabel[][] map = new JLabel[19][19];;
	private static LoadMap g;
	private int cow = matrix.length;
	private Color color;
	private int width;
	private int height;
	int x = 11;
	int y = 11;
	int xx = 7;
	int yy = 7;
    
    public void moveGhost() {
		map[xx][yy].setIcon(null);
		// if()
		if (matrix[xx][yy - 1] != 0) {
			yy--;
		} else {
			if (matrix[xx + 1][yy] != 0 && matrix[xx - 1][yy] == 0) {
				xx++;
			} else {
				if (matrix[xx - 1][yy] != 0) {
					xx--;
				}
			}
		}
		map[xx][yy].setIcon(ghost);
    }
}

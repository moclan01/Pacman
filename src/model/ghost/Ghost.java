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
	int rowGhost = 7;// Dong cua Ghost trong map
	int colGhost = 7;// Cot cua Ghost trong map
    
    //moveGhost chua lam
	public void moveGhost() {
		map[rowGhost][colGhost].setIcon(null);
		if (matrix[rowGhost][colGhost - 1] != 0) {
			colGhost--;
		} else {
			if (matrix[rowGhost + 1][colGhost] != 0 && matrix[rowGhost - 1][colGhost] == 0) {
				rowGhost++;
			} else {
				if (matrix[rowGhost - 1][colGhost] != 0) {
					rowGhost--;
				}
			}
		}
		map[rowGhost][colGhost].setIcon(ghost);
	}
}

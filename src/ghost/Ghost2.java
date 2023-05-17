package ghost;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import map.ShortestPath;
import view.MainView;

public class Ghost2 {
	private MainView mainView;
	private ShortestPath path;
	private int colPac;
	private int rowPac;
	private int colGhost;
	private int rowghost;
	private int indexPac = 0;
	private int indexGhost = 0;
	private int[] colIndex;
	private int[] rowIndex;
	private JLabel[][] map;
	private ImageIcon imgGhost;
	private int[][] matrix;
	private boolean endGame = false;

	public Ghost2(MainView mainView, ShortestPath shortestPath) {
		this.mainView = mainView;
		this.path = shortestPath;
		map = mainView.getMap();
		imgGhost = mainView.getRedGhost();
		colPac = mainView.getColPac();
		rowPac = mainView.getRowPac();
		colGhost = mainView.getColGhost2();
		rowghost = mainView.getRowGhost2();
		matrix = mainView.getMatrix();
		colIndex = new int[32];
		rowIndex = new int[32];
		setListIndex();
	}

	public void setListIndex() {
		colIndex[0] = 1;
		rowIndex[0] = 1;
		for (int i = 0; i < mainView.getMatrix().length; i++) {
			for (int j = 0; j < mainView.getMatrix().length; j++) {
				if (mainView.getMatrix()[i][j] > 1) {
					rowIndex[mainView.getMatrix()[i][j] - 1] = i;
					colIndex[mainView.getMatrix()[i][j] - 1] = j;
				}
			}
		}
	}

	public void setGhostIndex() {
		for (int i = 0; i < colIndex.length; i++) {
			if (rowghost == rowIndex[i] && colGhost == colIndex[i]) {
				indexGhost = i;
			}
		}
	}

	public void setPacIndex() {
		rowPac = mainView.getRowPac();
		colPac = mainView.getColPac();
		for (int i = 0; i < colIndex.length; i++) {
			if (rowPac == rowIndex[i] && colPac == colIndex[i]) {
				indexPac = i;
			}
		}
	}

	public int[] getColIndex() {
		return colIndex;
	}

	public int[] getRowIndex() {
		return rowIndex;
	}

	public ShortestPath getPath() {
		return path;
	}

	public int getIndexPac() {
		return indexPac;
	}

	public int getIndexGhost() {
		return indexGhost;
	}

	public List<Integer> shortestPath() {
		List<Integer> result = new ArrayList<>();

		result = path.duongDiNganNhat2(indexGhost, indexPac);
		return result;
	}

	public void moveGhost() {
		setPacIndex();
		setGhostIndex();

//		System.out.println(shortestPath());
//		System.out.println("Pac: " + indexPac + " ghost: " + indexGhost);
//		System.out.println("Pac; " + rowPac + " " + colPac);
//		System.out.println("Ghost" + rowghost + " " + colGhost);

		if (rowghost == rowPac && colGhost == colPac) {
			mainView.endGame();
		} else {
			map[rowghost][colGhost].setIcon(null);
			if (indexGhost != indexPac) {
				int x = colIndex[shortestPath().get(1)];
				int y = rowIndex[shortestPath().get(1)];
//				System.out.println(y + " " + x);
				if (colGhost < x && checkRight()) {
					colGhost++;
				}
				if (colGhost > x && checkLeft()) {
					colGhost--;
				}

				if (rowghost < y && checkDown()) {
					rowghost++;
				}
				if (rowghost > y && checkUp()) {
					rowghost--;
				}

			} else {
				if (rowghost == rowPac && colGhost == colPac) {
//				JOptionPane.showMessageDialog(mainView, "Game Over");
//					mainView.endGame();
				} else {
					if (colGhost < colPac && checkRight()) {
						colGhost++;
					}
					if (colGhost > colPac && checkLeft()) {
						colGhost--;
					}
					if (rowghost < rowPac && checkDown()) {
						rowghost++;
					}
					if (rowghost > rowPac && checkUp()) {
						rowghost--;
					}
				}
			}
		}
//		mainView.setRowGhost(rowghost);
//		mainView.setColGhost(colGhost);
		map[rowghost][colGhost].setIcon(imgGhost);
	}

	public MainView getMainView() {
		return mainView;
	}

	public int getColPac() {
		return colPac;
	}

	public int getRowPac() {
		return rowPac;
	}

	public int getColGhost() {
		return colGhost;
	}

	public int getRowghost() {
		return rowghost;
	}

	public boolean checkLeft() {
		return matrix[rowghost][colGhost - 1] != 0;
	}

	public boolean checkRight() {
		return matrix[rowghost][colGhost + 1] != 0;
	}

	public boolean checkUp() {
		return matrix[rowghost - 1][colGhost] != 0;
	}

	public boolean checkDown() {
		return matrix[rowghost + 1][colGhost] != 0;
	}

	public boolean endGame() {
		return endGame;
	}
}

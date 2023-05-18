package ghost;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import map.ShortestPath;
import view.MainView;

public class GhostManagement {
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
    private Random rd;

	public GhostManagement(MainView mainView, ShortestPath shortestPath, int rowGhost, int colGhost) {
		this.mainView = mainView;
		this.path = shortestPath;
		map = mainView.getMap();
		imgGhost = mainView.getOrangeGhost();
		colPac = mainView.getColPac();
		rowPac = mainView.getRowPac();
		matrix = mainView.getMatrix();
		colIndex = new int[32];
		rowIndex = new int[32];
		setListIndex();
	}

	// set vị trí row, col của các đỉnh đồ thị trong map
	public void setListIndex() { 
		colIndex[0] = 1;
		rowIndex[0] = 1;
		// Cơ chế set: trong file chứa ma trận map, những vị trí đỉnh được thay
		// bởi giá trị của đỉnh đó - ví dụ: đỉnh 2 ở vị trí 5 5 thì matrix[5][5] = 2
		// sau đó từ file lấy ra các giá trị row, col để gán là mảng
		for (int i = 0; i < mainView.getMatrix().length; i++) {
			for (int j = 0; j < mainView.getMatrix().length; j++) {
				if (mainView.getMatrix()[i][j] > 1) {
					rowIndex[mainView.getMatrix()[i][j] - 1] = i;
					colIndex[mainView.getMatrix()[i][j] - 1] = j;
				}
			}
		}
	}

	// set vị trí (đỉnh) đồ thị của ghost trong map
	public void setGhostIndex() { 
		// Cơ chế set: Nếu row và col của ghost đều = với row và col của 1 đỉnh thì
		// vị trí của ghost = đỉnh có row, col trùng vs row col của ghost
		for (int i = 0; i < colIndex.length; i++) {
			if (rowghost == rowIndex[i] && colGhost == colIndex[i]) {
				indexGhost = i;
			}
		}
	}

	// set vi trí ( đỉnh) đồ thị của PacMan trong map
	public void setPacIndex() { 
		// Cơ chế set: trước tiên lấy row và col từ mainview -> do row và col của pacman
		// bị người chơi thay đổi, nên phải cập nhật
		// sau đó làm tương tự như ghost
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

	// đường đi ngắn nhất từ ghost đến pacman
	public List<Integer> shortestPath() {
        rd = new Random();
        int shortestPathIndex = rd.nextInt(1);

		List<Integer> result = new ArrayList<>();

        if(shortestPathIndex == 0){
            result = path.duongDiNganNhat(indexGhost, indexPac);
        }
        else{
            result = path.duongDiNganNhat2(indexGhost, indexPac);
        }
		return result;
	}

	// phương thức di chuyển ghost
	public void moveGhost() {
		setPacIndex(); // cập nhật vị trí pacman
		setGhostIndex();// cập nhật vị trí ghost
		System.out.println("PacMan " + " PacIndex: " + indexPac + " row: " + rowPac + " col: " + colPac);
		System.out.println("Ghost " + " GhostIndex: " + indexGhost + " row: " + rowghost + " col: " + colGhost);
		if (rowghost == rowPac && colGhost == colPac) {
			// nếu ghost bắt được pacman thì kết thúc game
			mainView.endGame();
		} else {
			map[rowghost][colGhost].setIcon(null); // xóa icon ghost ở vị trí củ
			if (indexGhost != indexPac) { // pacman và ghost ở 2 đỉnh khác nhau
				// => phải tìm đường đi

				System.out.println("Đường đi: " + shortestPath());

				int x = colIndex[shortestPath().get(1)];
				int y = rowIndex[shortestPath().get(1)];
				// shortestPath().get(1) là đỉnh tiếp theo mà ghost phải đến trên đường đuổi
				// theo pacman
				System.out.println(y + " " + x);
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

			} // pacmac và ghost ở cùng 1 đỉnh => tức là ghost hoặc pacman
			 else { 
				// chưa đi đến đỉnh khác nên index chưa được set lại
				if (rowghost == rowPac && colGhost == colPac) {
					mainView.endGame();
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
		// mainView.setRowGhost(rowghost);
		// mainView.setColGhost(colGhost);
		map[rowghost][colGhost].setIcon(imgGhost); // set icon cho vị trí mới
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

	// tất cả check đều để kiểm tra xem vị trí tiếp theo có phải tường hay không
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

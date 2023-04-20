package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.PlayerController;
import model.map.LoadMap;

public class MainView extends JFrame {
	private final ImageIcon pacman = new ImageIcon("resources/image/pacman.png");
	private final ImageIcon pacmanU = new ImageIcon("resources/image/up.gif");
	private final ImageIcon pacmanD = new ImageIcon("resources/image/down.gif");
	private final ImageIcon pacmanL = new ImageIcon("resources/image/left.gif");
	private final ImageIcon pacmanR = new ImageIcon("resources/image/right.gif");
	
	private int direction = KeyEvent.VK_RIGHT; // lay su kien tu ban phim
	private int[][] matrix; //
	private JLabel[][] map = new JLabel[33][33];

	
	private int cow;
	private PlayerController playerController;

	int rowPac = 17; // Dong cua Pacman trong map ban dau
	int colPac = 17;// Cot cua PamMan trong map ban dau
	int rowGhost = 5;// Dong cua Ghost trong map
	int colGhost = 5;// Cot cua Ghost trong map
	int huongDi = 0; // chua dung
	// 1 -> left, 2 -> down, 3 -> right, 4-> up
	ImageIcon orangeGhost = new ImageIcon("resources/image/orangeGhost.gif");

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int e) {
		this.direction = e;
	}

	public MainView(String pathFile) {

		LoadMap loadMap = new LoadMap();
		loadMap.loadMatrix(pathFile);
		this.matrix = loadMap.getMatrix();

		this.playerController = new PlayerController(this);
		addKeyListener(playerController);
		this.cow = matrix.length;
		
		setTitle("PacMan");
		setResizable(false);
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(cow, cow)); // map la ma tran ke cua do thi nen luon luon la ma tran vuong
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				map[i][j] = new JLabel();
				map[i][j].setOpaque(true);
				if (matrix[i][j] != 0) {
					map[i][j].setBackground(Color.gray);
				} else {
					map[i][j].setBackground(Color.BLACK);
				}
				add(map[i][j]);
			}
		}

		// KeyListener ac = new Controller(this);
		map[rowPac][colPac].setIcon(pacman);
		// map[x][y].setIcon(null);
		// addKeyListener(ac);

		map[rowGhost][colGhost].setIcon(orangeGhost);

		// Cai thoi gian cap nhat giao dien
		// -> 200 milisecond cap nhat 1 lan = 200 milisecond pacman + ghost di chuyen 1
		// lan
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {
				//gọi hàm điều hướng
				playerController.movePacman(getDirection());
			}
		};
		timer.scheduleAtFixedRate(task, 0, 200);

		setVisible(true);
	}



	public void printMatrix() {
		for(int[] row : matrix) {
			for(int num : row)
				System.out.print(num + " ");
			System.out.println();
		}
	}

	public ImageIcon getPacman() {
		return pacman;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public  JLabel[][] getMap() {
		return map;
	}

	public void setMap(JLabel[][] map) {
		this.map = map;
	}

	public int getCow() {
		return cow;
	}

	public void setCow(int cow) {
		this.cow = cow;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController player) {
		this.playerController = player;
	}

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

	public int getRowGhost() {
		return rowGhost;
	}

	public void setRowGhost(int rowGhost) {
		this.rowGhost = rowGhost;
	}

	public int getColGhost() {
		return colGhost;
	}

	public void setColGhost(int colGhost) {
		this.colGhost = colGhost;
	}

	public int getHuongDi() {
		return huongDi;
	}

	public void setHuongDi(int huongDi) {
		this.huongDi = huongDi;
	}

	public ImageIcon getOrangeGhost() {
		return orangeGhost;
	}

	public void setOrangeGhost(ImageIcon orangeGhost) {
		this.orangeGhost = orangeGhost;
	}

	public ImageIcon getPacmanU() {
		return pacmanU;
	}

	public ImageIcon getPacmanD() {
		return pacmanD;
	}

	public ImageIcon getPacmanL() {
		return pacmanL;
	}

	public ImageIcon getPacmanR() {
		return pacmanR;
	}
}

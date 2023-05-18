package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ghost.Ghost;
import ghost.Ghost2;
import ghost.GhostManagement;
import map.LoadMap;
import map.ShortestPath;
import pacman.Foods;
import pacman.PlayerController;

public class MainView extends JPanel {
	private final ImageIcon pacman = new ImageIcon("resources/image/pacman.png");
	private final ImageIcon pacmanU = new ImageIcon("resources/image/up.gif");
	private final ImageIcon pacmanD = new ImageIcon("resources/image/down.gif");
	private final ImageIcon pacmanL = new ImageIcon("resources/image/left.gif");
	private final ImageIcon pacmanR = new ImageIcon("resources/image/right.gif");

	private int direction = KeyEvent.VK_RIGHT; // lay su kien tu ban phim
	private int[][] matrix; //
	private JLabel[][] map;
	private List<Foods> foods;
	private List<GhostManagement> ghosts;

	private int cow;
	private PlayerController playerController;
	private Controller controller;
	int rowPac = 17; // Dong cua Pacman trong map ban dau
	int colPac = 17;// Cot cua PamMan trong map ban dau
	int rowGhost = 5;// Dong cua Ghost trong map
	int colGhost = 5;// Cot cua Ghost trong map
	int huongDi = 0; // chua dung
	int rowGhost2 = 5;// Dong cua Ghost trong map
	int colGhost2 = 9;// Cot cua Ghost trong map
	private Ghost ghost;
	private Ghost2 ghost2;
	private ImageIcon orangeGhost = new ImageIcon("resources/image/orangeGhost.gif");
	private ImageIcon redGhost = new ImageIcon("resources/image/redGhost.gif");
	private boolean endGame = false;

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int e) {
		this.direction = e;
	}

	public MainView(String pathFile, Controller controller) {
		foods = new ArrayList<>();
		ghosts = new ArrayList<>();
		this.controller = controller;

		LoadMap loadMap = new LoadMap();
		loadMap.loadMatrix(pathFile);

		this.matrix = loadMap.getMatrix();
		this.map = new JLabel[matrix.length][matrix.length];
		this.ghost = new Ghost(this, new ShortestPath("resources/map/LTDT_Map_23x23.txt"));
		this.ghost2 = new Ghost2(this, new ShortestPath("resources/map/LTDT_Map_23x23.txt"));
		this.playerController = new PlayerController(this);
		addKeyListener(playerController);
		this.cow = matrix.length;

		setLayout(new GridLayout(cow, cow)); // map la ma tran ke cua do thi nen luon luon la ma tran vuong
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				map[i][j] = new JLabel();
				map[i][j].setOpaque(true);
				if (matrix[i][j] != 0) {
					map[i][j].setBackground(Color.gray);
					foods.add(new Foods(i, j));
				} else {
					map[i][j].setBackground(Color.BLACK);
				}
				add(map[i][j]);
			}
		}

		map[rowPac][colPac].setIcon(pacman);
		map[rowGhost][colGhost].setIcon(orangeGhost);
		map[rowGhost2][colGhost2].setIcon(redGhost);

		setVisible(true);
	}

	public void movePacMan(KeyEvent e) {
		playerController.keyPressed(e);

	}

	public void render(Graphics g){
		for (int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length; j++){
				if(matrix[i][j] != 0){
					foods.get(i).render(g);
				}
			}
		}
	}

	public void run() {
		
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {
				// gọi hàm điều hướng
				if (!endGame) {
					playerController.movePacman(getDirection());
					ghost.moveGhost();
					ghost2.moveGhost();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 200);

	}


	public void reset() {

	}

	public void endGame() {
		endGame = true;
		controller.endGame();
	}

	public void printMatrix() {
		for (int[] row : matrix) {
			for (int num : row)
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

	public JLabel[][] getMap() {
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

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

	public int getRowGhost2() {
		return rowGhost2;
	}

	public int getColGhost2() {
		return colGhost2;
	}

	public ImageIcon getRedGhost() {
		return redGhost;
	}

}

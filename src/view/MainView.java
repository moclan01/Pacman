package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Location.Location;
import control.Controller;
import ghost.Ghost;
import ghost.GhostManagement;
import map.LoadMap;
import map.ShortestPath;
import pacman.Pacman;

public class MainView extends JPanel {
	private final ImageIcon pacman = new ImageIcon("resources/image/pacman.png");
	private final ImageIcon pacmanU = new ImageIcon("resources/image/up.gif");
	private final ImageIcon pacmanD = new ImageIcon("resources/image/down.gif");
	private final ImageIcon pacmanL = new ImageIcon("resources/image/left.gif");
	private final ImageIcon pacmanR = new ImageIcon("resources/image/right.gif");

	private int direction = KeyEvent.VK_RIGHT; // lay su kien tu ban phim
	private int[][] matrix; //
	private JLabel[][] map;

	private int cow;
	private Pacman playerController;
	private Controller controller;
	int rowPac = 17; // Dong cua Pacman trong map ban dau
	int colPac = 17;// Cot cua PamMan trong map ban dau
	int huongDi = 0; // chua dung
	private GhostManagement ghostManagement = new GhostManagement();
	private ImageIcon orangeGhostIcon = new ImageIcon("resources/image/orangeGhost.gif");
	private ImageIcon redGhostIcon = new ImageIcon("resources/image/redGhost.gif");
	private boolean endGame = false;

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int e) {
		this.direction = e;
	}

	public MainView(String pathFile, Controller controller) {
		this.controller = controller;

		LoadMap loadMap = new LoadMap();
		loadMap.loadMatrix(pathFile);

		this.matrix = loadMap.getMatrix();
		this.map = new JLabel[matrix.length][matrix.length];
		Ghost redGhost = new Ghost(this, new ShortestPath("./resources/map/LTDT_Map_23x23.txt"), new Location(5, 5));
		Ghost orangeGhost = new Ghost(this, new ShortestPath("./resources/map/LTDT_Map_23x23.txt"), new Location(5, 9));

		redGhost.setImageIcon(redGhostIcon);
		orangeGhost.setImageIcon(orangeGhostIcon);
		

		this.ghostManagement.addGhost(redGhost);
		this.ghostManagement.addGhost(orangeGhost);

		this.playerController = new Pacman(this);
		addKeyListener(playerController);
		this.cow = matrix.length;

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

		map[rowPac][colPac].setIcon(pacman);
		// map[rowGhost][colGhost].setIcon(orangeGhost);
		// map[rowGhost2][colGhost2].setIcon(redGhost);

		for(Ghost ghost : ghostManagement.getGhosts()) {
			int row = ghost.getLocation().getX();
			int col = ghost.getLocation().getY();
			map[row][col].setIcon(ghost.getIcon());
		}
		setVisible(true);
	}

	public void movePacMan(KeyEvent e) {
		playerController.keyPressed(e);

	}
	

	public void run() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				if (!endGame) {
					playerController.movePacman(getDirection());
					ghostManagement.moveAllGhost();
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

	public Pacman getPlayerController() {
		return playerController;
	}

	public void setPlayerController(Pacman player) {
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

	public int getHuongDi() {
		return huongDi;
	}

	public void setHuongDi(int huongDi) {
		this.huongDi = huongDi;
	}

	public ImageIcon getOrangeGhostIcon() {
		return orangeGhostIcon;
	}

	public void setOrangeGhostIcon(ImageIcon orangeGhost) {
		this.orangeGhostIcon = orangeGhost;
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
	public ImageIcon getRedGhostIcon() {
		return redGhostIcon;
	}

}

package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.View;

import controller.Player;
import model.ghost.Ghost;
import model.map.LoadMap;

public class CreateMap extends JFrame {
	private final ImageIcon pacman = new ImageIcon("resources/image/pacman.png"); // Pacman khi bat dau game
	private final ImageIcon pacmanL = new ImageIcon("resources/image/left.gif"); // Pacman khi re trai
	private final ImageIcon pacmanR = new ImageIcon("resources/image/right.gif"); // Pacman re phai
	private final ImageIcon pacmanU = new ImageIcon("resources/image/up.gif"); // Pacman di len
	private final ImageIcon pacmanD = new ImageIcon("resources/image/down.gif"); // Pacman di xuong
	private int direction = KeyEvent.VK_RIGHT; // lay su kien tu ban phim

	private static LoadMap g;
	private static int[][] matrix; //
	private static JLabel[][] map = new JLabel[33][33];;

	private int cow = matrix.length;
	private Color color;
	private Player player;
	private Ghost ghost;

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

	public CreateMap() {
		this.player = new Player(this);
		setTitle("PacMan");
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
				// moveGhost();
				player.movePacman(getDirection());
			}
		};
		timer.scheduleAtFixedRate(task, 0, 200);

		setVisible(true);
	}

	public void changeDirectionPacman(KeyEvent e) {
		this.setDirection(e.getKeyCode());
	}

	public static void main(String[] args) {
		g = new LoadMap();
		g.loadMatrix("./resources/map/Map_23x23.txt");
		matrix = g.getMatrix();
		CreateMap v = new CreateMap();
		// g.printMatrix();

		// v.printMAtrix();

	}
}

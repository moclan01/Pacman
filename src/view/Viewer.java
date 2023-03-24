package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Viewer extends JFrame{
	private static int[][] matrix;
	private static Graphh g;
	private int cow = matrix.length;
	private Color color ;
	private int width;
	private int height;
	public Viewer() {
		setTitle("PacMan");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(cow, cow));
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix.length; j++) {
				if(matrix[i][j] !=0) {
					add(new Panel2());
				}else {
					add(new Panel1());
				}
				
			}
		}
		System.out.println(MAXIMIZED_HORIZ);
		width = getWidth()/cow;
		height = getHeight()/cow;
		setVisible(true);
	}
	class Panel1 extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			
		}
	}
	class Panel2 extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.setColor(Color.gray);
			g.fillRect(0, 0, width, height);
			
		}
	}
	public void printMAtrix() {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix.length; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		g = new Graphh();
		g.loadGraph("./resources/map/Map_1.txt"); 
		matrix = g.getMatrix();
		Viewer v = new Viewer();
		
		v.printMAtrix();
				
	}
	
	
}

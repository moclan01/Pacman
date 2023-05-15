package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EndView extends JPanel implements KeyListener {
	JButton jbt_choiLai;
	JButton jbt_thoat;
	Controller controller;
	public EndView(Controller controller) {
		this.controller = controller;
		this.addKeyListener(this);
	jbt_choiLai = new JButton("Chơi Lại");
	jbt_thoat = new JButton("Thoát");
	
//	jbt_choiLai.setBounds(300, 300, 200, 200);
	jbt_choiLai.setLocation(300, 300);
//	this.setLayout(new BorderLayout());
//	this.add(jbt_choiLai, BorderLayout.CENTER);
//	this.add(jbt_choiLai);
		
	}
	
	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Image image = Toolkit.getDefaultToolkit().getImage("GameOver.png");
		g.drawImage(image,0,0, getWidth(), getHeight(), null);
		String s = "Ấn Space để chơi lại, Esc để thoát!";
		Font font = new Font("Arial", Font.BOLD	, 25);
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString(s, (int)(getWidth()/6), (int) (getHeight()/1.2));
		repaint();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			controller.exit();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			controller.restart();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
	EndView st = new EndView(null);
	
	JFrame jf = new JFrame();
	jf.setSize(300,300);
	jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
	jf.setLocationRelativeTo(null);
	st.setVisible(true);
	jf.setContentPane(st);
	st.setFocusable(true);
	jf.setVisible(true);
}
}

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import control.Controller;

public class StartView extends JPanel implements KeyListener {
	Controller controller;

	public StartView(Controller controller) {
		this.controller = controller;
		this.addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Image image = Toolkit.getDefaultToolkit().getImage("StartView.png");
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		String s = "Ấn Space để bắt đầu!";
		Font font = new Font("Arial", Font.BOLD, 27);
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString(s, (int) (getWidth() / 4), (int) (getHeight() / 1.2));
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			controller.start();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

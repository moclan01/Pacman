package view;

import javax.swing.JFrame;

import model.Model;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Pacman extends JFrame {

	public Pacman() {
		add(new Model());
	}

	public static void main(String[] args) {
		Pacman pac = new Pacman();
		pac.setVisible(true);
		pac.setTitle("Pacman");
		pac.setSize(380, 420);
		pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pac.setLocationRelativeTo(null);
		
		JMenuBar menubar;
		JMenu game;
		JMenu about;
		JMenuItem new_game;
		JMenuItem setting;
		JMenuItem exit;
		JMenuItem about_us;
		
		menubar = new JMenuBar();
		
		game = new JMenu("Game");
		about = new JMenu("About");
		
		new_game = new JMenuItem("New game");
		setting = new JMenuItem("Setting");
		exit = new JMenuItem("Exit");
		game = new JMenu("Game");
		about_us = new JMenuItem("About us");
		
		pac.setJMenuBar(menubar);
		
		menubar.add(game);
		menubar.add(about);
		
		game.add(new_game);
		game.add(setting);
		game.add(exit);
		about.add(about_us);
		
		pac.setVisible(true);

	}

}

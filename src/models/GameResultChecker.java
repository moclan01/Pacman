package models;

import models.ghost.Ghost;
import models.pacman.Pacman;
import view.MainView;

public class GameResultChecker {
    private Pacman pacman;
    private Ghost ghost;
    private MainView mainView;

    public void end(){
        int rowGhost = ghost.getLocation().getX();
		int colGhost = ghost.getLocation().getY();

        int rowPac = pacman.getLocation().getX();
        int colPac = pacman.getLocation().getY();

        if(colPac == colGhost && rowPac == rowGhost) {
            mainView.endGame();
        }
    }
}

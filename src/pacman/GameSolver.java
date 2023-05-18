package pacman;

import ghost.Ghost;
import view.MainView;

public class GameSolver {
    private Pacman pacman;
    private Ghost ghost;
    private MainView mainView;

    public void end(){
        if(pacman.getColPac() == ghost.getColGhost()
            && pacman.getRowPac() == ghost.getRowGhost()){
                mainView.endGame();
            }
    }
}

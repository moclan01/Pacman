package models.ghost;

import java.util.ArrayList;
import java.util.List;

public class GhostManagement {
    private List<Ghost> ghosts = new ArrayList<Ghost>();

    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public void moveAllGhost() {
        for(Ghost ghost : ghosts) {
            ghost.move();
        }
    }
}

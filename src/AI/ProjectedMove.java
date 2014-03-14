package AI;

import Core.Move;

/**
 * Created by Stephen Yingling on 3/13/14.
 */
public class ProjectedMove extends Move {
    protected int score;
    public ProjectedMove(Move m){
        this.activePiece = m.getActivePiece().copy();
        this.x1 = m.getX1();
        this.y1 = m.getY1();
        this.upgradeType = m.getUpgradeType();
        score =0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

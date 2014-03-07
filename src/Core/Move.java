package Core;

import Pieces.Piece;

/**
 * Created by Stephen Yingling on 3/5/14.
 */
public class Move {

    protected Piece activePiece;
    protected int x1;
    protected int y1;

    public Move(){}

    public Move(Piece piece,  int x1, int y1){
        this.activePiece = piece;

        this.x1 = x1;
        this.y1 = y1;
    }

    public Piece getActivePiece() {
        return activePiece;
    }

    public void setActivePiece(Piece activePiece) {
        this.activePiece = activePiece;
    }

    public int getX0(){
        return activePiece.getX();
    }

    public int getY0(){
        return activePiece.getY();
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    @Override
    public String toString(){
        return "(" + getX0() + ", " + getY0() + ") -> (" + getX1() + ", " + getY1() + ")";
    }
}

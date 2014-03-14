package Pieces;

import Core.Move;
import Core.MoveSet;
import Core.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Stephen Yingling on 3/5/14.
 */
public abstract class Piece {
    protected int x;
    protected int y;
    protected int max_x;
    protected int max_y;
    protected Player player;

    public abstract ArrayList<Move> generateMoves();

    public abstract MoveSet generateMoves(Piece[][] board);

    public int getX() {
        return x;
    }

    public void setMaxX(int x){
        max_x = x;
    }

    public void setMax_y(int y){
        max_y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    protected boolean withinBoard(int x, int y){
        return (x >= 0 && x < max_x && y >= 0 && y < max_y);
    }

    public boolean promotes(){
        return false;
    }

    public abstract Piece copy();

    @Override
    public boolean equals(Object j){
        if(j instanceof Piece){
            Piece p = (Piece) j;
            if(p.getPlayer() == player && p.getX() == x && p.getY() == y){
                return true;
            }

        }

        return false;
    }
}

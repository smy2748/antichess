package Pieces;

import Core.Move;
import Core.MoveSet;
import Core.Player;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Pawn extends Piece {

    public Pawn(int mx, int my, Player p){
        max_x = mx;
        max_y = my;
        player = p;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();
        int delta;
        if(player.isTopPlayer()){
            delta = -1;
        }
        else{
            delta = 1;
        }

        int nx, ny;
        for(int i=-1; i <=1; i++){
            nx = x +i;
            ny = y + delta;
            if(withinBoard(nx,ny)){
                moves.add(new Move(this,nx,ny));
            }
        }

        return moves;
    }

    @Override
    public MoveSet generateMoves(Piece[][] board) {
        MoveSet m = new MoveSet();
        int delta;
        if(player.isTopPlayer()){
            delta = -1;
        }
        else{
            delta = 1;
        }

        int nx, ny;
        Piece p;

        ny = y +delta;
        if(withinBoard(x,ny)){
            p = board[ny][x];
            if(p == null){
                m.addMove(new Move(this,x,ny));
            }
        }

        nx = x -1;
        if(withinBoard(nx,ny)){
            p = board[ny][nx];
            if(p != null){
                if(p.getPlayer() != player){
                    m.addCapture(new Move(this, nx, ny));
                }
            }
        }

        nx = x +1;
        if(withinBoard(nx,ny)){
            p = board[ny][nx];
            if(p != null){
                if(p.getPlayer() != player){
                    m.addCapture(new Move(this,nx,ny));
                }
            }
        }



        return m;
    }

    @Override
    public String toString(){
        return " "+player.getPlayerColorChar() + "P ";
    }

      
    @Override
    public boolean promotes(){
        return true;
    }

    @Override
    public Piece copy() {
        Pawn p = new Pawn(max_x,max_y,player);
        p.setX(x);
        p.setY(y);
        return p;
    }
}

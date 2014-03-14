package Pieces;

import Core.Move;
import Core.MoveSet;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Bishop extends Piece {

    public Bishop(int mx, int my){
        max_x = mx;
        max_y = my;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();
        int new_x, new_y;

        for(int i= (-1)*max_x; i< max_x; i++){
            if(i != 0){
                new_x = x + i;
                new_y = y + i;

                if(withinBoard(new_x, new_y)){
                    moves.add(new Move(this,new_x, new_y));
                }

                new_y = y -i;
                if(withinBoard(new_x, new_y)){
                    moves.add(new Move(this,new_x, new_y));
                }
            }
        }
        return moves;
    }

    @Override
    public MoveSet generateMoves(Piece[][] board) {
        Piece p;
        MoveSet m =  new MoveSet();

        //North Right Diagonal
        for(int nx = x+1, ny = y+1; withinBoard(nx,ny); nx++, ny++){
            p = board[ny][nx];
            if(p != null){
                if(p.getPlayer() != player){
                    m.addCapture(new Move(this,nx,ny));
                }
                break;
            }
            else{
                m.addMove(new Move(this,nx,ny));
            }
        }

        //North Left Diagonal
        for(int nx = x-1, ny = y+1; withinBoard(nx,ny); nx--, ny++){
            p = board[ny][nx];
            if(p != null){
                if(p.getPlayer() != player){
                    m.addCapture(new Move(this,nx,ny));
                }
                break;
            }
            else{
                m.addMove(new Move(this,nx,ny));
            }
        }

        //South Left Diagonal
        for(int nx = x-1, ny = y-1; withinBoard(nx,ny); nx--, ny--){
            p = board[ny][nx];
            if(p != null){
                if(p.getPlayer() != player){
                    m.addCapture(new Move(this,nx,ny));
                }
                break;
            }
            else{
                m.addMove(new Move(this,nx,ny));
            }
        }

        //South Right Diagonal
        for(int nx = x+1, ny = y-1; withinBoard(nx,ny); nx++, ny--){
            p = board[ny][nx];
            if(p != null){
                if(p.getPlayer() != player){
                    m.addCapture(new Move(this,nx,ny));
                }
                break;
            }
            else{
                m.addMove(new Move(this,nx,ny));
            }
        }

        return m;
    }

    @Override
    public Piece copy() {
        Bishop b = new Bishop(max_x,max_y);
        b.setPlayer(player);
        b.setX(x);
        b.setY(y);
        return b;
    }

    @Override
    public String toString(){
        return " "+player.getPlayerColorChar() + "B ";
    }
}

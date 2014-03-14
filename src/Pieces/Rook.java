package Pieces;

import Core.Move;
import Core.MoveSet;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Rook extends Piece {

    public Rook(int x, int y){
        max_x =x;
        max_y = y;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();

        for(int i=0; i< max_x; i++){
            if(i!= x){
                moves.add(new Move(this,i,y));
            }

            if(i != y){
                moves.add(new Move(this,x,i));
            }
        }
        return moves;
    }

    @Override
    public MoveSet generateMoves(Piece[][] board) {
        Piece p;
        int nx, ny;
        MoveSet m = new MoveSet();

        //Right
        for(nx = x+1, ny = y; withinBoard(nx,ny); nx++){
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

        //Up
        for(nx = x, ny = y+1; withinBoard(nx,ny); ny++){
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

        //Left
        for(nx = x-1, ny = y; withinBoard(nx,ny); nx--){
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

        for(nx = x, ny = y-1; withinBoard(nx,ny); ny--){
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
        Rook r = new Rook(max_x,max_y);
        r.setPlayer(player);
        r.setY(y);
        r.setX(x);
        return r;
    }

    @Override
    public String toString(){
        return " "+player.getPlayerColorChar() + "R ";
    }
}

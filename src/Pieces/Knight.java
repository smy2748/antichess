package Pieces;

import Core.Move;
import Core.MoveSet;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Knight extends Piece {

    public Knight(int mx, int my){
        max_x = mx;
        max_y = my;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();

        int new_x, new_y;

        for(int i= -2; i<=2; i+=4){
            for(int j=-1; j <= 1; j +=2){
                new_x = x +i;
                new_y = y+j;

                if(withinBoard(new_x, new_y)){
                    moves.add(new Move(this,new_x,new_y));
                }

                new_x = x+j;
                new_y = y+i;

                if(withinBoard(new_x, new_y)){
                    moves.add(new Move(this,new_x,new_y));
                }

            }
        }

        return moves;
    }

    @Override
    public MoveSet generateMoves(Piece[][] board) {
        MoveSet m = new MoveSet();
        int new_x, new_y;
        Piece p;

        for(int i= -2; i<=2; i+=4){
            for(int j=-1; j <= 1; j +=2){
                new_x = x +i;
                new_y = y+j;

                if(withinBoard(new_x, new_y)){
                    p = board[new_y][new_x];
                    if(p != null){
                        if(p.getPlayer() != player){
                            m.addCapture(new Move(this,new_x,new_y));
                        }
                    }
                    else{
                        m.addMove(new Move(this,new_x, new_y));
                    }
                }

                new_x = x+j;
                new_y = y+i;

                if(withinBoard(new_x, new_y)){
                    p = board[new_y][new_x];
                    if(p != null){
                        if(p.getPlayer() != player){
                            m.addCapture(new Move(this,new_x,new_y));
                        }
                    }
                    else{
                        m.addMove(new Move(this,new_x, new_y));
                    }
                }

            }
        }

        return m;
    }

    @Override
    public String toString(){
        return " "+player.getPlayerColorChar() + "N ";
    }
}

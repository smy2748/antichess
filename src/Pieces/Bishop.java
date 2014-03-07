package Pieces;

import Core.Move;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Bishop extends Piece {

    public Bishop(int mx, int my){
        max_x = x;
        max_y = y;
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
}

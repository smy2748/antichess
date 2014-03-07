package Pieces;

import Core.Move;

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
}

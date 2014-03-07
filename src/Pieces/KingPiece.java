package Pieces;

import Core.Move;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class KingPiece extends Piece {

    public KingPiece(int max_x, int max_y){
        this.max_x = max_x;
        this.max_y = max_y;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves= new ArrayList<Move>();

        for(int i=-1; i <= 1; i++ ){
            for(int j=-1; j<= 1; j++){
                if(j!=0 || i!= 0){
                    int new_x = this.x +i,
                        new_y = this.y + j;
                    if(new_x>=0 && new_y>=0 && new_x < max_x && new_y < max_y){
                        moves.add(new Move(this,new_x,new_y));
                    }
                }
            }
        }

        return moves;
    }
}

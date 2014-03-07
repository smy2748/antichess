package Pieces;

import Core.Move;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Queen extends Piece{

    public Queen(int max_x, int max_y){
        this.max_x = max_x;
        this.max_y = max_y;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();

        for(int i=0; i < this.max_x; i++){
            if(i != this.x){
                moves.add(new Move(this,i,this.y));
            }
            if( i != this.y){
                moves.add(new Move(this,this.x,i));
            }
        }
        int new_x, new_y;
        for(int i= (-1)*this.max_x; i < this.max_x; i++){
                if(i != 0 ){
                    new_x = this.x + i;
                    new_y = this.y + i;

                    if(new_x >=0 && new_x < max_x &&
                       new_y >=0 && new_y < max_y){
                        moves.add(new Move(this,new_x,new_y));
                    }

                    new_y = this.y - i;

                    if(new_x >=0 && new_x < max_x &&
                            new_y >=0 && new_y < max_y){
                        moves.add(new Move(this,new_x,new_y));
                    }

                }

        }


        return moves;
    }
}

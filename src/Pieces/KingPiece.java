package Pieces;

import Core.Move;
import Core.MoveSet;

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

    @Override
    public MoveSet generateMoves(Piece[][] board) {
        MoveSet m = new MoveSet();
        int new_x, new_y;
        Piece p;

        for(int i=-1; i <= 1; i++ ){
            for(int j=-1; j<= 1; j++){
                new_x = this.x +i;
                new_y = this.y + j;
                if(withinBoard(new_x,new_y) && (j !=0 || i != 0)){
                    p = board[new_y][new_x];
                    if(p != null){
                        if(p.getPlayer() != player){
                            m.addCapture(new Move(this,new_x, new_y));
                        }
                    }
                    else{
                        m.addMove(new Move(this,new_x,new_y));
                    }
                }
            }
        }

        return m;
    }

    @Override
    public Piece copy() {
        KingPiece kp = new KingPiece(max_x,max_y);
        kp.setPlayer(player);
        kp.setX(x);
        kp.setY(y);
        return kp;
    }

    @Override
    public String toString(){
        return " "+player.getPlayerColorChar() + "K ";
    }
}

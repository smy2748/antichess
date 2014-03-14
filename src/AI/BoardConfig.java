package AI;

import Core.Board;
import Pieces.Piece;

/**
 * Created by Stephen Yingling on 3/13/14.
 */
public class BoardConfig extends Board{

    public BoardConfig(){}

    public BoardConfig copy(){
        return BoardConfig.copy(this);
    }

    public static BoardConfig copy(Board b){
        BoardConfig bc = new BoardConfig();
        bc.setPlayer1(b.getPlayer1());
        bc.setPlayer2(b.getPlayer2());
        bc.setLastCapture(b.getLastCapture());
        bc.setX_dim(b.getX_dim());
        bc.setX_dim(b.getY_dim());
        Piece[] row;
        Piece p;
        for(int i=0; i<8; i++){
            row = b.getBoardSquares()[i];
            for(int j=0; j <8; j++){
                p = row[j];
                if(p!= null){
                    bc.getBoardSquares()[i][j] = p.copy();
                }
            }
        }

        return bc;
    }

}

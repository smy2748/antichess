package Core;

import Pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class RandomPlayer extends Player {
    protected Random random;

    public RandomPlayer(){random = new Random();}



    @Override
    public Move getMove() {
        ArrayList<Piece> pieces = board.getPiecesForPlayer(this);

        MoveSet m = new MoveSet();

        for(Piece p: pieces){
            m = m.merge(p.generateMoves(board.getBoardSquares()));
        }

        Move result;
        ArrayList<Move> moves;
        if(m.anyCaptures()){
            moves = m.getCaptures();

        }
        else{
            moves = m.getMoves();
        }

        result = moves.get(random.nextInt(moves.size()));


        return result;
    }
}

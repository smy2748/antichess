package AI;

import Core.Board;
import Core.Move;
import Core.MoveSet;
import Core.Player;
import Pieces.Piece;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/8/14.
 */
public class AIPlayer extends Player {
    protected int lookAhead;

    public AIPlayer(Board board){
        this.board = board;
        lookAhead = 5;
    }

    @Override
    public Move getMove() {
        ArrayList<Piece> pieces = board.getPiecesForPlayer(this);

        MoveSet m = new MoveSet();

        for(Piece p: pieces){
            m = m.merge(p.generateMoves(board.getBoardSquares()));
        }


        return null;
    }


}

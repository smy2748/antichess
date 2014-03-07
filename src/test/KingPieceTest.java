package test;
import Core.Move;
import Pieces.KingPiece;
import Pieces.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class KingPieceTest {

    @Test
    public void testKingMoves(){
        Piece p = new KingPiece(8,8);
        p.setX(0);
        p.setY(0);

        List<Move> moves= p.generateMoves();

        assertNotNull(moves);
        assertEquals(moves.size(),3);
        for(Move m : moves){
            assertTrue(m.getX1() >=0);
            assertTrue(m.getY1() >=0);
        }

        p.setX(7);
        p.setY(7);

        moves = p.generateMoves();

        assertEquals(moves.size(),3);
        for(Move m : moves){
            assertTrue(m.getX1() < 8);
            assertTrue(m.getY1() < 8);
        }

        p.setX(5);
        p.setY(3);

        moves = p.generateMoves();

        assertEquals(8,moves.size());

    }
}

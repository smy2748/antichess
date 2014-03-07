package test;

import Core.Move;
import Pieces.Queen;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class QueenTest {

    @Test
    public void testMoves(){
        Queen q = new Queen(8,8);

        q.setX(0);
        q.setY(0);

        List<Move> moves = q.generateMoves();



        assertEquals(21,moves.size());

        q.setX(7);

        moves = q.generateMoves();

        assertEquals(21,moves.size());

        q.setY(7);

        moves = q.generateMoves();

        assertEquals(21,moves.size());

        q.setX(0);

        moves = q.generateMoves();

        assertEquals(21,moves.size());

        q.setX(5);
        q.setY(4);

        moves = q.generateMoves();
        for(Move m : moves){
            System.out.println(m.toString());
        }

    }
}

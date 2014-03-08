package test;

import Core.*;
import Pieces.Bishop;
import Pieces.Knight;
import Pieces.Piece;
import Pieces.Rook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class BoardTest {

    @Test
    public void testSetup(){
        Board b = new Board(8,8);

        b.setUpGame();

        //System.out.println(b);

        Piece bishop = b.getBoardSquares()[0][2];

        MoveSet m = bishop.generateMoves(b.getBoardSquares());

        assertEquals(0,m.getCaptures().size());
        assertEquals(0, m.getMoves().size());
        assertFalse(m.anyCaptures());

        Piece knight = b.getBoardSquares()[0][1];

        m = knight.generateMoves(b.getBoardSquares());

        assertEquals(0,m.getCaptures().size());
        assertEquals(2, m.getMoves().size());
        assertFalse(m.anyCaptures());

        Piece pawn = b.getBoardSquares()[1][1];

        m = pawn.generateMoves(b.getBoardSquares());

        assertEquals(0,m.getCaptures().size());
        assertEquals(1, m.getMoves().size());
        assertFalse(m.anyCaptures());

    }


    public void testMakeMove() throws InvalidMoveException {
        Board b = new Board(8,8);

        b.setUpGame();

        System.out.println(b);

        Piece pawn = b.getBoardSquares()[1][4];

        Move m = new Move(pawn,4,2);
        b.makeMove(m);

        pawn = b.getBoardSquares()[6][0];
        m = new Move(pawn,0,5);
        b.makeMove(m);

        Piece bis = b.getBoardSquares()[0][5];
        m = new Move(bis,0,5);
        b.makeMove(m);

        System.out.println(b);
    }

    @Test
    public void picture(){
        Board b = new Board(8,8);
        Player p = new RandomPlayer();
        p.setTopPlayer(true);
        b.setPlayer1(p);
        p = new RandomPlayer();
        p.setTopPlayer(false);
        b.setPlayer2(p);

        Piece knight = new Knight(8,8);
        knight.setPlayer(b.getPlayer1());
        knight.setX(3);
        knight.setY(2);
        b.getBoardSquares()[2][3] = knight;



        Piece bis = new Bishop(8,8);
        bis.setPlayer(b.getPlayer2());
        bis.setX(2);
        bis.setY(4);
        b.getBoardSquares()[4][2] = bis;

        Piece rook = new Rook(8,8);
        rook.setPlayer(b.getPlayer2());
        rook.setX(4);
        rook.setY(4);
        b.getBoardSquares()[4][4] = rook;

        bis = new Bishop(8,8);
        bis.setPlayer(b.getPlayer1());
        bis.setX(0);
        bis.setY(0);
        b.getBoardSquares()[0][0] = bis;



        System.out.println(b);

    }

}

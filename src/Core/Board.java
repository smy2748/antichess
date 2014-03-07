package Core;

import Pieces.Piece;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/5/14.
 */
public class Board {

    protected Piece[][] boardSquares;
    protected int x_dim;
    protected int y_dim;

    public Board(){
        boardSquares = new Piece[8][8];
        x_dim = 8;
        y_dim = 8;
    }

    public Board(int x, int y){
        x_dim = x;
        y_dim = y;
        boardSquares = new Piece[y][x];

    }

    public void placePieces(ArrayList<Piece> pieces){
        for (Piece p : pieces){
            if (p.getX()< x_dim && p.getY()<y_dim){
                boardSquares[p.getY()][p.getX()] = p;
            }
        }
    }

    public void makeMove(Move move) throws InvalidMoveException {
        if(!validateMove(move)){
            throw new InvalidMoveException();
        }

        int x0, y0, x1, y1;
        Piece p = move.getActivePiece();
        x0 = move.getX0();
        y0 = move.getY0();
        x1 = move.getX1();
        y1 = move.getY1();


        boardSquares[y1][x1] = p;
        p.setX(x1);
        p.setY(y1);
        boardSquares[y0][x0] = null;

    }

    public boolean validateMove(Move move){
        if(move == null || move.getActivePiece() == null){
            return false;
        }

        if(move.getX1() <0 || move.getX1() >= x_dim){
            return false;
        }

        if(move.getY1() <0 || move.getY1() >= y_dim){
            return false;
        }

        Piece p = move.getActivePiece();
        Piece bp = boardSquares[p.getY()][p.getX()];

        if(! p.equals(bp)){
            return false;
        }

        ArrayList<Move> moves = p.generateMoves();
        for(Move m : moves){
            if(m.getX1() == move.getX1() &&
                    m.getY1() == move.getY1()){
                return true;
            }
        }

        return false;
    }

    public Piece[][] getBoardSquares() {
        return boardSquares;
    }

    public int getX_dim() {
        return x_dim;
    }

    public int getY_dim() {
        return y_dim;
    }
}

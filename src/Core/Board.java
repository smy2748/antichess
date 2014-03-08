package Core;

import Pieces.*;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/5/14.
 */
public class Board {

    protected Piece[][] boardSquares;
    protected int x_dim;
    protected int y_dim;
    protected Player p1,p2;


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

        if(p.promotes() && (p.getY() == 0 || p.getY()==7)){
            p = promotePiece(p,move);
        }


        boardSquares[y1][x1] = p;
        p.setX(x1);
        p.setY(y1);
        boardSquares[y0][x0] = null;

    }

    public Piece promotePiece(Piece p, Move m){
        Piece result;
        if(m.getUpgradeType() == 0){
            result = new Queen(x_dim,y_dim);
        }
        else if(m.getUpgradeType() == 1){
            result = new Bishop(x_dim,y_dim);
        }
        else if(m.getUpgradeType() == 2){
            result = new Rook(x_dim,y_dim);
        }
        else {
            result = new Knight(x_dim,y_dim);
        }

        result.setPlayer(p.getPlayer());
        result.setX(m.getX1());
        result.setY(m.getY1());

        return result;
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

        MoveSet moves = p.generateMoves(boardSquares);
        if(moves.anyCaptures()){
            for(Move m : moves.getCaptures()){
                if(m.getX1() == move.getX1()
                        && move.getY1() == move.getY1()){
                    return true;
                }
            }
        }
        else{
            for(Move m : moves.getMoves()){
                if(m.getX1() == move.getX1()
                        && move.getY1() == move.getY1()){
                    return true;
                }
            }
        }

        return false;
    }

    public void setPlayer1(Player player1){
        p1 = player1;
    }

    public void setPlayer2(Player player2){
        p2 = player2;
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }

    public ArrayList<Piece> getPiecesForPlayer(Player p){
        ArrayList<Piece> result = new ArrayList<Piece>();
        for(int i=0; i<boardSquares.length; i++){
            Piece[] row = boardSquares[i];
            for(int j=0; j< row.length; j++){
                if(row[j] != null){
                    Piece piece = row[j];
                    if(piece.getPlayer() == p){
                        result.add(piece);
                    }
                }
            }
        }

        return result;
    }

    public void setUpGame(){
        if(p1 == null){
            p1 = new RandomPlayer();
        }
        p1.setTopPlayer(false);
        p1.setBoard(this);
        if(p2 == null){
            p2 = new RandomPlayer();
        }
        p2.setTopPlayer(true);
        p2.setBoard(this);

        Piece p;
        p = new Rook(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(0);
        p.setY(0);
        boardSquares[0][0] = p;

        p = new Knight(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(1);
        p.setY(0);
        boardSquares[0][1] = p;

        p = new Bishop(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(2);
        p.setY(0);
        boardSquares[0][2] = p;

        p = new Queen(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(3);
        p.setY(0);
        boardSquares[0][3] = p;

        p = new KingPiece(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(4);
        p.setY(0);
        boardSquares[0][4] = p;

        p = new Bishop(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(5);
        p.setY(0);
        boardSquares[0][5] = p;

        p = new Knight(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(6);
        p.setY(0);
        boardSquares[0][6] = p;

        p = new Rook(x_dim,y_dim);
        p.setPlayer(p1);
        p.setX(7);
        p.setY(0);
        boardSquares[0][7] = p;

        for(int i=0; i< x_dim; i++){
            p = new Pawn(x_dim, y_dim,p1);
            p.setX(i);
            p.setY(1);
            boardSquares[1][i] = p;
        }

        p = new Rook(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(0);
        p.setY(7);
        boardSquares[7][0] = p;

        p = new Knight(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(1);
        p.setY(7);
        boardSquares[7][1] = p;

        p = new Bishop(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(2);
        p.setY(7);
        boardSquares[7][2] = p;

        p = new Queen(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(3);
        p.setY(7);
        boardSquares[7][3] = p;

        p = new KingPiece(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(4);
        p.setY(7);
        boardSquares[7][4] = p;

        p = new Bishop(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(5);
        p.setY(7);
        boardSquares[7][5] = p;

        p = new Knight(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(6);
        p.setY(7);
        boardSquares[7][6] = p;

        p = new Rook(x_dim,y_dim);
        p.setPlayer(p2);
        p.setX(7);
        p.setY(7);
        boardSquares[7][7] = p;

        for(int i=0; i< x_dim; i++){
            p = new Pawn(x_dim, y_dim,p2);
            p.setX(i);
            p.setY(6);
            boardSquares[6][i] = p;
        }
    }

    public Piece[][] getBoardSquares() {
        return boardSquares;
    }

    @Override
    public String toString(){
        String result = "";

        for(int i =boardSquares.length -1; i>= 0; i--){
            Piece[] row= boardSquares[i];

            for(int j=0; j<row.length; j++){
                if(row[j] == null){
                    result += " -- ";
                }
                else{
                    result += row[j].toString();
                }
            }
            result +="\n";
        }

        return result;
    }

    public int getX_dim() {
        return x_dim;
    }

    public int getY_dim() {
        return y_dim;
    }
}

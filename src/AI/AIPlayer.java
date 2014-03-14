package AI;

import Core.*;
import Pieces.*;

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
    public Move getMove() throws NoMovesException {
        ArrayList<Piece> pieces = board.getPiecesForPlayer(this);

        MoveSet m = new MoveSet();

        for(Piece p: pieces){
            m = m.merge(p.generateMoves(board.getBoardSquares()));
        }

        if(!m.anyCaptures() && m.getMoves().size() <= 0){
            throw new NoMovesException();
        }
        BoardConfig bc;
        ArrayList<Move> moves;
        if(m.anyCaptures()){
            moves = m.getCaptures();
        }
        else{
            moves = m.getMoves();
        }
        int max = -99, cur;
        Move makeThis = moves.get(0);
        for(Move move : moves){
            bc = BoardConfig.copy(board);
            Move pm = new ProjectedMove(move);
            try {
                bc.makeMove(pm);
            } catch (InvalidMoveException e) {
                e.printStackTrace();
            }
            cur = minimax(bc,5,-300,300,false);

            if(cur > max){
                max = cur;
                makeThis = move;
            }

        }

        return makeThis;
    }

    private int minimax(Board cur, int depth, int alphaScore, int betaScore, boolean maxTurn){
       ArrayList<Piece> pieces;
        if(maxTurn){
            pieces = cur.getPiecesForPlayer(this);
        }
        else{
            pieces = cur.getPiecesForPlayer(getOpponent());
        }
        ArrayList<Move> moves;
       MoveSet m = new MoveSet();
        BoardConfig bc;
        for(Piece p: pieces){
            m = m.merge(p.generateMoves(cur.getBoardSquares()));
        }

        if(depth ==0 || (m.getCaptures().size()<=0 && m.getMoves().size()<=0)){
            return evaluate(cur);
        }

        if(maxTurn){
            if(m.anyCaptures()){
                moves = m.getCaptures();
            }

            else{
                moves = m.getMoves();
            }

            for(Move move : moves){
                bc = BoardConfig.copy(cur);
                Move pm = new ProjectedMove(move);
                try {
                    bc.makeMove(pm);
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                }
                alphaScore = Math.max(alphaScore, minimax(bc,depth-1,alphaScore,betaScore,false));

                if (betaScore <= alphaScore){
                    break;
                }
            }

            return alphaScore;
        }

        else{
            if(m.anyCaptures()){
                moves = m.getCaptures();
            }

            else{
                moves = m.getMoves();
            }

            for(Move move : moves){
                bc = BoardConfig.copy(cur);
                Move pm = new ProjectedMove(move);
                try {
                    bc.makeMove(pm);
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                }
                betaScore = Math.min(betaScore, minimax(bc, depth - 1, alphaScore, betaScore, true));

                if (betaScore <= alphaScore){
                    break;
                }
            }

            return betaScore;

        }

    }


    private Player getOpponent(){
        if(board.getPlayer1() == this){
            return board.getPlayer2();
        }

        return board.getPlayer1();
    }

    private int evaluate(Board b){
        Player opponent;
        ArrayList<Piece> us, them;
        us = b.getPiecesForPlayer(this);

        if(b.getPlayer1() == this){
            opponent = b.getPlayer2();
        }
        else{
            opponent = b.getPlayer1();
        }

        them = b.getPiecesForPlayer(opponent);

        int score =0;

        for(Piece p : us){
            score -= getScoreForPiece(p);
        }

        for(Piece p: them){
            score += getScoreForPiece(p);
        }

        return score;

    }

    private int getScoreForPiece(Piece p){
        int score;
        if(p instanceof Bishop){
            score = 4;
        }
        else if(p instanceof KingPiece){
            score = 2;
        }
        else if(p instanceof Knight){
            score = 4;
        }
        else if(p instanceof Pawn){
            score = 1;
        }
        else if(p instanceof Queen){
            score = 7;
        }
        else if(p instanceof Rook){
            score = 5;
        }
        else{
            score = 1;
        }

        return score;
    }


}

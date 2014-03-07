package Pieces;

import Core.Move;
import Core.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Pawn extends Piece {

    public Pawn(int mx, int my, Player p){
        max_x = mx;
        max_y = my;
        player = p;
    }

    @Override
    public ArrayList<Move> generateMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();
        int delta;
        if(player.isTopPlayer()){
            delta = -1;
        }
        else{
            delta = 1;
        }

        int nx, ny;
        for(int i=-1; i <=1; i++){
            nx = x +i;
            ny = y + delta;
            if(withinBoard(nx,ny)){
                moves.add(new Move(this,nx,ny));
            }
        }

        return moves;
    }

    public ArrayList<Move> validMovesAfterCollisions(Piece p){
        ArrayList<Move> result = new ArrayList<Move>();

        List<Move> moves = p.generateMoves();

        return result;
    }
}

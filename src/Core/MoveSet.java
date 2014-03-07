package Core;

import java.util.ArrayList;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class MoveSet {

    protected ArrayList<Move> moves;
    protected ArrayList<Move> captures;

    public MoveSet(){
        moves = new ArrayList<Move>();
        captures = new ArrayList<Move>();
    }

    public void addCapture(Move m){
        captures.add(m);
    }

    public void addMove(Move m){
        moves.add(m);
    }

    public boolean anyCaptures(){
        return captures.size() > 0;
    }

    public ArrayList<Move> getMoves(){
        return moves;
    }

    public ArrayList<Move> getCaptures(){
        return captures;
    }
}

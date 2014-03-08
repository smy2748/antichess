package Core;

import java.util.ArrayList;
import java.util.List;

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

    public void addAllMoves(List<Move> moves){
        this.moves.addAll(moves);
    }

    public void addAllCaptures(List<Move> caps){
        this.captures.addAll(caps);
    }

    public MoveSet merge(MoveSet other){
        MoveSet result = new MoveSet();

        result.addAllCaptures(captures);
        result.addAllCaptures(other.getCaptures());

        result.addAllMoves(moves);
        result.addAllMoves(other.getMoves());

        return result;
    }
}

package Core;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public abstract class Player {
    protected String name;
    protected boolean topPlayer;
    protected Board board;

    public Player(){}

    public Player(String name, boolean topPlayer, Board b) {
        this.name = name;
        this.topPlayer = topPlayer;
        board = b;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTopPlayer() {
        return topPlayer;
    }

    public void setTopPlayer(boolean topPlayer) {
        this.topPlayer = topPlayer;
    }

    public String getPlayerColorChar(){
        if(topPlayer){
            return "W";
        }
            return "B";
    }

    public String getPlayerColor(){
        if(topPlayer){
            return "White";
        }
        return "Black";
    }

    public abstract Move getMove();
}

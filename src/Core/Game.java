package Core;

import java.io.FileWriter;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Game {

    protected Player p1;
    protected Player p2;

    public void playGame(){
        p1 = new RandomPlayer();
        p2 = new RandomPlayer();

        Board b = new Board();
        b.setPlayer1(p1);
        b.setPlayer2(p2);

        b.setUpGame();
        Player activePlayer = p1;
        while (b.getPiecesForPlayer(p1).size() > 0 && b.getPiecesForPlayer(p2 ).size()>0){
            System.out.println(b);
            Move m = activePlayer.getMove();
            try{
                b.makeMove(m);
            } catch(InvalidMoveException e){
                System.out.println("Invalid Move made:" + m.toString());
            }
            if(activePlayer == p1){
                activePlayer = p2;
            }
            else{
                activePlayer = p1;
            }
        }


    }

    public static void main(String args[]){
        Game g = new Game();
        g.playGame();
    }
}

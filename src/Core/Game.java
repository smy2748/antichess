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
        boolean stalemate = false;

        Board b = new Board();
        b.setPlayer1(p1);
        b.setPlayer2(p2);

        b.setUpGame();
        Player activePlayer = p1;
        while (b.getPiecesForPlayer(p1).size() > 0 && b.getPiecesForPlayer(p2 ).size()>0 && b.getLastCapture()<= 50){
            System.out.println(b);
            Move m = new Move(null,0,0);
            try{
                m = activePlayer.getMove();
            }
            catch(NoMovesException e){
                stalemate = true;
                break;
            }
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

        System.out.println(b);

        if(stalemate){
            System.out.println(activePlayer.getName() + " playing as " + activePlayer.getPlayerColor() + " wins via Stalemate!");
        }
        else if(b.getLastCapture() > 50){
            System.out.println("Game was a draw due to 50 move rule");
        }
        else if(b.getPiecesForPlayer(p1).size() == 0){
            System.out.println(p1.getName() + " playing as " + p1.getPlayerColor() + " wins!");
        }
        else{
            System.out.println(p2.getName() + " playing as " + p2.getPlayerColor() + " wins!");
        }


    }

    public static void main(String args[]){
        Game g = new Game();
        g.playGame();
    }
}

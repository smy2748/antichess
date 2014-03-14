package Core;

import AI.AIPlayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Game {

    protected Player p1;
    protected Player p2;

    public void playGame(int gameNum) throws IOException {
        File f = new File("game-num-"+gameNum);
        FileWriter fw = new FileWriter(f);
        p1 = new RandomPlayer();

        boolean stalemate = false;

        Board b = new Board();
        p2 = new AIPlayer(b);
        p1.setName("Rando");
        p2.setName("AI Player");
        b.setPlayer1(p1);
        b.setPlayer2(p2);

        b.setUpGame();

        fw.write("Starting game with " + p1 + " starting.\n");
        Player activePlayer = p1;
        while (b.getPiecesForPlayer(p1).size() > 0 && b.getPiecesForPlayer(p2 ).size()>0 && b.getLastCapture()<= 50){
            System.out.println(b);
            fw.write(b.toString());
            Move m = new Move(null,0,0);
            try{
                m = activePlayer.getMove();
            }
            catch(NoMovesException e){
                stalemate = true;
                break;
            }

            System.out.println(activePlayer.getName() + " makes move " + m);
            fw.write(activePlayer.getName() + " makes move " + m +"\n");
            try{
                b.makeMove(m);
            } catch(InvalidMoveException e){
                System.out.println("Invalid Move made: " + m.toString());
            }

            if(activePlayer == p1){
                activePlayer = p2;
            }
            else{
                activePlayer = p1;
            }
        }



        if(stalemate){
            System.out.println(activePlayer.getName() + " playing as " + activePlayer.getPlayerColor() + " wins via Stalemate!");
            fw.write(activePlayer.getName() + " playing as " + activePlayer.getPlayerColor() + " wins via Stalemate!");
        }
        else if(b.getLastCapture() > 50){
            System.out.println(b);
            fw.write(b.toString());
            System.out.println("Game was a draw due to 50 move rule");
            fw.write("Game was a draw due to 50 move rule");
        }
        else if(b.getPiecesForPlayer(p1).size() == 0){
            System.out.println(b);
            fw.write(b.toString());
            System.out.println(p1.getName() + " playing as " + p1.getPlayerColor() + " wins!");
            fw.write(p1.getName() + " playing as " + p1.getPlayerColor() + " wins!");
        }
        else{
            System.out.println(b);
            fw.write(b.toString());
            System.out.println(p2.getName() + " playing as " + p2.getPlayerColor() + " wins!");
            fw.write(p2.getName() + " playing as " + p2.getPlayerColor() + " wins!");
        }

        fw.close();
    }

    public static void main(String args[]) throws IOException {
        Game g = new Game();

        for(int i=0; i< 20; i++){
            g.playGame(i);
        }
    }
}

package Core;

import AI.AIPlayer;
import CLI.HumanPlayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Game {

    protected Player p1;
    protected Player p2;

    public void playGame(int gameNum) throws IOException {
        File f = new File("game-num-"+gameNum);
        FileWriter fw = new FileWriter(f);


        boolean stalemate = false;

        Board b = new Board();
        b.setPlayer1(p1);
        b.setPlayer2(p2);

        b.setUpGame();
        Player activePlayer;
        Random r = new Random();
        if(r.nextInt(100) <50){
            activePlayer = p1;
        }
        else{
            activePlayer = p2;
        }

        fw.write("Starting game with " + activePlayer.getName() + " playing " + activePlayer.getPlayerColor() + " starting.\n");
        System.out.println("Starting game with " + activePlayer.getName() + " playing " + activePlayer.getPlayerColor() + " starting.\n");

        while (b.getPiecesForPlayer(p1).size() > 0 && b.getPiecesForPlayer(p2 ).size()>0 && b.getLastCapture()<= 50){
            //System.out.println(b);
            fw.write("\n"+b +"\n");
            Move m = new Move(null,0,0);
            try{
                m = activePlayer.getMove();
            }
            catch(NoMovesException e){
                stalemate = true;
                break;
            }

            //System.out.println(activePlayer.getName() + " makes move " + m);
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
            activePlayer.setWins(activePlayer.getWins()+1);
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
            p1.setWins(p1.getWins()+1);
        }
        else{
            System.out.println(b);
            fw.write(b.toString());
            System.out.println(p2.getName() + " playing as " + p2.getPlayerColor() + " wins!");
            fw.write(p2.getName() + " playing as " + p2.getPlayerColor() + " wins!");
            p2.setWins(p2.getWins()+1);
        }

        fw.close();
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public static void main(String args[]) throws IOException {
        Game g = new Game();
        Player p1 = new HumanPlayer();
        p1.setName("Human");
        g.setP1(p1);

        Player p2 = new AIPlayer();
        p2.setName("AI Player");
        g.setP2(p2);

        //for(int i=0; i< 100; i++){
            g.playGame(200);
       // }

        System.out.println(g.getP1().getName() + " has " + g.getP1().getWins() + " out of 100");
        System.out.println(g.getP2().getName() + " has " + g.getP2().getWins() + " out of 100");
    }
}

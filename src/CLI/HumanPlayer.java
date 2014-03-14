package CLI;

import Core.Move;
import Core.NoMovesException;
import Core.Player;

import java.util.Scanner;

/**
 * Created by Stephen Yingling on 3/13/14.
 */
public class HumanPlayer extends Player {


    @Override
    public Move getMove() throws NoMovesException {
        boolean validMove = false;
        Move m = new Move(null,-1,-1);
        Scanner sc = new Scanner(System.in);
        System.out.println(board.toString());
        while(!validMove){
            m = parseMove(sc);

            if(board.validateMove(m)){
                validMove = true;
            }
            else{
                System.out.println("That is an invalid move");
            }
        }

        return m;
    }

    private Move parseMove(Scanner sc){
        Move m;
        System.out.println("\n"+name +"'s turn." +"\n Input a move: ");
        String moveString = sc.nextLine();
        moveString = moveString.trim();

        if(moveString.equalsIgnoreCase("SHOW")){
            System.out.println("\nReshowing the board: ");
            System.out.println(board.toString()+"\n");

            m = parseMove(sc);
        }
        else{
            String[] mArr= moveString.split(",");

            if(mArr.length != 4){
                System.out.println("Not enough arguements, try again\n");
                m = parseMove(sc);
            }
            else{
                int x0, x1, y0, y1;
                x0 = Integer.parseInt(mArr[0]);
                y0 = Integer.parseInt(mArr[1]);
                x1 = Integer.parseInt(mArr[2]);
                y1 = Integer.parseInt(mArr[3]);

                m = new Move(board.getBoardSquares()[y0][x0],x1,y1);
            }
        }

        return m;
    }
}

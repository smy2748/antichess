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


    }
}

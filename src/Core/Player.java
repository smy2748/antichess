package Core;

/**
 * Created by Stephen Yingling on 3/7/14.
 */
public class Player {
    protected String name;
    protected boolean topPlayer;

    public Player(){}

    public Player(String name, boolean topPlayer) {
        this.name = name;
        this.topPlayer = topPlayer;
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
}

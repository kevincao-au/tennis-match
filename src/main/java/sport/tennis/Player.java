package sport.tennis;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author Kevin Cao
 */
public class Player {

    public static final List<String> GAME_SCORE_BOARD = Arrays.asList("0", "15", "30", "40");

    private AtomicInteger gameScore; // Game Score may be updated atomically

    private int setScore;

    private String name;

    public Player(String name) {
        this.name = name;
        this.gameScore = new AtomicInteger(0);
    }

    public void winPoint() {
        this.gameScore.incrementAndGet();
    }

    public void winGame() {
        this.setScore += 1;
    }

    public String displayGameScore(){
        return GAME_SCORE_BOARD.get(this.gameScore.get());
    }

    public int getSetScore() {
        return setScore;
    }

    public int getGameScore() {
        return gameScore.get();
    }

    public void resetGameScore() {
        this.gameScore.set(0);
    }

    public String getName() {
        return name;
    }
}

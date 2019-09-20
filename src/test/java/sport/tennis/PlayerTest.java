package sport.tennis;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Kevin Cao
 */
public class PlayerTest {

    @Test
    void testName() {
        Player player = new Player("Peter");
        assertEquals("Peter", player.getName());
    }

    @Test
    void testWinPoint1() {
        Player player = new Player("James");
        player.winPoint();
        assertEquals(1, player.getGameScore());
    }

    @Test
    void testWinPoint2() {
        Player player = new Player("James");
        IntStream.rangeClosed(1, 2).forEach(value -> {
            player.winPoint();
        });
         assertEquals(2, player.getGameScore());

    }

    @Test
    void testWinPoint3() {
        Player player = new Player("James");
        IntStream.rangeClosed(1, 3).forEach(value -> {
            player.winPoint();
        });

        assertEquals(3, player.getGameScore());
    }

    @Test
    void testWinGame1() {
        Player player = new Player("James");
        IntStream.rangeClosed(1, 2).forEach(value -> {
            player.winGame();
        });
        assertEquals(2, player.getSetScore());
    }

    @Test
    void testWinGame2() {
        Player player = new Player("James");
        IntStream.rangeClosed(1, 4).forEach(value -> {
            player.winGame();
        });
        assertEquals(4, player.getSetScore());
    }

    @Test
    void testWinGame3() {
        Player player = new Player("James");
        IntStream.rangeClosed(1, 6).forEach(value -> {
            player.winGame();
        });
        assertEquals(6, player.getSetScore());

    }

    @Test
    void testDisplayGameScore() {
        Player player = new Player("Kevin");
        assertEquals("0", player.displayGameScore());
        player.winPoint();
        assertEquals("15", player.displayGameScore());
        player.winPoint();
        assertEquals("30", player.displayGameScore());
        player.winPoint();
        assertEquals("40", player.displayGameScore());
    }


    @Test
    void testResetGameScore() {
        Player player = new Player("Kevin");
        IntStream.rangeClosed(1, 5).forEach(value -> {
            player.winPoint();
        });
        assertEquals(5, player.getGameScore());
        player.resetGameScore();
        assertEquals(0, player.getGameScore());
    }

}

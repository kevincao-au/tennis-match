package sport.tennis;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    @Test
    void testPointWonBy() {
        Player player1 = new Player("John");
        Player player2 = new Player("Mark");
        Game game = new Game(player1, player2);
        game.pointWonBy(player2);
        game.pointWonBy(player1);

        assertEquals("15 - 15", game.displayScore());
    }

    @Test
    void testPointWonByInvalidPlayer() {
        Player player1 = new Player("John");
        Player player2 = new Player("Mark");
        Game game = new Game(player1, player2);
        Player player3 = new Player("Adam");
        assertThrows(IllegalArgumentException.class, () -> {
            game.pointWonBy(player3);
        });


    }

    @Test
    void testGetLeadPlayerByGame() {
        Player player1 = new Player("John");
        Player player2 = new Player("Mark");
        Game game = new Game(player1, player2);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        game.pointWonBy(player2);

        assertEquals(player2, game.getLeadPlayerByGame());
    }

    @Test
    void testGetLeadPlayerBySet() {
        Player player1 = new Player("John");
        Player player2 = new Player("Mark");
        Game game = new Game(player1, player2);
        player1.winGame();

        assertEquals(player1, game.getLeadPlayerBySet());
    }

    @Test
    void testResetGameScore() {
        Player player1 = new Player("John");
        Player player2 = new Player("Mark");
        Game game = new Game(player1, player2);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        game.pointWonBy(player2);
        game.pointWonBy(player1);
        assertEquals("30 - 30", game.displayScore());
        game.resetGameScore();
        assertEquals("0 - 0", game.displayScore());
    }
}

package sport.tennis;


/**
 * @author Kevin Cao
 */
@FunctionalInterface
public interface GameState {

    String displayScore(Player player1, Player player2);
}

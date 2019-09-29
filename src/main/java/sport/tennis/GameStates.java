package sport.tennis;


import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author Kevin Cao
 */
public class GameStates {

//    public static Supplier<GameState> DEUCE = () ->  (player1, player2) -> displaySetScore(player1, player2, true).concat("Deuce");
//    public static Supplier<GameState> DEUCE_ADVANTAGE = () ->  (player1, player2) -> displaySetScore(player1, player2, true).concat("Advantage ".concat(getLeadPlayerByGame(player1, player2).getName()));
//    public static Supplier<GameState> IN_PROGRESS = () -> (player1, player2) -> displaySetScore(player1, player2, true).concat(displayGameScore(player1, player2));
//    public static Supplier<GameState> WON_A_GAME = () -> (player1, player2) -> displaySetScore(player1, player2, false);
//    public static Supplier<GameState> WON_A_SET = () -> (player1, player2) -> displaySetScore(player1, player2, true).concat(getLeadPlayerByGame(player1, player2).getName()).concat(" wins!");

    public static interface ScoreDisplayFunction extends BiFunction<Player, Player, String> {
    }

    public static ScoreDisplayFunction DEUCE = (player1, player2) -> displaySetScore(player1, player2, true).concat("Deuce");
    public static ScoreDisplayFunction DEUCE_ADVANTAGE = (player1, player2) -> displaySetScore(player1, player2, true).concat("Advantage ".concat(getLeadPlayerByGame(player1, player2).getName()));
    public static ScoreDisplayFunction IN_PROGRESS = (player1, player2) -> displaySetScore(player1, player2, true).concat(displayGameScore(player1, player2));
    public static ScoreDisplayFunction WON_A_GAME = (player1, player2) -> displaySetScore(player1, player2, false);
    public static ScoreDisplayFunction WON_A_SET = (player1, player2) -> displaySetScore(player1, player2, true).concat(getLeadPlayerByGame(player1, player2).getName()).concat(" wins!");

    public static Player getLeadPlayerByGame(Player player1, Player player2) {
        return player1.getGameScore() > player2.getGameScore() ? player1 : player2;
    }
    /**
     *
     * @param player1
     * @param player2
     * @param delimiter
     * @return
     */
    public static String displaySetScore(Player player1, Player player2, boolean delimiter) {
        StringBuilder setScoreStrBuilder = new StringBuilder();
        if (player1.getSetScore() > 0 || player2.getSetScore() > 0) { // Do NOT display Set Score 0 - 0
            setScoreStrBuilder.append(player1.getSetScore()).append(" - ").append(player2.getSetScore());
            if (delimiter) {
                setScoreStrBuilder.append(", ");
            }
        }
        return setScoreStrBuilder.toString();
    }

    /**
     *
     * @param player1
     * @param player2
     * @return
     */
    public static String displayGameScore(Player player1, Player player2) {
        StringBuilder gameScoreStrBuilder = new StringBuilder();
        gameScoreStrBuilder.append(player1.displayGameScore()).append(" - ").append(player2.displayGameScore());
        return gameScoreStrBuilder.toString();
    }

}

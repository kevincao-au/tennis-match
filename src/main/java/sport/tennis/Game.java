package sport.tennis;

import sport.ScoringService;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * @author Kevin Cao
 */
public class Game implements ScoringService {

    private Player playerA;
    private Player playerB;

    private AtomicReference<GameStatus> gameStatus = new AtomicReference<>(GameStatus.GAME_IN_PROGRESS_NO_DEUCE); // Game Status may be updated atomically

    private Supplier<GameState> currentGameState;


    public Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public void pointWonBy(final Player player) {
        if (gameStatus.get() == GameStatus.SET_WON_IN_DEUCE || gameStatus.get() == GameStatus.SET_WON_NO_DEUCE) {
            throw new IllegalStateException("Set is finished!");
        }

        if (player == playerA) {
            playerA.winPoint();
        } else if (player == playerB) {
            playerB.winPoint();
        } else {
            throw new IllegalArgumentException("Invalid Player!");
        }

        applyStatus();
    }

    /**
     *
     */
    private void applyStatus() {
        if (playerA.getGameScore() >= 3 && playerB.getGameScore() >= 3) {
            if (Math.abs(playerB.getGameScore() - playerA.getGameScore()) >= 2) {
                getLeadPlayerByGame().winGame();
                if (getLeadPlayerByGame().getSetScore() == 6) {
                    // Set Won in Deuce
                    gameStatus.set(GameStatus.SET_WON_IN_DEUCE);
                    currentGameState = GameStates.WON_A_SET;
                } else {
                    resetGameScore();
                    // Game Won in Deuce
                    gameStatus.set(GameStatus.GAME_WON_IN_DEUCE);
                    currentGameState = GameStates.WON_A_GAME;
                }
            } else if (playerA.getGameScore() == playerB.getGameScore()) {
                // Game in Deuce
                gameStatus.set(GameStatus.GAME_IN_DEUCE);
                currentGameState = GameStates.DEUCE;
            } else {
                // Game in Deuce (Advantage)
                gameStatus.set(GameStatus.GAME_IN_DEUCE_ADVANTAGE);
                currentGameState = GameStates.DEUCE_ADVANTAGE;
            }
        } else {
            if (playerA.getGameScore() > 3 || playerB.getGameScore() > 3) {
                getLeadPlayerByGame().winGame();
                resetGameScore();
                if (getLeadPlayerByGame().getSetScore() == 6) {
                    // Set Won No Deuce
                    gameStatus.set(GameStatus.SET_WON_NO_DEUCE);
                    currentGameState = GameStates.WON_A_SET;
                } else {
                    // Game Won No Deuce
                    gameStatus.set(GameStatus.GAME_WON_NO_DEUCE);
                    currentGameState = GameStates.WON_A_GAME;
                }
            } else {
                // Game In Progress No Deuce
                gameStatus.set(GameStatus.GAME_IN_PROGRESS_NO_DEUCE);
                currentGameState = GameStates.IN_PROGRESS;
            }
        }
    }



    @Override
    public String displayScore() {
        if (currentGameState.get() == null) {
            return "No Game Yet!";
        }
        return currentGameState.get().displayScore(playerA, playerB);
    }

    @Override
    public Player getLeadPlayerByGame() {
        return GameStates.getLeadPlayerByGame(playerA, playerB);
    }

    @Override
    public Player getLeadPlayerBySet() {
        return playerA.getSetScore() > playerB.getSetScore() ? playerA : playerB;
    }

    @Override
    public void resetGameScore() {
        playerA.resetGameScore();
        playerB.resetGameScore();
    }


}

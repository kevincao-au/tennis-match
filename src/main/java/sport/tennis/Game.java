package sport.tennis;

import sport.ScoringService;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Kevin Cao
 */
public class Game implements ScoringService {

    private Player playerA;
    private Player playerB;
    private AtomicReference<GameStatus> gameStatus = new AtomicReference<>(GameStatus.GAME_IN_PROGRESS_NO_DEUCE); // Game Status may be updated atomically

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
                } else {
                    resetGameScore();
                    // Game Won in Deuce
                    gameStatus.set(GameStatus.GAME_WON_IN_DEUCE);
                }
            } else if (playerA.getGameScore() == playerB.getGameScore()) {
                // Game in Deuce
                gameStatus.set(GameStatus.GAME_IN_DEUCE);
            } else {
                // Game in Deuce (Advantage)
                gameStatus.set(GameStatus.GAME_IN_DEUCE_ADVANTAGE);
            }
        } else {
            if (playerA.getGameScore() > 3 || playerB.getGameScore() > 3) {
                getLeadPlayerByGame().winGame();
                resetGameScore();
                if (getLeadPlayerByGame().getSetScore() == 6) {
                    // Set Won No Deuce
                    gameStatus.set(GameStatus.SET_WON_NO_DEUCE);
                } else {
                    // Game Won No Deuce
                    gameStatus.set(GameStatus.GAME_WON_NO_DEUCE);
                }
            } else {
                // Game In Progress No Deuce
                gameStatus.set(GameStatus.GAME_IN_PROGRESS_NO_DEUCE);
            }
        }
    }

    /**
     *
     * @param delimiter
     * @return
     */
    protected String displaySetScore(boolean delimiter) {
        StringBuilder setScoreStrBuilder = new StringBuilder();
        if (playerA.getSetScore() > 0 || playerB.getSetScore() > 0) { // Do NOT display Set Score 0 - 0
            setScoreStrBuilder.append(playerA.getSetScore()).append(" - ").append(playerB.getSetScore());
            if (delimiter) {
                setScoreStrBuilder.append(", ");
            }
        }
        return setScoreStrBuilder.toString();
    }

    /**
     *
     * @return
     */
    protected String displayGameScore() {
        StringBuilder gameScoreStrBuilder = new StringBuilder();
        gameScoreStrBuilder.append(playerA.displayGameScore()).append(" - ").append(playerB.displayGameScore());
        return gameScoreStrBuilder.toString();
    }

    @Override
    public String displayScore() {
        switch (gameStatus.get()) {
            case GAME_IN_PROGRESS_NO_DEUCE:
                return displaySetScore(true).concat(displayGameScore());
            case GAME_WON_NO_DEUCE:
            case GAME_WON_IN_DEUCE:
                return displaySetScore(false);
            case SET_WON_NO_DEUCE:
            case SET_WON_IN_DEUCE:
                return displaySetScore(true).concat(getLeadPlayerByGame().getName()).concat(" wins!");
            case GAME_IN_DEUCE:
                return displaySetScore(true).concat("Deuce");
            case GAME_IN_DEUCE_ADVANTAGE:
                return displaySetScore(true).concat("Advantage ").concat(getLeadPlayerByGame().getName());
        }
        return null;
    }

    @Override
    public Player getLeadPlayerByGame() {
        return playerA.getGameScore() > playerB.getGameScore() ? playerA : playerB;
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

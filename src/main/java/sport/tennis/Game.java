package sport.tennis;

import sport.ScoringService;

public class Game implements ScoringService {

    private Player playerA;
    private Player playerB;

    public Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public void pointWonBy(final Player player) {
        if (player == playerA) {
            playerA.winPoint();
        } else if (player == playerB) {
            playerB.winPoint();
        } else {
            throw new IllegalArgumentException("Invalid Player!");
        }

    }

    @Override
    public String displayScore() {
        if (playerA.getGameScore() >= 3 && playerB.getGameScore() >= 3) {
            if (Math.abs(playerB.getGameScore() - playerA.getGameScore()) >= 2) {
                getLeadPlayerByGame().winGame();
                if (getLeadPlayerByGame().getSetScore() == 6) {
                    return String.valueOf(playerA.getSetScore()).concat(" - ").concat(String.valueOf(playerB.getSetScore()))
                            .concat(", ").concat(getLeadPlayerByGame().getName()).concat(" wins!");
                } else {
                    resetGameScore();
                    return String.valueOf(playerA.getSetScore()).concat(" - ").concat(String.valueOf(playerB.getSetScore()));
                }
            } else if (playerA.getGameScore() == playerB.getGameScore()) {
                StringBuilder scoreStrBuilder = new StringBuilder();
                if (playerA.getSetScore() > 0 || playerB.getSetScore() > 0) {
                    scoreStrBuilder.append(playerA.getSetScore()).append(" - ").append(playerB.getSetScore()).append(", ");
                }
                scoreStrBuilder.append("Deuce");
                return scoreStrBuilder.toString();
            } else {
                StringBuilder scoreStrBuilder = new StringBuilder();
                if (playerA.getSetScore() > 0 || playerB.getSetScore() > 0) {
                    scoreStrBuilder.append(playerA.getSetScore()).append(" - ").append(playerB.getSetScore()).append(", ");
                }
                scoreStrBuilder.append("Advantage ").append(getLeadPlayerByGame().getName());
                return scoreStrBuilder.toString();
            }
        } else {
            if (playerA.getGameScore() > 3 || playerB.getGameScore() > 3) {
                getLeadPlayerByGame().winGame();
                resetGameScore();
                if (getLeadPlayerByGame().getSetScore() == 6) {
                    return String.valueOf(playerA.getSetScore()).concat(" - ").concat(String.valueOf(playerB.getSetScore()))
                            .concat(", ").concat(getLeadPlayerByGame().getName()).concat(" wins!");
                } else {
                    return String.valueOf(playerA.getSetScore()).concat(" - ").concat(String.valueOf(playerB.getSetScore()));
                }

            } else {
                StringBuilder scoreStrBuilder = new StringBuilder();
                if (playerA.getSetScore() > 0 || playerB.getSetScore() > 0) {
                    scoreStrBuilder.append(playerA.getSetScore()).append(" - ").append(playerB.getSetScore()).append(", ");
                }
                scoreStrBuilder.append(playerA.displayGameScore()).append(" - ").append(playerB.displayGameScore());
                return scoreStrBuilder.toString();
            }
        }
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

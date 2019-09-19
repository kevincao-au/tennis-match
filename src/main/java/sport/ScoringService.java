package sport;

import sport.tennis.Player;

public interface ScoringService {

    void pointWonBy(final Player player);

    String displayScore();

    Player getLeadPlayerByGame();

    Player getLeadPlayerBySet();

    void resetGameScore();
}

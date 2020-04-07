/**
 * todo: The class Game
 *
 * @author  Michele Di Bendetto
 * @version 1.0
 */
public class Game {
    RoundResult[] roundHistory;
    int roundConter = 0;

    public Game() {
        // SIZE = 8 is able to cover the maximum score of 12
        roundHistory = new RoundResult[8];
    }

    public void play(Player realPlayer, Player virtualPlayer) {
        final int POINTS = 3;
        final int EXTRA_POINTS = 2;
        String result = "";
        boolean hasRealPlayerWon = false;
        int sum = realPlayer.fingers + virtualPlayer.fingers;

        if (sum % 2 == 0) {
            result = "EVEN";
        } else {
            result = "ODD";
        }

        // start process score
        if (realPlayer.type == result) {
            realPlayer.points += POINTS;
            hasRealPlayerWon = true;
        } else {
            virtualPlayer.points += POINTS;
        }

        // process extra points
        // NB: realPlayer.fingers == virtualPlayer.fingers
        // then: NOBODY GETS EXTRA POINTS
        if (realPlayer.fingers > virtualPlayer.fingers) {
            realPlayer.extraPoints += EXTRA_POINTS;
        } else if (realPlayer.fingers < virtualPlayer.fingers) {
            virtualPlayer.extraPoints += EXTRA_POINTS;
        }
        // end: process score

        updateHistory(
            realPlayer.fingers,
            virtualPlayer.fingers,
            hasRealPlayerWon
        );
    }

    private void updateHistory(
        int realPlayerFingers,
        int virtualPlayerFingers,
        boolean hasRealPlayerWon
    ) {
        RoundResult round = new RoundResult();
        round.realPlayerFingers = realPlayerFingers;
        round.virtualPlayerFingers = virtualPlayerFingers;
        round.hasRealPlayerWon = hasRealPlayerWon;

        roundHistory[roundConter] = round;
        roundConter++;
    }

    public boolean hasGotMaxScore(Player realPlayer, Player virtualPlayer) {
        final int MAX_SCORE = 12;

        if (
            virtualPlayer.getScore() >= MAX_SCORE ||
            realPlayer.getScore() >= MAX_SCORE
        ) {
            return true;
        }

        return false;
    }
}

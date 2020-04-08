/**
 * todo: The class Game
 *
 * @author  Michele Di Bendetto
 * @version 1.0* @version 1.0
 */
public class Game {
    public RoundResult[] roundHistory;
    private int roundConter = 0;

    public Game() {
        // SIZE = 8 is able to cover the maximum score of 12
        roundHistory = new RoundResult[8];
    }

    public int getRoundCounter() {
        return roundConter;
    }

    public void play(Player realPlayer, Player virtualPlayer) {
        final int POINTS = 3;
        final int EXTRA_POINTS = 2;
        String result = "";
        boolean hasRealPlayerWon = false;
        int sum = realPlayer.fingers + virtualPlayer.fingers;

        if (isEven(sum)) {
            result = "EVEN";
        } else {
            result = "ODD";
        }

        // start process score
        if (realPlayer.type.equals(result)) {
            realPlayer.points += POINTS;
            hasRealPlayerWon = true;
        } else {
            virtualPlayer.points += POINTS;
        }

        // process extra points
        // NB: there is no ELSE,
        // if: realPlayer.fingers == virtualPlayer.fingers
        // then: NOBODY GETS EXTRA POINTS
        if (realPlayer.fingers > virtualPlayer.fingers) {
            realPlayer.extraPoints += EXTRA_POINTS;
        } else if (realPlayer.fingers < virtualPlayer.fingers) {
            virtualPlayer.extraPoints += EXTRA_POINTS;
        }
        // end: process score

        // update History info
        updateHistory(realPlayer, virtualPlayer, hasRealPlayerWon);
    }

    private boolean isEven(int value) {
        return (value % 2 == 0);
    }

    private void updateHistory(
        Player realPlayer,
        Player virtualPlayer,
        boolean hasRealPlayerWon
    ) {
        RoundResult round = new RoundResult();
        // who won the round
        round.hasRealPlayerWon = hasRealPlayerWon;
        // Human player info
        round.realPlayerFingers = realPlayer.fingers;
        if (isEven(realPlayer.fingers)) {
            round.realUserTotalEven = 1;
        } else {
            round.realUserTotalOdd = 1;
        }
        // PC player info
        round.virtualPlayerFingers = virtualPlayer.fingers;
        if (isEven(virtualPlayer.fingers)) {
            round.virtualUserTotalEven = 1;
        } else {
            round.virtualUserTotalOdd = 1;
        }
        // store the info
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

    public RoundResult getCurrentRound() {
        return roundHistory[roundConter - 1];
    }
}

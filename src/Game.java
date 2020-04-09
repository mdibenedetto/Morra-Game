/**
 * The class Game is in charge to process the input,
 * compute the score and update the round history.
 *
 * @author  Michele Di Bendetto
 * @version 1.0
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

    /**
     * The play method gets as params the objects humanPlayer and virtualPlayer,
     * it process the number of fingers and set the round history.     *
     */
    public void play(Player humanPlayer, Player virtualPlayer) {
        final int POINTS = 3;
        final int EXTRA_POINTS = 2;

        String oddOrEven = "";
        boolean hasHumanPlayerWon = false;
        int sum = humanPlayer.fingers + virtualPlayer.fingers;

        if (isEven(sum)) {
            oddOrEven = "EVEN";
        } else {
            oddOrEven = "ODD";
        }

        // start process score
        if (humanPlayer.oddOrEven.equals(oddOrEven)) {
            humanPlayer.points += POINTS;
            hasHumanPlayerWon = true;
        } else {
            virtualPlayer.points += POINTS;
        }

        // process extra points
        // NB: there is no ELSE,
        // if: humanPlayer.fingers == virtualPlayer.fingers
        // then: NOBODY GETS EXTRA POINTS
        if (humanPlayer.fingers > virtualPlayer.fingers) {
            humanPlayer.extraPoints += EXTRA_POINTS;
        } else if (humanPlayer.fingers < virtualPlayer.fingers) {
            virtualPlayer.extraPoints += EXTRA_POINTS;
        }
        // end: process score

        // update History info
        updateHistory(humanPlayer, virtualPlayer, hasHumanPlayerWon, oddOrEven);
    }

    private boolean isEven(int value) {
        return (value % 2 == 0);
    }

    private void updateHistory(
        Player humanPlayer,
        Player virtualPlayer,
        boolean hasHumanPlayerWon,
        String oddOrEven
    ) {
        RoundResult round = new RoundResult();
        // is this round sum odd or even
        round.oddOrEven = oddOrEven;
        // who won the round
        round.hasHumanPlayerWon = hasHumanPlayerWon;
        // Human player info
        round.humanPlayerFingers = humanPlayer.fingers;
        if (isEven(humanPlayer.fingers)) {
            round.humanPlayerTotalEven = 1;
        } else {
            round.humanPlayerTotalOdd = 1;
        }
        // PC player info
        round.virtualPlayerFingers = virtualPlayer.fingers;
        if (isEven(virtualPlayer.fingers)) {
            round.virtualPlayerTotalEven = 1;
        } else {
            round.virtualPlayerTotalOdd = 1;
        }
        // store the info
        roundHistory[roundConter] = round;
        roundConter++;
    }

    public boolean hasGotMaxScore(Player humanPlayer, Player virtualPlayer) {
        final int MAX_SCORE = 12;

        if (
            virtualPlayer.getScore() >= MAX_SCORE ||
            humanPlayer.getScore() >= MAX_SCORE
        ) {
            return true;
        }

        return false;
    }

    public RoundResult getCurrentRound() {
        return roundHistory[roundConter - 1];
    }
}

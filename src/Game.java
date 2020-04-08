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

    public void play(Player humanPlayer, Player virtualPlayer) {
        final int POINTS = 3;
        final int EXTRA_POINTS = 2;
        String result = "";
        boolean hasHumanPlayerWon = false;
        int sum = humanPlayer.fingers + virtualPlayer.fingers;

        if (isEven(sum)) {
            result = "EVEN";
        } else {
            result = "ODD";
        }

        // start process score
        if (humanPlayer.type.equals(result)) {
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
        updateHistory(humanPlayer, virtualPlayer, hasHumanPlayerWon);
    }

    private boolean isEven(int value) {
        return (value % 2 == 0);
    }

    private void updateHistory(
        Player humanPlayer,
        Player virtualPlayer,
        boolean hasHumanPlayerWon
    ) {
        RoundResult round = new RoundResult();
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

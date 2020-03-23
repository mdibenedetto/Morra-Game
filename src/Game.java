public class Game {
    RoundResult[] roundHistory;
    int roundConter = 0;

    public Game() {
        roundHistory = new RoundResult[10];
    }

    public void play(PlayerBase realPlayer, PlayerBase virtualPlayer) {
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
            realPlayer.points += 3;
            hasRealPlayerWon = true;
        } else {
            virtualPlayer.points += 3;
        }
        // TODO: Check rule for extra points
        if (realPlayer.fingers > virtualPlayer.fingers) {
            realPlayer.extraPoints += 2;
        } else {
            virtualPlayer.extraPoints += 2;
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
}

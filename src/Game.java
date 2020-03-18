public class Game {
    RoundResult[] history;

    public Game() {}

    public String play(int realPlayerFingers, int virtualPlayerFingers) {
        String result = "";
        int sum = realPlayerFingers + virtualPlayerFingers;
        if (sum % 2 == 0) {
            result = "EVEN";
        } else {
            result = "ODD";
        }

        return result;
    }
}

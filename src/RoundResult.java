/**
 * The class RoundResult store the attribute
 * related to history of a single game
 * - who the winner is,
 * - history of the numbers of fingers shown by
 * both the user and the computer per round.
 *
 * @author  Raminta Kairyte
 * @version 1.0
 */
public class RoundResult {
    public String oddOrEven;
    public boolean hasHumanPlayerWon;

    public int humanPlayerFingers;
    public int virtualPlayerFingers;

    public int humanPlayerTotalEven;
    public int virtualPlayerTotalEven;

    public int humanPlayerTotalOdd;
    public int virtualPlayerTotalOdd;
}

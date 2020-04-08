/**
    The history shows, for each game:
    - the number of rounds won and lost by the human player
    - how many even and odd numbers have been chosen by each player
    - the extra points received by each player per game.  
*/

/**
 * todo: The class GameResult
 *
 * @author  Raminta Kairyte
 * @version 1.0
 */
public class GameResult {
    public int wonRounds;
    public int lostRounds;

    public int humanPlayerTotalOdd;
    public int virtualPlayerTotalEven;

    public int humanPlayerTotalEven;
    public int virtualPlayerTotalOdd;

    public int humanPlayerExtaScore;
    public int virtualPlayerExtaScore;
}

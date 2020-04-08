/**
 * The class Player identify a player which can be either an user or the PC
 *
 * @author  Hsiu Hui Huang
 * @version 1.0
 */
public class Player {
    public String name;
    public String oddOrEven; //"ODD"|"EVEN" ;
    public int fingers;

    public int points = 0;
    public int extraPoints = 0;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        name = "Player";
    }

    public int getScore() {
        return points + extraPoints;
    }

    /**
     * This setter method is used to set number
     * of fingers that the user want to show
     * @param fingers
     * @return Nothing.
     */
    public void setFingers(int fingers) {
        //the variable fingers we declare in Class Player
        this.fingers = fingers;
    }

    public void reset() {
        this.fingers = 0;
        this.points = 0;
        this.extraPoints = 0;
        this.oddOrEven = "";
    }
}

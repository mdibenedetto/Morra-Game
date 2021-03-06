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

    /**
     * This is the constructor sets the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * This is the constructor sets a default name for the player
     */
    public Player() {
        name = "Player-X";
    }

    public int getScore() {
        return points + extraPoints;
    }

    /**
     * This setter method is used to set number
     * of fingers that the user want to show
     *
     */
    public void setFingers(int fingers) {
        this.fingers = fingers;
    }

    /**
     * This method reset all class members
     *
     */
    public void reset() {
        this.fingers = 0;
        this.points = 0;
        this.extraPoints = 0;
        this.oddOrEven = "";
    }
}

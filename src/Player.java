/**
 * The class Player identify a player which can be either an user or the PC
 *
 * @author  Hsiu Hui Huang
 * @version 1.0
 */
public class Player {
    public String name; // optional
    public String type; //"ODD"|"EVEN" ;
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
}

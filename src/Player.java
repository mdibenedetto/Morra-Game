/**
 * The class Player identify a player which can be either an user or the PC
 *
 * @author  Hsiu Hui Huang
 * @version 1.0
 */
public class Player {
    String name; // optional
    String type; //"ODD"|"EVEN" ;
    int fingers;

    int points = 0;
    int extraPoints = 0;

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

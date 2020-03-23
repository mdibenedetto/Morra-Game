import java.util.Random;

// Nydia
public class VirtualPlayer extends PlayerBase {

    public VirtualPlayer() {
        name = "PC-Virtual-User";
    }

    // todo: describe what it does
    public void setRandomFingers() {
        int low = 1, high = 10;

        Random rnd = new Random();
        int randomFingers = rnd.nextInt(high) + low;

        this.fingers = randomFingers;
    }
}

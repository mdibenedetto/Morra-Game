import java.util.Random;

public class VirtualPlayer extends PlayerBase {

    public void setRandomFingers() {
        int low = 1, high = 10;

        Random rnd = new Random();
        int randomFingers = rnd.nextInt(high) + low;

        this.fingers = randomFingers;
    }
}

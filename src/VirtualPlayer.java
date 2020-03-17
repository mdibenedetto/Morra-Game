import java.util.Random;

public class VirtualPlayer extends PlayerBase {

    public int setRandomFingers() {
        int low = 1, high = 10;

        Random rnd = new Random();
        int res = rnd.nextInt(high) + low;
        return res;
    }
}

/**
 * The class MorraGameApp is the entry point of the programm
 *
 * @author  Michele Di Bendetto
 * @version 1.0
 */
public class MorraGameApp {

    public static void main(String[] args) {
        System.out.println("MorraGameApp");
        GameManager gm = new GameManager();
        gm.startup();
    }
}

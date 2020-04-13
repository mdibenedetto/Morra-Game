/**
 * The class MorraGameApp is the entry point of the program
 *
 * @author  Michele Di Bendetto
 * @version 1.0
 */
public class MorraGameApp {

    /**
     * The method is the entry point of the programm
     * It starts the game by using the instance method startup()
     * of class GameManager
     */
    public static void main(String[] args) {
        System.out.println("MorraGameApp");
        GameManager gm = new GameManager();
        gm.startup();
    }
}

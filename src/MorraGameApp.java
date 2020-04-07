/**
 * todo: The class MorraGameApp
 *
 * @author  Michele Di Bendetto
 * @version 1.0
 */
public class MorraGameApp {

    public static void main(String[] args) {
        clearScreen();
        System.out.println("MorraGameApp");
        GameManager gm = new GameManager();
        gm.startup();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

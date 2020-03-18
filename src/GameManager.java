import java.util.Scanner;

public class GameManager {
    Scanner sc = null;
    // PC
    VirtualPlayer virtualPlayer;
    //
    RealPlayer realPlayer;
    Game game;
    GameResult[] history;

    public GameManager() {
        sc = new Scanner(System.in);
        game = new Game();
    }

    public void startup() {
        displayMenu();
        setPlayers();

        // WHILE
        boolean keepPlay = true;
        while (keepPlay) {
            startGame();

            // ask the user if wants to keep playings, set keepPlay= true or false

            // debug
            keepPlay = false;
        }
        // END WHILE

        displayGameHistory();
    }

    private void displayMenu() {
        println("MENU user");
    }

    private void setPlayers() {
        println("Sete Player properties (name, ODD / EVEN)");
        // PC
        virtualPlayer = new VirtualPlayer();
        virtualPlayer.type = "EVEN";
        virtualPlayer.name = "Tanos";
        // set scanner
        realPlayer = new RealPlayer();
        realPlayer.name = "Black Widow";
        realPlayer.type = "ODD";
    }

    private void startGame() {
        println("START GAME");

        boolean keepPlay = true;

        String result = "";

        while (keepPlay) {
            // TODO: this is just a test: you need to use Scanner
            realPlayer.setFingers(10);
            virtualPlayer.setRandomFingers();

            result = game.play(realPlayer.fingers, virtualPlayer.fingers);

            if (realPlayer.type == result) {
                realPlayer.score += 3;
            } else {
                virtualPlayer.score += 3;
            }
            // TODO: extra points need to be implemented

            // a game finishes based on the first one who makes 12 points
            if (virtualPlayer.score == 12 || realPlayer.score == 12) {
                keepPlay = false;
            }
        }

        displayRoundHistory();
    }

    private void displayRoundHistory() {
        println("Round History");

        // test
        if (realPlayer.score == 12) {
            println("RealPlayer  is the winner");
        } else {
            println("VirtuaPlayer  is the winner");
        }
    }

    private void displayGameHistory() {
        println("Game History");
    }

    private void println(String msg) {
        System.out.println(msg);
    }
}

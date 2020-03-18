import java.util.Scanner;

public class GameManager {
    Scanner sc = null;
    // PC
    VirtualPlayer virtualPlayer;
    //
    RealPlayer realPlayer;
    Game game;
    GameResult[] gameHistory;

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
        println("Set Player properties (name, ODD / EVEN)");
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
                realPlayer.points += 3;
            } else {
                virtualPlayer.points += 3;
            }
            // TODO: Check rule for extra points
            if (realPlayer.fingers > virtualPlayer.fingers) {
                realPlayer.extraPoints += 2;
            }

            // a game finishes based on the first one who makes 12 points
            if (virtualPlayer.getScore() == 12 || realPlayer.getScore() == 12) {
                keepPlay = false;
            }
        }

        displayRoundHistory();
    }

    private void displayRoundHistory() {
        println("Round History");

        // test
        if (realPlayer.getScore() == 12) {
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

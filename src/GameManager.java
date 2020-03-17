import java.util.Scanner;

public class GameManager {
    Scanner sc = null;

    VirtualPlayer virtualPlayer;
    UserPlayer userPlayer;
    Game game;
    GameResult[] history;

    public GameManager() {
        sc = new Scanner(System.in);
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
    }

    private void startGame() {
        println("START GAME");

        int score = 0;

        do {
            displayRoundHistory();
            // a game finishes based on the first one who makes 12 points
            // debug
            score++;
        } while (score == 0);
    }

    private void displayRoundHistory() {
        println("Round History");
    }

    private void displayGameHistory() {
        println("Game History");
    }

    private void println(String msg) {
        System.out.println(msg);
    }
}

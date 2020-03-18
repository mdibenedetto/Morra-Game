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
        // PC
        virtualPlayer = new VirtualPlayer();

        // set scanner
        userPlayer = new UserPlayer();
    }

    private void startGame() {
        println("START GAME");

        boolean keepPlay = true;

        while (keepPlay) {
            // a game finishes based on the first one who makes 12 points

            // this is just a test: remove this line when the program is ready
            this.userPlayer.score = 12;

            if (this.virtualPlayer.score == 12 || this.userPlayer.score == 12) {
                keepPlay = false;
            }
        }

        displayRoundHistory();
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

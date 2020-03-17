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
        startGame();

        // into the while
        // while {
        displayRoundHistory();
        // }

        displayGameHistory();
    }

    private void displayMenu() {
        println("MENU");
    }

    private void setPlayers() {}

    private void startGame() {}

    private void displayRoundHistory() {
        println("Round History");
    }

    private void displayGameHistory() {
        println("Game tory");
    }

    private void println(String msg) {
        System.out.println(msg);
    }
}

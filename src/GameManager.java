import java.util.Arrays;
import java.util.Scanner;

public class GameManager {
    Scanner sc = null;
    // PC
    VirtualPlayer virtualPlayer;
    //
    RealPlayer realPlayer;
    GameResult[] gameHistory;
    int historyCounter = 0;

    public GameManager() {
        sc = new Scanner(System.in);

        gameHistory = new GameResult[100];
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
            keepPlay = wantStillPlay();
        }
        // END WHILE

        displayGameHistory();
    }

    // Rami: show a menu to describe what the user has to do
    private void displayMenu() {
        println("MENU user");
    }

    // Nydia: Use Scanner to set the user
    private void setPlayers() {
        println("Set Player properties (name, ODD / EVEN)");
        // PC
        virtualPlayer = new VirtualPlayer();
        virtualPlayer.type = "EVEN";
        // set scanner
        realPlayer = new RealPlayer();
        realPlayer.name = "Black Widow";
        realPlayer.type = "ODD";
    }

    // Nydia: number between 1 and 10
    // Use Scanner to set the number of fingers the user want to use
    private int getRealUserFingers() {
        // TODO: this is just a test: you need to use Scanner
        return 10;
    }

    // Rami:
    // Use Scanner
    private boolean wantStillPlay() {
        boolean userResponse = false;

        return userResponse;
    }

    private void startGame() {
        println("START GAME");

        boolean keepPlay = true;
        int realPlayerFinger = 0;
        Game game = new Game();

        while (keepPlay) {
            realPlayerFinger = getRealUserFingers();
            realPlayer.setFingers(realPlayerFinger);
            virtualPlayer.setRandomFingers();

            game.play(realPlayer, virtualPlayer);

            // a game finishes when one of the user reach 12 points
            if (virtualPlayer.getScore() >= 12 || realPlayer.getScore() >= 12) {
                keepPlay = false;
            }
        }

        updateGameHistory();
        displayRoundHistory(game.roundHistory);
    }

    private void updateGameHistory() {
        if (historyCounter > gameHistory.length) {
            gameHistory = Arrays.copyOf(gameHistory, gameHistory.length * 2);
        }

        GameResult gameResult = new GameResult();
        gameResult.wonRounds = 5;

        gameHistory[historyCounter] = gameResult;
        historyCounter++;
    }

    // Rami: display the game history
    private void displayGameHistory() {
        println("Game History");
    }

    // Rami: display the Round history
    private void displayRoundHistory(RoundResult[] roundHistory) {
        println("Round History");
        println("");

        // TODO T: test.. we need to show the real history
        if (realPlayer.getScore() == 12) {
            println("RealPlayer " + realPlayer.name + " is the winner");
        } else {
            println("VirtuaPlayer " + virtualPlayer.name + "  is the winner");
        }
        println("");
    }

    private void println(String msg) {
        System.out.println(msg);
    }
}

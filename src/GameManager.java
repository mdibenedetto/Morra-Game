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
            keepPlay = userWantStillPlay();
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
        virtualPlayer.name = "Tanos";
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

    // Nydia:
    private boolean userWantStillPlay() {
        boolean userResponse = false;

        return userResponse;
    }

    private void startGame() {
        println("START GAME");

        boolean keepPlay = true;
        String result = "";
        int realPlayerFinger = 0;

        while (keepPlay) {
            realPlayerFinger = getRealUserFingers();
            realPlayer.setFingers(realPlayerFinger);
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
            } else {
                virtualPlayer.extraPoints += 2;
            }

            // a game finishes when one of the user reach 12 points
            if (virtualPlayer.getScore() >= 12 || realPlayer.getScore() >= 12) {
                keepPlay = false;
            }
        }

        displayRoundHistory();
    }

    // Rami: display the Round history
    private void displayRoundHistory() {
        println("Round History");

        // test
        if (realPlayer.getScore() == 12) {
            println("RealPlayer  is the winner");
        } else {
            println("VirtuaPlayer  is the winner");
        }
    }

    // Rami: display the game history
    private void displayGameHistory() {
        println("Game History");
    }

    private void println(String msg) {
        System.out.println(msg);
    }
}

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * todo: The class GameManager
 *
 * @author  Michele Di Bendetto
 * @version 1.0
 */
public class GameManager {
    private Scanner sc = null;
    // PC
    private Player virtualPlayer;
    // Human Player
    private Player humanPlayer;
    private GameResult[] gameHistory;
    private int historyCounter = 0;
    private final String NEW_LINE = "\r\n";

    /**
     * This is the constructor where Class variable are initiaized
     *
     * @author  Michele Di Bendetto
     *
     */
    public GameManager() {
        sc = new Scanner(System.in);
        gameHistory = new GameResult[100];
    }

    /**
     * This method manages all life cycle of the game
     *
     * @author  Michele Di Bendetto
     *
     */
    public void startup() {
        displayMenu();
        setPlayers();
        displayWelcome();

        boolean keepPlay = true;
        while (keepPlay) {
            setPlayerAttributes();
            startGame();
            keepPlay = wantStillPlay();
        }
        display("I FINISHED");

        displayGameHistory();
    }

    /**
     * This method shows a menu to describe what the user has to do
     *
     * @author  Raminta Kairyte
     *
     */
    private void displayMenu() {
        String message = "";

        displayMessage(message);
    }

    /**
     * This method ....
     *
     * @author  Hsiu Hui Huang
     *
     */
    private void setPlayers() {
        // creat new objects
        virtualPlayer = new Player("Virtual Player");
        humanPlayer = new Player();
        displayInfoRequest("Enter your user name:");
        humanPlayer.name = sc.next();
    }

    /**
     * This method sets the attributes of the player needed to start the game,
     * The meaninful attributes are "name" of the player and "type (ODD / EVEN")
     *
     * @author  Hsiu Hui Huang
     *
     */
    private void setPlayerAttributes() {
        virtualPlayer.reset();
        humanPlayer.reset();

        displayMessage("Set Player properties (ODD / EVEN)");

        String oddOrEven = "";
        do {
            displayInfoRequest(
                (
                    "Enter 'ODD' if you want to play with odd numbers" +
                    NEW_LINE +
                    "  " +
                    "Enter 'EVEN' if you want to play with even numbers:"
                )
            );

            oddOrEven = sc.next();
            // convert lower case to upper case
            oddOrEven = oddOrEven.toUpperCase();
            humanPlayer.oddOrEven = oddOrEven;
            //if user choose odd, that means computer will be even
            if (oddOrEven == "ODD") {
                virtualPlayer.oddOrEven = "EVEN";
            } else {
                //otherwise if user choose even then computer is odd
                virtualPlayer.oddOrEven = "ODD";
            }
            // loop untill "type" is "ODD" or "EVEN"
        } while (!(oddOrEven.equals("ODD") || oddOrEven.equals("EVEN")));
    }

    /**
     * This method display a welcome message for the user,
     * showing the name and if it has been chosen ODD or EVEN
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayWelcome() {
        String message =
            ("Welcome " + humanPlayer.name + " to the game!") +
            (
                NEW_LINE +
                "  You have chosen to play with " +
                humanPlayer.oddOrEven +
                " numbers."
            ) +
            (NEW_LINE + "  Have fun!" + NEW_LINE);

        display("");
        displayMessage(message);
    }

    /**
     * This getter: the number of fingers the user want to show
     * @author  Hsiu Hui Huang
     *
     */
    private int getHumanPlayerFingers() {
        int userFingers = 0;
        // ask user to input how the number of fingers

        do {
            displayInfoRequest("Enter the number of fingers you want to show:");

            if (sc.hasNextInt()) {
                userFingers = sc.nextInt();
                //check if user enter the correct input(1 to 11)
                if (userFingers > 0 && userFingers < 11) {
                    displaySubMessage(
                        "You have entered \"" + userFingers + "\" fingers."
                    );
                } else {
                    displayWarning("Please enter number between 1 and 10!");
                }
            } else {
                displayWarning(
                    "Please enter a \"valid number\" between 1 and 10!!"
                );
                sc.next();
            }
        } while (userFingers <= 0 || userFingers > 10);

        return userFingers;
    }

    /**
     * this method generates a random number which will be used
     * to set the fingers for the playemate (PC)
     * @author  Hsiu Hui Huang
     *
     */
    public int getRandomFingers() {
        //instance variables: lowest number 1, and highest number 10
        int low = 1;
        int high = 10;
        int randomFingers;
        //creat type Ramdon
        Random myRand = new Random();
        //generate a random number between 1 and 10
        randomFingers = myRand.nextInt(high) + low;
        // display the number generated
        displaySubMessage(
            "Your playmate has entered \"" + randomFingers + "\" fingers."
        );
        //the variable from Player to take the value of randomFingers
        return randomFingers;
    }

    /**
     * This method asks the user if they want to keep playings
     *
     * @author Raminta Kairyte
     *
     */
    private boolean wantStillPlay() {
        boolean stillPlay = false;
        int userResponse = 0;
        do {
            displayMessage(
                "Do you want to continue the game? YES - press 1, NO - press 2"
            );

            if (sc.hasNextInt()) {
                userResponse = sc.nextInt();

                if (userResponse == 1) {
                    stillPlay = true;
                } else if (userResponse == 2) {
                    stillPlay = false;
                }
            } else {
                // keep going getting the next token (next user input)
                sc.next();
            }
        } while (userResponse < 0 || userResponse > 2);

        return stillPlay;
    }

    /**
     * This method runs a single game, it collects all inputs from the players,
     * process those inputs and shows who wins the round
     * and finally who have won the game
     *
     * @author  Michele Di Bendetto
     *
     */
    private void startGame() {
        displayMessage("START GAME");

        boolean keepPlay = true;
        int roundInfoCounter = 0;
        int humanPlayerFinger = 0;
        int virtualPlayerFinger = 0;

        Game game = new Game();

        while (keepPlay) {
            // diplay current round
            roundInfoCounter++;
            displayRoundCounter(roundInfoCounter);
            // get input from the user
            humanPlayerFinger = getHumanPlayerFingers();
            virtualPlayerFinger = getRandomFingers();
            // set attribute Fingers for the players
            humanPlayer.setFingers(humanPlayerFinger);
            virtualPlayer.setFingers(virtualPlayerFinger);
            // process information
            game.play(humanPlayer, virtualPlayer);

            displayInfoCurrentRound(game.getCurrentRound());
            // a game finishes when one of the user reach 12 points
            if (game.hasGotMaxScore(humanPlayer, virtualPlayer)) {
                keepPlay = false;
            }
        }

        displayMessage("END GAME");
        // Process info relative to the round history
        updateGameHistory(game, humanPlayer, virtualPlayer);
        // Output info relative to the round history
        displayRoundHistory(game);
    }

    /**
     * This method displays the number of points each player has,
     *  and whether the user or the computer won the round.
     * @author  Michele Di Bendetto
     * @param virtualPlayer2
     * @param humanPlayer2
     *
     */
    private void displayInfoCurrentRound(RoundResult currentRound) {
        display("");
        displayMessage("Your points are: " + humanPlayer.getScore());
        displaySubMessage("Playmate's points are: " + virtualPlayer.getScore());
        displaySubMessage("The sum is: " + currentRound.oddOrEven);
        displaySubMessage(
            "You << " +
            (currentRound.hasHumanPlayerWon ? "WON" : "LOST") +
            " >> this round."
        );
    }

    /**
     * This method display the current round number
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayRoundCounter(int roundInfoCounter) {
        display("");
        displayMessage("Round N." + roundInfoCounter);
    }

    /**
     * This method update Game History information by processing info in roundHistory
     *
     *
     * @author  Michele Di Bendetto
     *
     */
    private void updateGameHistory(
        Game game,
        Player humanPlayer,
        Player virtualPlayer
    ) {
        // in case the limit of the array lenght is reached,
        // the array will be resized the double of is original size
        if (historyCounter > gameHistory.length) {
            gameHistory = Arrays.copyOf(gameHistory, gameHistory.length * 2);
        }

        GameResult gameResult = new GameResult();

        RoundResult[] roundHistory = game.roundHistory;
        int roundCounter = game.getRoundCounter();

        for (int i = 0; i < roundCounter; i++) {
            RoundResult round = roundHistory[i];

            // - the number of rounds won and lost by the human player
            if (round.hasHumanPlayerWon) {
                gameResult.wonRounds++;
            } else {
                gameResult.lostRounds++;
            }
            // - how many even and odd numbers have been chosen by each player
            // Human player
            gameResult.humanPlayerTotalOdd += round.humanPlayerTotalOdd;
            gameResult.humanPlayerTotalEven += round.humanPlayerTotalEven;
            // PC player
            gameResult.virtualPlayerTotalEven += round.virtualPlayerTotalEven;
            gameResult.virtualPlayerTotalOdd += round.virtualPlayerTotalOdd;
        }

        // - the extra points received by each player per game.
        gameResult.humanPlayerExtaScore = humanPlayer.extraPoints;
        gameResult.virtualPlayerExtaScore = virtualPlayer.extraPoints;
        // store info in historymichel
        gameHistory[historyCounter] = gameResult;
        historyCounter++;
    }

    /**
     * This method display a history of all games played
     *
     * @author  Raminta Kairyte
     *
     */
    private void displayGameHistory() {
        display(NEW_LINE);
        displayMessage("Game History");

        for (int i = 0; i < historyCounter; i++) {
            GameResult gameInfo = gameHistory[i];

            display("");
            displayMessage("Game: " + (i + 1));

            displaySubMessage(
                "Total won rounds by the user: " + gameInfo.wonRounds
            );
            displaySubMessage(
                "Total lost rounds by the user: " + gameInfo.lostRounds
            );
            display("");

            displaySubMessage(
                "Total picked ODDS by the user: " + gameInfo.humanPlayerTotalOdd
            );
            displaySubMessage(
                "Total picked EVEN by the Virtual player: " +
                gameInfo.virtualPlayerTotalEven
            );
            display("");

            displaySubMessage(
                "Total picked EVEN by the user: " +
                gameInfo.humanPlayerTotalEven
            );
            displaySubMessage(
                "Total picked ODDS by the Virtual user: " +
                gameInfo.virtualPlayerTotalOdd
            );
            display("");

            displaySubMessage(
                "Total Extra scores by the  user: " +
                gameInfo.humanPlayerExtaScore
            );
            displaySubMessage(
                "Total Extra scores by the Virtual user: " +
                gameInfo.virtualPlayerExtaScore
            );
        }
    }

    /**
     * This method displays the Round history
     *
     * @author  Raminta Kairyte
     *
     */
    private void displayRoundHistory(Game game) {
        display(NEW_LINE);
        displayMessage("Round History");

        RoundResult[] roundHistory = game.roundHistory;
        int roundCounter = game.getRoundCounter();

        for (int i = 0; i < roundCounter; i++) {
            RoundResult round = roundHistory[i];
            display("");
            displayMessage("Round: " + (i + 1));

            if (round.hasHumanPlayerWon) {
                displaySubMessage(
                    "Human Player " + humanPlayer.name + " won the round"
                );
            } else {
                displaySubMessage(
                    "VirtualdisplaySubMessage Player " +
                    virtualPlayer.name +
                    "  is the winner"
                );
            }

            displaySubMessage(
                "Virtual player fingers picked: " + round.virtualPlayerFingers
            );
            displaySubMessage(
                "User fingers picked: " + round.humanPlayerFingers
            );
            displaySubMessage(
                "Total ODDS picked by the user " + round.humanPlayerTotalOdd
            );
            displaySubMessage(
                "Total picked EVEN by the user: " + round.humanPlayerTotalEven
            );
            displaySubMessage(
                "Total picked EVEN picked by the Virtual user: " +
                round.virtualPlayerTotalEven
            );
            displaySubMessage(
                "Total picked ODDS by the  user: " + round.virtualPlayerTotalOdd
            );
            displaySubMessage(
                "Total picked by the Virtual user: " +
                round.humanPlayerTotalEven
            );
        }
    }

    /**
     * This method is a helper method to display Info message
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayMessage(String msg) {
        display("> " + msg);
    }

    /**
     * This method is a helper method to display Info message with different indentation
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displaySubMessage(String msg) {
        display("  " + msg);
    }

    /**
     * This method is a helper method to display request of Info message
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayInfoRequest(String msg) {
        System.out.print(NEW_LINE + "?-INPUT: " + msg + " ");
    }

    /**
     * This method is a helper method to display Warning message
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayWarning(String msg) {
        display("!-WARNING: " + msg);
    }

    /**
     * This method is a helper method to display message
     *
     * @author  Michele Di Bendetto
     *
     */
    private void display(String msg) {
        System.out.println(msg);
    }
}

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The class GameManager is the core class of the app:
 * - it handles the entire lifecycle of the Game App,
 * - it collects the input from the user,
 * - it processes that input
 * - it displays the output
 *
 * @author  Michele Di Bendetto, Raminta Kairyte, Hsiu Hui Huang
 * @version 1.0
 */
public class GameManager {
    private Scanner sc = null;
    private Player virtualPlayer;
    private Player humanPlayer;
    private GameResult[] gameHistory;
    private int historyCounter = 0;
    private final String NEW_LINE = "\r\n";

    /**
     * This constructor set 2 instances:
     * - 'sc' to use class Scanner to collect input from the User
     * - gameHistory to store the game played information
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
        displayGameInfo();
        setPlayers();
        displayWelcome();

        boolean keepPlay = true;
        while (keepPlay) {
            setPlayerAttributes();
            startGame();
            keepPlay = wantStillPlay();
        }
        displayGameHistory();
    }

    /**
     * This method shows a menu to describe what the user has to do
     *
     * @author  Raminta Kairyte
     *
     */
    private void displayGameInfo() {
        String message =
            NEW_LINE +
            "****************************************************" +
            NEW_LINE +
            "         Morra Odds and Evens Variation" +
            NEW_LINE +
            "****************************************************" +
            NEW_LINE +
            "This variation of the game is a two-player game," +
            NEW_LINE +
            "where one player is going to be the \"Odds\" player " +
            NEW_LINE +
            "and the other player is the \"Evens\" player." +
            NEW_LINE +
            "****************************************************" +
            NEW_LINE +
            "                    Rules" +
            NEW_LINE +
            "****************************************************" +
            NEW_LINE +
            "In each round of the game, the players will" +
            NEW_LINE +
            "simultaneously hold out between 1 and 10 fingers." +
            NEW_LINE +
            "The winner of the round is decided based on the sum" +
            NEW_LINE +
            "of fingers shown by both players, namely" +
            NEW_LINE +
            "if the sum is:" +
            NEW_LINE +
            NEW_LINE +
            "a) an \"even\" number then the \"Evens\" player wins," +
            NEW_LINE +
            "b) an \"odd\" number then the \"Odds\" player wins." +
            NEW_LINE +
            "****************************************************" +
            NEW_LINE +
            "                    Score" +
            NEW_LINE +
            "****************************************************" +
            NEW_LINE +
            "The winner of the round receives three points." +
            NEW_LINE +
            "In addition, the player whose number of fingers" +
            NEW_LINE +
            "is closer to the sum, receives two extra points." +
            NEW_LINE +
            NEW_LINE +
            "The winner of the game is the first player" +
            NEW_LINE +
            "who accumulates 12 points." +
            NEW_LINE +
            "****************************************************";

        displayMessage(message);
    }

    /**
     * This method create the instance for both player and set the name
     *
     * @author  Hsiu Hui Huang
     *
     */
    private void setPlayers() {
        // creat new objects
        virtualPlayer = new Player("Baby Yoda");
        humanPlayer = new Player();
        displayInfoRequest(
            "Type your player name or enter to play as 'Player-1':"
        );

        humanPlayer.name = sc.nextLine();
        if (humanPlayer.name.isEmpty()) {
            humanPlayer.name = "Player-1";
        }
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

        displayMessage(
            "You have chosen to play with " +
            humanPlayer.oddOrEven +
            " numbers."
        );
        display("");
    }

    /**
     * This method display a welcome message for the user,
     * showing the name and if it has been chosen ODD or EVEN
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayWelcome() {
        String face =
            " +\"\"\"\"\"+ " +
            NEW_LINE +
            "[| o o |]" +
            NEW_LINE +
            " |  ^  | " +
            NEW_LINE +
            " | '-' | " +
            NEW_LINE +
            " +-----+ ";

        String message =
            (NEW_LINE + face + NEW_LINE) +
            "****************************************************" +
            NEW_LINE +
            ("Welcome << " + humanPlayer.name + " >> to \"Morra Game\"") +
            (
                NEW_LINE +
                "You are going to play against \"" +
                virtualPlayer.name +
                "\"."
            ) +
            (NEW_LINE + "The game is going to start!") +
            (NEW_LINE + "Have fun!" + NEW_LINE) +
            "****************************************************";

        display("");
        displayMessage(message);
    }

    /**
     * This method gets the number of fingers the user want to show
     *
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
     * to set the fingers for the playmate (PC)
     *
     * @author  Hsiu Hui Huang
     *
     */
    public int getRandomFingers() {
        //instance variables: lowest number 1, and highest number 10
        int low = 1;
        int high = 10;
        int randomFingers;
        //create type Ramdon
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
            displayMessage("Do you want to continue the game?");
            displaySubMessage("a) YES - press 1");
            displaySubMessage("b) NO - press 2");

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
     * This method runs a game,
     * it collects all inputs from the players,
     * it process those inputs,
     * it updates the Game History
     * it shows who won the round
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

        display("");
        displayMessage("END GAME");
        // Process info relative to the round history
        updateGameHistory(game, humanPlayer, virtualPlayer);
        // Output info relative to the round history
        displayRoundHistory(game);
    }

    /**
     * This method displays the number of points each player has got,
     * and whether the user or the computer won the round.
     *
     * @author  Michele Di Bendetto
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
     * This method displays the current round number
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

            // General
            display("");
            displayMessage("Game: " + (i + 1));

            displaySubMessage(
                "Total won rounds by the Human player: " + gameInfo.wonRounds
            );
            displaySubMessage(
                "Total lost rounds by the Human player: " + gameInfo.lostRounds
            );

            // TOTALS ODD - human player / Virtual player
            display("");

            displaySubMessage(
                "Total winnings of ODD by the Human player: " +
                gameInfo.humanPlayerTotalOdd
            );

            displaySubMessage(
                "Total winnings of ODD by the Virtual player: " +
                gameInfo.virtualPlayerTotalOdd
            );

            // TOTALS EVEN - human player / Virtual player
            display("");

            displaySubMessage(
                "Total winnings of EVEN by the Virtual player: " +
                gameInfo.virtualPlayerTotalEven
            );

            displaySubMessage(
                "Total winnings of EVEN by the Human player: " +
                gameInfo.humanPlayerTotalEven
            );

            // EXTRA SCORE - human player / Virtual player
            display("");

            displaySubMessage(
                "Total Extra scores by the Human player: " +
                gameInfo.humanPlayerExtaScore
            );
            displaySubMessage(
                "Total Extra scores by the Virtual player: " +
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
        display("");
        displayMessage("Round History");

        RoundResult[] roundHistory = game.roundHistory;
        int roundCounter = game.getRoundCounter();

        for (int i = 0; i < roundCounter; i++) {
            RoundResult round = roundHistory[i];
            display("");
            displayMessage("Round: " + (i + 1));
            display("");
            // general
            if (round.hasHumanPlayerWon) {
                displaySubMessage(
                    "Human player << " + humanPlayer.name + " >> won the round"
                );
            } else {
                displaySubMessage(
                    "Virtual player << " +
                    virtualPlayer.name +
                    " >> won the round"
                );
            }

            display("");
            displaySubMessage(
                "Virtual player's fingers picked: " + round.virtualPlayerFingers
            );
            displaySubMessage(
                "Human player's fingers picked: " + round.humanPlayerFingers
            );

            // Totals - ODD - human Player / Virtual Player
            display("");

            displaySubMessage(
                "Total winnings of ODD by the Human player: " +
                round.humanPlayerTotalOdd
            );

            displaySubMessage(
                "Total winnings of ODD by the Virtual Player: " +
                round.virtualPlayerTotalOdd
            );

            // Totals - EVEN - human Player / Virtual Player
            display("");

            displaySubMessage(
                "Total winnings of EVEN by the Human player: " +
                round.humanPlayerTotalEven
            );

            displaySubMessage(
                "Total winnings of EVEN by the  Virtual Player: " +
                round.virtualPlayerTotalEven
            );
        }
        display("");
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

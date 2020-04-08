/*
    << Program Requirements >>

    Develop an application to allow a user to play repeatedly the game “Morra Odds and Evens”
    with a computer.  

    1. At the beginning of each game the user will be prompted to choose whether
        he/she would like to be either the “Odds” or the “Even” player. 
    
    2. In each round of the game, the user must decide the number 
        of fingers to show (i.e. between 1 and 10). 

        2.1 Similarly, in each round of the game the computer will 
            randomly pick one number between 1 and 10. 
    
    3. In each round, the game displays the computer’s choice. 
    
    4. After each round the game displays the number of points each player has, 
        and whether the user or the computer won the round.

    5. A game finishes when one of the players accumulates 12 points. 
    
    6. At the end of a game, the game displays who the winner is, 
        and a history of the numbers of fingers shown by both the
        user and the computer per round.

    7. Once a game has finished the application asks the player 
        if he/she would like to play another game. 
    
    8. At the end of all games, display a history of games played.     
        The history shows, for each game:

        8.1 the number of rounds won and lost by the human player, 
        8.2 and how many even and odd numbers have been chosen by each player, 
        8.3 and the extra points received by each player per game. 

    All the history elements of the game should be coded using arrays. 
*/

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
    private void displayMenu() {
        String message = "";
        /*        
        **************************************************** 
                 Morra Odds and Evens Variation             
        ****************************************************
        This variation of the game is a two-player game, 
        where one player is going to be the “Odds” player 
        and the other player is the “Evens” player. 
        ****************************************************
                            Rules
        ****************************************************
        In each round of the game, the players will
        simultaneously hold out between 1 and 10 fingers. 
        
        The winner of the round is decided based on the sum 
        of fingers shown by both players, namely 
        if the sum is:

        a) an "even" number then the “Evens” player wins, 
        b) an "odd" number then the “Odds” player wins. 
        ****************************************************
                            Score
        ****************************************************
        The winner of the round receives three points. 
        In addition, the player whose number of fingers 
        is closer to the sum, receives two extra points.
        
        The winner of the game is the first player 
        who accumulates 12 points. 
        ****************************************************       
        */

        displayMessage(message);
    }

    /**
     * This method sets the attributes of the player needed to start the game,
     * The meaninful attributes are "name" of the player and "type (ODD / EVEN")
     *
     * @author  Hsiu Hui Huang
     *
     */
    private void setPlayers() {
        displayMessage("Set Player properties (name, ODD / EVEN)");
        // creat new objects
        virtualPlayer = new Player("Virtual Player");
        humanPlayer = new Player();

        displayInfoRequest("Enter your user name:");
        humanPlayer.name = sc.next();

        String type = "";
        do {
            displayInfoRequest(
                "Enter 'ODD' if you want to choose odd, otherwise please enter 'EVEN':"
            );

            type = sc.next();
            // convert lower case to upper case
            type = type.toUpperCase();
            humanPlayer.type = type;
            //if user choose odd, that means computer will be even
            if (type == "ODD") {
                virtualPlayer.type = "EVEN";
            } else {
                //otherwise if user choose even then computer is odd
                virtualPlayer.type = "ODD";
            }
            // loop untill "type" is "ODD" or "EVEN"
        } while (!(type.equals("ODD") || type.equals("EVEN")));
    }

    /**
     * This method display a welcome message for the user,
     * showing the name and if it has been chosen ODD or EVEN
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayWelcome() {
        final String NEW_LINE = "\r\n";
        String message =
            ("Welcome " + humanPlayer.name + " to the game!") +
            (
                NEW_LINE +
                "  You have chosen to play with " +
                humanPlayer.type +
                " numbers."
            ) +
            (NEW_LINE + "  Have fun!" + NEW_LINE);

        displayMessage(message);
    }

    /**
     * This getter: the number of fingers the user want to show
     * @author  Hsiu Hui Huang
     *
     */
    private int getHumanPlayerFingers() {
        int userFingers = 0;
        do {
            // ask user to input how the number of fingers
            displayInfoRequest("Enter the number of fingers you want to show:");

            if (sc.hasNextInt()) {
                userFingers = sc.nextInt();

                //check if user enter the correct input(1 to 11)
                if (userFingers > 0 && userFingers < 11) {
                    displayMessage(
                        "You have entered \"" + userFingers + "\" fingers."
                    );
                } else {
                    displayWarning(
                        "Please enter number of fingers between 1 and 10!"
                    );
                }
            } else {
                displayWarning(
                    "Please enter a \"valid number\" of fingers between 1 and 10!"
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
        displayMessage(
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
        // Use Scanner in this method
        boolean userResponse = false;

        return userResponse;
    }

    /**
     * todo: This method...
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

            displayRoundWinner(game.getCurrentRound());
            // a game finishes when one of the user reach 12 points
            if (game.hasGotMaxScore(humanPlayer, virtualPlayer)) {
                keepPlay = false;
            }
        }

        displayMessage("END GAME");
        // Process info relative to the round history
        updateGameHistory(game, humanPlayer, virtualPlayer);
        // Output info relative to the round history
        displayRoundHistory(game.roundHistory);
    }

    private void displayRoundWinner(RoundResult currentRound) {
        displayMessage(
            "You " +
            (currentRound.hashumanPlayerWon ? "won" : "loose") +
            " this round."
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
            if (round.hashumanPlayerWon) {
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
        displayMessage("Game History");

        for (int i = 0; i < gameHistory.length; i++) {
            GameResult gameInfo = gameHistory[i];
            // displayMessage() info from gameInfo
            // ex.  gameInfo.lostRounds
        }
    }

    /**
     * This method displays the Round history
     *
     * @author  Raminta Kairyte
     *
     */
    private void displayRoundHistory(RoundResult[] roundHistory) {
        displayMessage("Round History");
        displayMessage("");

        // TODO T: test.. we need to show the real history
        if (humanPlayer.getScore() == 12) {
            displayMessage(
                "humanPlayer " + humanPlayer.name + " is the winner"
            );
        } else {
            displayMessage(
                "VirtuaPlayer " + virtualPlayer.name + "  is the winner"
            );
        }
        displayMessage("");
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
     * This method is a helper method to display request of Info message
     *
     * @author  Michele Di Bendetto
     *
     */
    private void displayInfoRequest(String msg) {
        display("?-INFO: " + msg);
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

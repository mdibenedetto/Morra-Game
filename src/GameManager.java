/*
    << Program Description >>
    Morra Odds and Evens Variation
    
    This variation of the game is a two-player game, where one player is going to be the “Odds”
    player and the other player is the “Evens” player. In each round of the game, the players will
    simultaneously hold out between 1 and 10 fingers. 
    
    The winner of the round is decided based on the sum of fingers shown by both players, 
    namely if the sum is an even number then the “Evens” player wins, otherwise 
    if the sum is an odd number then the “Odds” player wins. 
    
    The winner of the round receives three points. In addition, the player whose number of 
    fingers is closer to the sum, receives two extra points.

    The winner of the game is the first player who accumulates 12 points. 
*/

/*
    << Requirements >>

    Develop an application to allow a user to play repeatedly the game “Morra Odds and Evens”
    with a computer.  

    1. At the beginning of each game the user will be prompted to choose whether
    he/she would like to be either the “Odds” or the “Even” player. 
    
    2. In each round of the game, the user must decide the number of fingers to show (i.e. between 1 and 10). 
    2.1 Similarly, in each round of the game the computer will randomly pick one number between 1 and 10. 
    
    3. In each round, the game displays the computer’s choice. 
    
    4. After each round the game displays the number of points each player has, 
    and whether the user or the computer won the round.

    5. A game finishes when one of the players accumulates 12 points. 
    
    6. At the end of a game, the game displays who the winner is, 
    and a history of the numbers of fingers shown by both the
    user and the computer per round.

    7. Once a game has finished the application asks the player if he/she would like to play another
    game. 
    
    8. At the end of all games, display a history of games played. 
    
    9. The history shows, for each game, the number of rounds won and lost by the human player, 
    and how many even and odd numbers have been chosen by each player, and the extra points 
    received by each player per game. 
    All the history elements of the game should be coded using arrays. 
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    Scanner sc = null;
    // PC
    Player virtualPlayer;
    //
    Player realPlayer;
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

        // At the end of all games, display a history of games played.
        displayGameHistory();
    }

    // Rami: show a menu to describe what the user has to do
    private void displayMenu() {
        println("MENU user");
    }

    /**
     * This method...
     *
     * @author  Nydia Huang
     * @version 1.0
     */
    private void setPlayers() {
        println("Set Player properties (name, ODD / EVEN)");
        // creat new objects
        virtualPlayer = new Player("Virtual Player");
        realPlayer = new Player();

        println("Enter your user name? ");
        realPlayer.name = sc.next();

        String type = "";

        do {
            println(
                "Enter 'ODD' if you want to choose odd, otherwise please enter 'EVEN'"
            );

            type = sc.next();
            // convert lower case to upper case
            type = type.toUpperCase();
            realPlayer.type = type;
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
     * This getter: the number of fingers the user want to show
     * @author  Nydia Huang
     * @version 1.0
     */
    private int getRealUserFingers() {
        int userFingers = 0;
        Scanner sc;
        do {
            // ask user to input how the number of fingers
            println("Enter the number of fingers you want to show");

            sc = new Scanner(System.in);

            if (sc.hasNextInt()) {
                userFingers = sc.nextInt();
                //check if user enter the correct input(1 to 11)
                if (userFingers > 0 && userFingers < 11) {
                    println("You have enter " + userFingers + " fingers.");
                } else {
                    println("Please enter number of fingers between 1 and 10");
                }
            }
        } while (userFingers <= 0 || userFingers > 10);

        sc.close();
        return userFingers;
    }

    /**
     * this method....
     * @author  Nydia Huang
     * @version 1.0
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
        //the variable from Player to take the value of randomFingers
        return randomFingers;
    }

    /**
     * This method...
     *
     * @author  Raminta
     * @version 1.0
     */
    // Use Scanner
    private boolean wantStillPlay() {
        boolean userResponse = false;

        return userResponse;
    }

    private void startGame() {
        println("START GAME");

        boolean keepPlay = true;
        int realPlayerFinger = 0;
        int virtualPlayerFinger = 0;
        Game game = new Game();

        while (keepPlay) {
            realPlayerFinger = getRealUserFingers();
            virtualPlayerFinger = getRandomFingers();

            realPlayer.setFingers(realPlayerFinger);
            virtualPlayer.setFingers(virtualPlayerFinger);

            game.play(realPlayer, virtualPlayer);

            // a game finishes when one of the user reach 12 points
            if (game.hasGotMaxScore(realPlayer, virtualPlayer)) {
                keepPlay = false;
            }
        }
        println("END GAME");
        // updateGameHistory(game.roundHistory);
        // displayRoundHistory(game.roundHistory);
    }

    private void updateGameHistory(RoundResult[] roundHistory) {
        if (historyCounter > gameHistory.length) {
            gameHistory = Arrays.copyOf(gameHistory, gameHistory.length * 2);
        }

        GameResult gameResult = new GameResult();

        for (RoundResult round : roundHistory) {
            // for (int i = 0; i < roundHistory.length; i++) {
            //     RoundResult round = roundHistory[i];

            // - the number of rounds won and lost by the human player
            if (round.hasRealPlayerWon) {
                gameResult.wonRounds++;
            } else {
                // TODO gameResult.lostRounds++;
            }
            // - how many even and odd numbers have been chosen by each player
            // TODO

            // realUserTotalOdd
            // virtualUserTotalEven
            // realUserTotalEven
            // virtualUserTotalOdd

            // - the extra points received by each player per game.
            // TODO
            // realUserExtaScore
            // virtualUserExtaScore
        }

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

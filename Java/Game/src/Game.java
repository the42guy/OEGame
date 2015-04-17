import java.util.Scanner;

/**
 * Created by peter on 15/4/15.
 */
public class Game {
    private static int roundsPlayed;
    private static Scanner sc = new Scanner(System.in);
    private static boolean userWonToss, userWantBat, userWonGame;

    private Game() {
        System.out.println("Game class should not be instantiated. It is a static class.");                             //you can not create an instance of the Game class. Static and private.
    }

    public static void startGame() {                                                                                    //the game starts here
        setPlayerName();                                                                                                //sets user's name
        toss();                                                                                                         //do a toss to select batting/balling
        if(userWantBat) {                                                                                               //userWantBat is set by toss
            bat();                                                                                                      //do the batting
            System.out.println(runsToMake());                                                                           //displays the target for bot
            ball();                                                                                                     //user balls  :P
        } else {                                                                                                        //if the user wants to ball
            ball();                                                                                                     //then ball
            System.out.println(runsToMake());                                                                           //display user's target
            bat();                                                                                                      //now user bats
        }
        setWinner();                                                                                                    //sets winner
    }

    private static void setWinner() {
        int playerScore = Player.getScore();
        int botScore = CPUPlayer.getScore();
        if(playerScore > botScore) {
            System.out.println("Congratulations! You won!");
            System.out.printf("Your score is %d and the bot scored %d. You won by a margin of %d\n\n", playerScore, botScore, playerScore - botScore);
            userWonGame = true;
        } else if(playerScore < botScore) {
            System.out.println("The bot won. Best of luck next time!");
            System.out.printf("The bot scored %d and you scored %d. You lost by a margin of %d\n\n", botScore, playerScore, botScore - playerScore);
            userWonGame = false;
        } else if(playerScore == botScore) {
            System.out.println("A tie. Darn.");
            System.out.printf("Both of you scored %d", playerScore);
        }
    }

    private static void toss() {
        boolean userEven = userTossChoice();
        int userChoice = getUserNum();
        int botChoice = getBotNum();
        int combined = userChoice + botChoice;
        if((combined % 2 == 0) && (userEven)) {                                                                         //if the combined int is even and the user wanted an even
            System.out.printf("You won the toss! ");
            userWonToss = true;                                                                                         //then user wins toss
        } else if ((combined % 2 != 0) && (!userEven)) {                                                                //if the combined int is odd and the user wanted an odd
            System.out.printf("You won the toss! ");
            userWonToss = true;                                                                                         //then user wins toss
        } else {                                                                                                        //otherwise they lose
            System.out.printf("You lost the toss! ");
        }
        System.out.printf("You chose %d and the computer chose %d, resulting in %d\n", userChoice, botChoice, combined);
        if(userWonToss) {
            setUserBattingPref();
        } else {
            userWantBat = !CPUPlayer.wantToBat();                                                                       //If CPUPlayer wants to bat, user doesn't. Vice versa.
        }
    }

    private static void setUserBattingPref() {
        System.out.print("Do you want to bat (y/n)? ");
        String wantToBat = sc.nextLine().toLowerCase();
        if ((wantToBat.equals("y")) || (wantToBat.equals("yes")) || (wantToBat.equals("bat")) || (wantToBat.equals("yep"))) {
            userWantBat = true;
            System.out.println("You chose to bat");
        } else if ((wantToBat.equals("n")) || (wantToBat.equals("no")) || (wantToBat.equals("ball")) || (wantToBat.equals("nope"))) {
            userWantBat = false;
            System.out.println("You chose to ball");
        } else {
            System.out.println("Looks like the input was wrong. Try again!");
            setUserBattingPref();
        }
    }

    private static int getUserNum() {
        return Player.getNum();
    }

    private static int getBotNum() {
        return CPUPlayer.getNum();
    }

    private static boolean userTossChoice() {                                                                           //returns true if user wants even, false if odd
        boolean userWantsEven = false;
        System.out.print("Select your choice: even or odd (e/o)? ");
        String userTossC = sc.nextLine().toLowerCase();
        if((userTossC.equals("e")) || (userTossC.equals("even"))) {
            System.out.println("You chose even");
            userWantsEven = true;
        } else if ((userTossC.equals("o")) || (userTossC.equals("odd"))) {
            System.out.println("You chose odd");
            userWantsEven = false;
        } else {
            userTossChoice();
        }
        return userWantsEven;
    }

    private static void bat() {
        System.out.println("\nYou are batting");
        boolean notOutYet = true;                                                                                       //user is not yet out
        do {
            int userRun = getUserNum();                                                                                 //takes user input
            int botRun = getBotNum();                                                                                   //takes bot input
            if(userRun == botRun) {                                                                                     //if same, then the user is out
                System.out.printf("\nYou're out.\nBoth you and the bot chose %d.\n", (userRun + botRun) / 2);
                notOutYet = false;                                                                                      //sets the boolean
            } else {
                System.out.printf("You chose %d and the bot chose %d. \n", userRun, botRun);                            //otherwise show the runs and
                Player.incrementScore(userRun);                                                                         //increment Player's score
            }
            System.out.printf("Your score is %d\n\n", Player.getScore());
        } while (notOutYet);
    }

    private static void ball() {
        System.out.println("\nYou are balling");
        boolean botNotYetOut = true;                                                                                    //bot is not yet out
        do {
            int userRun = getUserNum();                                                                                 //user input
            int botRun = getBotNum();                                                                                   //bot input
            if(userRun == botRun) {                                                                                     //if same, bowl the bot out
                System.out.printf("\nYou bowled the bot out!\nBoth you and the bot chose %d.\n", (userRun + botRun)/2);
                botNotYetOut = false;                                                                                   //sets the boolean
            } else {
                System.out.printf("The bot chose %d, but you chose %d. \n", botRun, userRun);                           //otherwise show the runs and
                CPUPlayer.incrementScore(botRun);                                                                       //increment Bot's score
            }
            System.out.printf("Bot\'s score is %d\n\n", CPUPlayer.getScore());
        } while (botNotYetOut);
    }

    private static String runsToMake() {
        int scorePlayer1 = Player.getScore();
        int scorePlayer2 = CPUPlayer.getScore();
        String runsToMakeStatement = "%s needs to make a minimum of %d points more to beat %s.";                        //the default message template. %s means string and %d is int
        if(scorePlayer1 > scorePlayer2) {
            runsToMakeStatement = String.format(runsToMakeStatement, Player.getName(), (scorePlayer1-scorePlayer2), CPUPlayer.getName());   //inserts the given strings and ints in the template
        } else if (scorePlayer1 < scorePlayer2) {
            runsToMakeStatement = String.format(runsToMakeStatement, CPUPlayer.getName(), (scorePlayer2-scorePlayer1), Player.getName());
        }
        return runsToMakeStatement;
    }

    private static void setPlayerName() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Player.setName(name);
    }

}

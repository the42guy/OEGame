import java.util.Scanner;

/**
 * Created by peter on 15/4/15.
 */
public class Game {
    private static int roundsPlayed;
    private static Scanner sc = new Scanner(System.in);
    private static boolean userWonToss, userWantBat, userWonGame;

    private Game() {
        System.out.println("Game class should not be instantiated. It is a static class.");
    }

    public static void startGame() {
        toss();
        if(userWantBat) {
            bat();
            ball();
        } else {
            ball();
            bat();
        }
        setWinner();
    }

    private static void setWinner() {
        int playerScore = Player.getScore();
        int botScore = CPUPlayer.getScore();
        if(playerScore > botScore) {
            System.out.println("Congratulations! You won!");
            System.out.printf("Your score is %d and the bot scored %d. You won by a margin of %d\n\n", playerScore, botScore, playerScore - botScore);
        } else if(playerScore < botScore) {
            System.out.println("The bot won. Best of luck next time!");
            System.out.printf("The bot scored %d and you scored %d. You lost by a margin of %d\n\n", botScore, playerScore, botScore - playerScore);
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
        if((combined % 2 == 0) && (userEven)) {
            System.out.printf("You won the toss! ");
            userWonToss = true;
        } else if ((combined % 2 != 0) && (!userEven)) {
            System.out.printf("You won the toss! ");
            userWonToss = true;
        } else {
            System.out.printf("You lost the toss! ");
        }
        System.out.printf("You chose %d and the computer chose %d, resulting in %d\n", userChoice, botChoice, combined);
        if(userWonToss) {
            setUserBattingPref();
        } else {
            userWantBat = !CPUPlayer.wantToBat();                                                            //If CPUPlayer wants to bat, user doesn't. Vice versa.
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

    private static boolean userTossChoice() {
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
        boolean notOutYet = true;
        do {
            int userRun = getUserNum();
            int botRun = getBotNum();
            if(userRun == botRun) {
                System.out.printf("\nYou're out. \nBoth you and the bot chose %d. \n", (userRun + botRun) / 2);
                notOutYet = false;
            } else {
                System.out.printf("You chose %d and the bot chose %d. \n", userRun, botRun);
                Player.incrementScore(userRun);
            }
            System.out.printf("Your score is %d\n\n", Player.getScore());
        } while (notOutYet);
    }

    private static void ball() {
        System.out.println("\nYou are balling");
        boolean botNotYetOut = true;
        do {
            int userRun = getUserNum();
            int botRun = getBotNum();
            if(userRun == botRun) {
                System.out.printf("\nYou bowled the bot out! \nBoth you and the bot chose %d. \n", (userRun + botRun)/2);
                botNotYetOut = false;
            } else {
                System.out.printf("The bot chose %d, but you chose %d. \n", botRun, userRun);
                CPUPlayer.incrementScore(botRun);
            }
            System.out.printf("Bot\'s score is %d\n\n", CPUPlayer.getScore());
        } while (botNotYetOut);
    }

}

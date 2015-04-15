import java.util.Scanner;

/**
 * Created by peter on 15/4/15.
 */
public class Player {
    static Scanner s = new Scanner(System.in);
    private static int score;

    public static int getNum() {
        System.out.print("Enter your number (1-10): ");
        int userChoice = 0;
        boolean loopTheSelection = true;
        do {
            userChoice = s.nextInt();
            if((userChoice > 10) || (userChoice < 1)) {
                System.out.println("Your input is either empty or invalid!");
                System.out.print("Enter another number (1 - 10): ");
            } else {
                loopTheSelection = false;
            }  //end if
        } while (loopTheSelection);  //end do-while
        return userChoice;
    }

    public static void incrementScore(int incrementValue) {
        score += incrementValue;
    }

    public static int getScore() {
        return score;
    }

}

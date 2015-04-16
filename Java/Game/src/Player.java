import java.util.Scanner;

/**
 * Created by peter on 15/4/15.
 */
public class Player /*implements PlayerInterface*/ {                                                                    //will implement interface when multiplayer is more developed
    static Scanner s = new Scanner(System.in);
    private static int score;
    private static String name;

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

    public static void setName(String name) {
        Player.name = name;
    }

    public static String getName() {
        return Player.name;
    }


}

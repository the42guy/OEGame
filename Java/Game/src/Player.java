import java.util.Scanner;

/**
 * Created by peter on 15/4/15.
 */
public class Player /*implements PlayerInterface*/ {                                                                    //will implement interface when multiplayer is more developed
    static Scanner s = new Scanner(System.in);
    private static int score;
    private static String name;

    public static int getNum() {                                                                                        //asks user for a numeric input
        System.out.print("Enter a number (1-10): ");
        int userChoice = 0;                                                                                             //initializes default userChoice to prevent errors.
        boolean loopTheSelection = true;                                                                                //user has not done input, so loop it
        do {
            userChoice = s.nextInt();                                                                                   //takes an int from the user
            if((userChoice > 10) || (userChoice < 1)) {                                                                 //if userInput is >10 or <1, then it is considered out of range
                System.out.println("Your input is either empty or invalid!");
                System.out.print("Enter another number (1 - 10): ");
            } else {                                                                                                    //sets the looping bool as false
                loopTheSelection = false;
            }  //end if
        } while (loopTheSelection);  //end do-while
        return userChoice;
    }

    public static void incrementScore(int incrementValue) {                                                             //increments user's score
        score += incrementValue;
    }

    public static int getScore() {                                                                                      //returns user's score
        return score;
    }

    public static void setName(String name) {                                                                           //sets the name
        Player.name = name;
    }

    public static String getName() {                                                                                    //whats the name?
        return Player.name;
    }


}

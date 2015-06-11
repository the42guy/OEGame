import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * Created by peter on 15/4/15.
 */
public class CPUPlayer extends Player /*implements PlayerInterface*/ {                                                  //will implement when multiplayer is more developed, possibly not extend Player
    private static int score;
    private static int unPredictabilityLen;                                                                            //on a scale of 1-10, 10 means extremely difficult, 1 means darn easy
    private static ArrayList<Integer> outputRange;
    private static String name = "Bot";

    /*public short getBotNum() {                                                                                          //this method might be needed for multi-bots
        return getBotNumStatic();
    }*/

    static {                                                                                                            //the static initializer

    }

    private static void setRanges(int length) {                                                                                   //randomly sets the ranges, depending on max capacity
        outputRange = new ArrayList<>(length);
        System.out.printf("The length is %d\n", length);
        for(int i = 0; i < length; i++) {
            switch (i) {
                case 0:
                    outputRange.add(0, getRandomInt());
                    break;
                default:
                    int intToCheck = getRandomInt();
                    boolean hasInt = outputRange.contains(intToCheck);
                    while(hasInt) {
                        intToCheck = getRandomInt();
                        if(!outputRange.contains(intToCheck)) {
                            hasInt = false;
                        }
                    } //end while
                    outputRange.add(i, intToCheck);
            } //end switch case
        }
        printRange();
    }

    private static void printRange() {
        System.out.println("The outputRange is now: ");
        for(int i = 0; i < outputRange.size(); i++) {
            if(i+1 == outputRange.size()) {
                System.out.print(outputRange.get(i) + "\n");
            } else {
                System.out.print(outputRange.get(i) + ", ");
            }
        }
    }

    private static int getRandomInt() {
        int randomInt = (int) (random() * 10);
        return randomInt;
    }

    public static int getNum() {                                                                                        //gets a random input from the bot
        int botChoice;
        do {
            botChoice = (int) (random() * 10);                                                                     //a random int with max limit 10 (1-10)
        } while (!((botChoice <= 10) && (botChoice >= 1)));                                                             //break it if the int is between 1 and 10, else repeat to get a desried int

        return botChoice;
    }

    public static void incrementScore(int incrementValue) {                                                             //duh
        score += incrementValue;
    }

    public static int getScore() {                                                                                      //duh
        return score;
    }

    public static boolean wantToBat() {                                                                                 //a random method to set the batting preference for the bot
        boolean wantToBatBool = true;                                                                                   //wants to bat by default
        int aRandomInt = (int) (random() * 99);                                                                    //an int between 1 and 99
        if(aRandomInt % 2 != 0) {                                                                                       //more chances that the number is odd. So more likely that it wll chose to ball (51%)
            System.out.println("\nThe bot chose to ball");
            wantToBatBool = false;
        } else {
            System.out.println("\nThe bot chose to bat");
            wantToBatBool = true;
        }
        return wantToBatBool;
    }

    public static String getName() {                                                                                    //The name's Bot. James Bot.
        return CPUPlayer.name;
    }

    public static void setUnPredictabilityLen(int length) {
        unPredictabilityLen = length;
        setRanges(unPredictabilityLen);
    }
}

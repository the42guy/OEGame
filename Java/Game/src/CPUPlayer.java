import static java.lang.Math.*;

/**
 * Created by peter on 15/4/15.
 */
public class CPUPlayer extends Player /*implements PlayerInterface*/ {                                                  //will implement when multiplayer is more developed, possibly not extend Player
    private static int score;
    private static int unPredictabilityLen = 2;                                                                            //on a scale of 1-10, 10 means extremely difficult, 1 means darn easy
    private static int[] range;
    private static String name = "Bot";

    /*public short getBotNum() {                                                                                          //this method might be needed for multi-bots
        return getBotNumStatic();
    }*/

    static {                                                                                                            //the static initializer

    }

    private static void setRanges(int length) {                                                                                   //randomly sets the ranges, depending on max capacity
        range = new int[length];
        System.out.printf("The length is %d, which is equal to %d\n", range.length, length);
        for(int i = 0; i < range.length; i++) {
            range[i] = (int) (random() * 10);
            if ((range.length > 1) && (i > 0)) {                                                                //if the range length is more than 1 or not equal to 0
                range[i] = createUniqueNumber(range[i], range);
                System.out.printf("Range[%d] initialized as %d\n", i, range[i]);
            } else if ((i == 0) || (range.length == 1)) {                                                                      //if the length of range is 1 or it is the first iteration here
                System.out.println("First time here/range length is one");
                System.out.printf("range[%d] initialized as %d in this first iteration\n", i, range[i]);
            }
        }
        System.out.println("The range is now: ");
        for(int i = 0; i < range.length; i++) {
            if(i == range.length) {
                System.out.print(range[i]);
            } else {
                System.out.print(range[i] + ", ");
            }

        }
    }

    private static int createUniqueNumber(int a, int[] arrayToSearchFora) {
    	boolean areEqual = false;
        for(int i = 0; i < arrayToSearchFora.length; i++) {
            for(int j = 0; j < i; j++) {
                boolean iAndJEqual = true;
                do {
                    if(range[i] == range[j]) {
                        range[j] = (int) (Math.random() * 10);
                        System.out.printf("The int %d now initialized as %d\n", range[i], range[j]);
                    } else if (range[i] != range[j]) {
                        System.out.printf("The ints %d and %d are not equal\n", range[i], range[j]);
                        iAndJEqual = false;
                    }
                } while (iAndJEqual);
                System.out.printf("Ending for inner index %d\n\n", j);
            }
        }

        return a;
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

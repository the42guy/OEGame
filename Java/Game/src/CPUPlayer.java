/**
 * Created by peter on 15/4/15.
 */
public class CPUPlayer extends Player /*implements PlayerInterface*/ {                                                  //will implement when multiplayer is more developed, possibly not extend Player
    private static int score;

    /*public short getBotNum() {                                                                                          //this method might be needed for multi-bots
        return getBotNumStatic();
    }*/

    private static String name;

    public static int getNum() {
        int botChoice;
        looper: do {
            botChoice = (int) (Math.random() * 10);
        } while (!((botChoice <= 10) && (botChoice >= 1)));
        return botChoice;
    }

    public static void incrementScore(int incrementValue) {
        score += incrementValue;
    }

    public static int getScore() {
        return score;
    }

    public static boolean wantToBat() {
        boolean wantToBatBool = true;
        int aRandomInt = (int) (Math.random() * 99);
        if(aRandomInt % 2 == 0) {
            System.out.println("\nThe bot chose to ball");
            wantToBatBool = false;
        } else {
            System.out.println("\nThe bot chose to bat");
            wantToBatBool = true;
        }
        return wantToBatBool;
    }

    public static void setName(String name) {
        CPUPlayer.name = name;
    }

    public static String getName() {
        return CPUPlayer.name;
    }
}

/**
 * Created by peter on 15/4/15.
 */
public class CPUPlayer extends Player {
    private static int score;

    /*public short getBotNum() {                                                                                          //this method might be needed for multi-bots
        return getBotNumStatic();
    }*/

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
            wantToBatBool = false;
        } else {
            wantToBatBool = true;
        }
        return wantToBatBool;
    }
}

package Controller;

import java.util.TimerTask;
import java.util.Timer;

public class GameTimer {

    private static int seconds;
    private static int secondsCounter;
    private static int minutes;
    private static int minutesCounter;

    private static final int TIME_INTERVAL = 1;
    private static final int MINUTE_IN_SECONDS = 60;
    private static final int RESET = 0;

    private static boolean gameStart = false;

    public GameTimer() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            public void run() {
                if (gameStart)
                    incrementSeconds();
                }

            }, 0, 1000);
    }

    public static void gameReset(){
        setSeconds(RESET);
        setSecondsCounter(RESET);
        setMinutes(RESET);
        setMinutesCounter(RESET);
        setGameStart(false);
    }

    public void incrementSeconds(){
        setSeconds(getSeconds() + TIME_INTERVAL);

        if(getSeconds() >= MINUTE_IN_SECONDS) {
            setSeconds(RESET);
            setMinutes(getMinutes() + TIME_INTERVAL);
        }
    }

    public static int getSeconds() {
        return seconds;
    }

    public static int getSecondsCounter(){
        return secondsCounter;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static int getMinutesCounter() {
        return minutesCounter;
    }

    public static void setSeconds(int seconds) {
        GameTimer.seconds = seconds;
    }

    public static void setSecondsCounter(int secondsCounter){
        GameTimer.secondsCounter = secondsCounter;
    }

    public static void setMinutes(int minutes) {
        GameTimer.minutes = minutes;
    }

    public static void setMinutesCounter(int minutesCounter) {
        GameTimer.minutesCounter = minutesCounter;
    }

    public static void setGameStart(boolean gameRunning) {
        GameTimer.gameStart = gameRunning;
    }
}
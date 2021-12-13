package com.comp2042_cw_hcyss4.Controller;

import java.util.TimerTask;
import java.util.Timer;

/**
 * GameTimer class
 * @author Shanahan
 * @since 09/12/2021
 */
public class GameTimer {

    private static int seconds;
    private static int secondsCounter;
    private static int minutes;
    private static int minutesCounter;

    private static final int TIME_INTERVAL = 1;
    private static final int MINUTE_IN_SECONDS = 60;
    private static final int RESET = 0;

    private static boolean gameStart = false;

    /**
     * Game Timer constructor to be called when creating the object; is set to increment using via incrementSeconds() method
     */
    public GameTimer() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {

            public void run() {
                if (gameStart)
                    incrementSeconds();
                }

            }, 0, 1000);
    }

    /**
     * Method to reset the game timer, values set to 0 or false
     */
    public static void gameReset(){
        setSeconds(RESET);
        setSecondsCounter(RESET);
        setMinutes(RESET);
        setMinutesCounter(RESET);
        setGameStart(false);
    }

    /**
     * Method to increment the second variable by 1, at 60 seconds, minute is incremented by 1
     */
    public void incrementSeconds(){
        setSeconds(getSeconds() + TIME_INTERVAL);

        if(getSeconds() >= MINUTE_IN_SECONDS) {
            setSeconds(RESET);
            setMinutes(getMinutes() + TIME_INTERVAL);
        }
    }

    /**
     * Returns the Seconds value of the Game Timer
     * @return the seconds
     */
    public static int getSeconds() {
        return seconds;
    }

    /**
     * Returns the Seconds counter value of the Game Timer
     * @return the seconds counter value
     */
    public static int getSecondsCounter(){
        return secondsCounter;
    }

    /**
     * Returns the Minutes value of the Game Timer
     * @return the minutes
     */
    public static int getMinutes() {
        return minutes;
    }

    /**
     * Returns the Minutes counter value of the Game Timer
     * @return the minutes counter value
     */
    public static int getMinutesCounter() {
        return minutesCounter;
    }

    /**
     * Sets the Seconds value of the Game Timer
     * @param seconds the new seconds value
     */
    public static void setSeconds(int seconds) {
        GameTimer.seconds = seconds;
    }

    /**
     * Sets the Seconds counter value of the Game Timer
     * @param secondsCounter the new seconds counter value
     */
    public static void setSecondsCounter(int secondsCounter){
        GameTimer.secondsCounter = secondsCounter;
    }

    /**
     * Sets the Minutes value of the Game Timer
     * @param minutes the new minutes value
     */
    public static void setMinutes(int minutes) {
        GameTimer.minutes = minutes;
    }

    /**
     * Sets the Minutes counter value of the Game Timer
     * @param minutesCounter the new minutes counter value
     */
    public static void setMinutesCounter(int minutesCounter) {
        GameTimer.minutesCounter = minutesCounter;
    }

    /**
     * Sets the state of the game (started or not)
     * @param gameStart the state of the game
     */
    public static void setGameStart(boolean gameStart) {
        GameTimer.gameStart = gameStart;
    }

    /**
     * Returns the state of the game
     * @return the state of the game (started or not)
     */
    public static boolean getGameStart() {
        return gameStart;
    }
}
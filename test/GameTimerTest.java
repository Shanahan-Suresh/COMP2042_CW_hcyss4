import Controller.GameTimer;

import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {

    @org.junit.jupiter.api.Test
    void increaseSecond() {
        GameTimer gameTimer = new GameTimer();
        GameTimer.gameReset();

        gameTimer.incrementSeconds();
        assertEquals(1, GameTimer.getSeconds()); //verify seconds was incremented

        GameTimer.setSeconds(59);
        gameTimer.incrementSeconds();
        assertEquals(1, GameTimer.getMinutes()); //verify minutes was incremented
        assertEquals(0, GameTimer.getSeconds()); //verify seconds were reset to 0
    }


    @org.junit.jupiter.api.Test
    void secondsValue() {
        GameTimer.setSeconds(15);
        assertEquals(15, GameTimer.getSeconds()); //verify seconds value
    }

    @org.junit.jupiter.api.Test
    void secondsCounterValue() {
        GameTimer.setSecondsCounter(10);
        assertEquals(10, GameTimer.getSecondsCounter()); //verify seconds counter value
    }

    @org.junit.jupiter.api.Test
    void minutesValue() {
        GameTimer.setMinutes(3);
        assertEquals(3, GameTimer.getMinutes()); //verify minutes value
    }

    @org.junit.jupiter.api.Test
    void minutesCounterValue() {
        GameTimer.setMinutesCounter(4);
        assertEquals(4, GameTimer.getMinutesCounter()); //verify minutes counter value
    }

    @org.junit.jupiter.api.Test
    void gameStartValue() {
        GameTimer.setGameStart(true);
        assertTrue(GameTimer.getGameStart()); //verify game is started
        GameTimer.setGameStart(false);
        assertFalse(GameTimer.getGameStart()); //verify game is stopped
    }

    @org.junit.jupiter.api.Test
    void resetGame() {
        GameTimer.setSeconds(2);
        GameTimer.setMinutes(3);
        GameTimer.setSecondsCounter(5);
        GameTimer.setMinutesCounter(4);
        GameTimer.setGameStart(true);

        GameTimer.gameReset(); //verify if every value is 0 or false after reset
        assertEquals(0, GameTimer.getSeconds());
        assertEquals(0, GameTimer.getMinutes());

        assertEquals(0, GameTimer.getMinutesCounter());
        assertEquals(0, GameTimer.getSecondsCounter());
        assertFalse(GameTimer.getGameStart());
    }
}
package test;

import Controller.GameTimer;
import Controller.HighScore;
import Model.Walls.Wall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {

    @Test
    void calculateScore() {
        HighScore.setScore(new int[10][3]);

        int i, j;
        for (i = 0; i < 9; i++) {

            for (j = 0; j < 3; j++) {
                HighScore.getScore()[i][j] = 2;
            }
        }

        Wall.setBrokenBrickCount(7);
        GameTimer.setMinutes(3);
        GameTimer.setSeconds(10);

        HighScore.calculateScore();

        assertEquals(7, HighScore.getScore()[0][0]); //verify brick count in score array
        assertEquals(3, HighScore.getScore()[0][1]); //verify minutes  in score array
        assertEquals(10, HighScore.getScore()[0][2]); //verify seconds  in score array
    }

    @Test
    void getScore() {
        HighScore.setScore(new int[10][3]);
        int i, j;

        for (i = 0; i < 9; i++) {

            for (j = 0; j < 3; j++) {
                assertEquals(0, HighScore.getScore()[i][j]); //verify getscore is empty in new array
            }
        }
    }
}
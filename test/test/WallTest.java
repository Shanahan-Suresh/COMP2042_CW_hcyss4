package test;

import Model.Walls.Wall;
import org.junit.jupiter.api.Test;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));

    @Test
    public void wallReset(){
        wall.nextLevel();
        wall.bricks[0].impact();
        wall.bricks[10].impact();
        assertTrue(wall.bricks[0].isBroken()); //verify the brick is broken
        assertTrue(wall.bricks[10].isBroken()); //verify the brick is broken

        wall.wallReset();
        assertFalse(wall.bricks[0].isBroken()); //verify the brick is NOT broken
        assertFalse(wall.bricks[10].isBroken()); //verify the brick is NOT broken
    }

    @Test
    public void setBallXSpeed(){
        wall.setBallXSpeed(10);
        assertEquals(10, wall.ball.getSpeedX()); //verify ball x speed
    }

    @Test
    public void setBallYSpeed(){
        wall.setBallYSpeed(10);
        assertEquals(10, wall.ball.getSpeedY()); //verify ball y speed
    }
}

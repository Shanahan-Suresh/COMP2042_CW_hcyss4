import Model.Bricks.SteelBrick;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {

    SteelBrick steelBrick = new SteelBrick(new Point(0,0), new Dimension(60,45));
    Rectangle brickFace = new Rectangle(new Point(0,0), new Dimension(60,45));

    @Test
    void getBrickFace() {
        assertEquals(brickFace, steelBrick.getBrickFace()); //verify the SteelBrick object type is a Rectangle
    }

    @Test
    void impact() {
        Random rnd = new Random();
        double STEEL_PROBABILITY = 0.5; //more than steel's probability
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            assertFalse(steelBrick.isBroken()); //brick will NOT be broken
        }
    }

    @Test
    public void repairSteelBrick(){
        steelBrick.impact();
        steelBrick.repair();
        assertEquals(1, steelBrick.getStrength()); //verify steel strength AFTER repair is 1
        assertFalse(steelBrick.isBroken()); //verify the brick is not broken
    }
}
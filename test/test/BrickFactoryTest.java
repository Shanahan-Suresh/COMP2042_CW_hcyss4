package test;

import Model.Bricks.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

class BrickFactoryTest {
    Point point = new Point();
    Dimension area = new Dimension(0, 0);

    @Test
    public void brickFactoryClayBrickTest() {
        assertEquals(new ClayBrick(point, area).getClass(),
                (BrickFactory.getBrickType("CLAY", point, area)).getClass()); //verify object is ClayBrick class
    }

    @Test
    public void brickFactoryCementBrickTest() {
        assertEquals(new CementBrick(point,area).getClass(),
                (BrickFactory.getBrickType("CEMENT", point, area)).getClass()); //verify object is CementBrick class
    }

    @Test
    public void brickFactorySteelBrickTest() {
        assertEquals(new SteelBrick(point,area).getClass(),
                (BrickFactory.getBrickType("STEEL", point, area)).getClass()); //verify object is SteelBrick class
    }

    @Test
    public void brickFactoryDiamondBrickTest() {
        assertEquals(new DiamondBrick(point,area).getClass(),
                (BrickFactory.getBrickType("DIAMOND", point, area)).getClass()); //verify object is DiamondBrick class
    }

}
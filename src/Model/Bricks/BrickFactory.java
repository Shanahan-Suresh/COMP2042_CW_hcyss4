package Model.Bricks;

import java.awt.*;

/**
 * Brick Factory Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class BrickFactory {

    /**
     * A factory class to instantiate new Brick objects
     * @param point X and Y coordinates of the brick
     * @param size size of the Brick
     * @param brickType type of the brick
     * @return the object created by the constructor of corresponding called brick type
     */
    public static Brick getBrickType(String brickType, Point point, Dimension size){

        if (brickType == null)
            return null;
        if(brickType.equalsIgnoreCase("CLAY"))
            return new ClayBrick(point, size);

        else if(brickType.equalsIgnoreCase("CEMENT"))
            return new CementBrick(point, size);

        else if(brickType.equalsIgnoreCase("STEEL"))
            return new SteelBrick(point, size);

        else if(brickType.equalsIgnoreCase("DIAMOND"))
            return new DiamondBrick(point, size);

        else
            return null;
    }
}

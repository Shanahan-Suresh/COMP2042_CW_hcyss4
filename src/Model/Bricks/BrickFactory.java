package Model.Bricks;

import java.awt.*;

public class BrickFactory {

    public static Brick getBrickType(String brickType, Point point, Dimension size){

        if (brickType == null)
            return null;
        if(brickType.equalsIgnoreCase("CLAY"))
            return new ClayBrick(point, size);

        else if(brickType.equalsIgnoreCase("CEMENT"))
            return new CementBrick(point, size);

        else if(brickType.equalsIgnoreCase("STEEL"))
            return new SteelBrick(point, size);

        else
            return null;
    }
}

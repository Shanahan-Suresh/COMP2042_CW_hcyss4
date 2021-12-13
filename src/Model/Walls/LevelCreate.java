package Model.Walls;

import Model.Bricks.Brick;
import Model.Bricks.BrickFactory;


import java.awt.*;

/**
 * Level Create Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class LevelCreate {

    /** Generates a level with consisting of only one brick type
     * @param drawArea the level shape
     * @param brickCount the number of bricks in the level
     * @param lineCount the number of brick rows
     * @param brickSizeRatio the brick size ratio in comparison to the window
     * @param type the brick type
     * @return array of bricks
     */
    protected static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, WallType type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] temp  = new Brick[brickCount];

        Dimension brickSize = new Dimension( (int)brickLength, (int)brickHeight);
        Point point = new Point();

        int i;
        for(i = 0; i < temp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            double x = (i % brickOnLine) * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            point.setLocation(x,y);
            temp[i] = makeBrick(point, brickSize, type);
        }

        for(double y = brickHeight; i < temp.length; i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            point.setLocation(x,y);
            temp[i] = BrickFactory.getBrickType("CLAY", point, brickSize);
        }

        return temp;
    }

    /** Generates a level with two types of bricks
     * @param drawArea the level shape
     * @param brickCount the number of bricks in the level
     * @param lineCount the number of brick rows
     * @param brickSizeRatio the brick size ratio in comparison to the window
     * @param typeA the 1st brick type
     * @param typeB the 2nd brick type
     * @return array of bricks
     */
    protected static Brick[] makeChessboardLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, WallType typeA, WallType typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHeight = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] temp  = new Brick[brickCount];

        Dimension brickSize = new Dimension( (int)brickLength, (int)brickHeight);
        Point point = new Point();

        int i;
        for(i = 0; i < temp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHeight;
            point.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            temp[i] = b ?  makeBrick(point, brickSize, typeA) : makeBrick(point, brickSize, typeB);
        }

        for(double y = brickHeight;i < temp.length;i++, y += 2*brickHeight){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            point.setLocation(x,y);
            temp[i] = makeBrick(point, brickSize, typeA);
        }

        return temp;
    }

    private static Brick makeBrick(Point point, Dimension size, WallType type){
        Brick out;

        switch(type){
            case CLAY:
                out = BrickFactory.getBrickType("CLAY", point, size);
                break;

            case STEEL:
                out = BrickFactory.getBrickType("STEEL", point, size);
                break;

            case CEMENT:
                out = BrickFactory.getBrickType("CEMENT", point, size);
                break;

            case DIAMOND:
                out = BrickFactory.getBrickType("DIAMOND", point, size);
                break;

            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }

        return out;
    }

}

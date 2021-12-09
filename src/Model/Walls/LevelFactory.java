package Model.Walls;

import Model.Bricks.Brick;
import Model.Bricks.CementBrick;
import Model.Bricks.ClayBrick;
import Model.Bricks.SteelBrick;

import java.awt.*;

class LevelFactory {

    protected static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCount, int lineCount, double brickSizeRatio, WallType type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCount -= brickCount % lineCount;

        int brickOnLine = brickCount / lineCount;

        double brickLength = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] temp  = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLength,(int) brickHgt);
        Point point = new Point();

        int i;
        for(i = 0; i < temp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            double x = (i % brickOnLine) * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHgt;
            point.setLocation(x,y);
            temp[i] = makeBrick(point, brickSize, type);
        }

        for(double y = brickHgt; i < temp.length; i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLength) - (brickLength / 2);
            point.setLocation(x,y);
            temp[i] = new ClayBrick(point, brickSize);
        }

        return temp;
    }

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
        double brickHgt = brickLength / brickSizeRatio;

        brickCount += lineCount / 2;

        Brick[] temp  = new Brick[brickCount];

        Dimension brickSize = new Dimension((int) brickLength,(int) brickHgt);
        Point point = new Point();

        int i;
        for(i = 0; i < temp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCount)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLength;
            x =(line % 2 == 0) ? x : (x - (brickLength / 2));
            double y = (line) * brickHgt;
            point.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            temp[i] = b ?  makeBrick(point, brickSize, typeA) : makeBrick(point, brickSize, typeB);
        }

        for(double y = brickHgt;i < temp.length;i++, y += 2*brickHgt){
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
                out = new ClayBrick(point,size);
                break;

            case STEEL:
                out = new SteelBrick(point,size);
                break;

            case CEMENT:
                out = new CementBrick(point, size);
                break;

            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }

        return out;
    }

}

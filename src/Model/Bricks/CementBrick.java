package Model.Bricks;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


public class CementBrick extends Brick {

    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
    private Shape brickFace = super.brickFace;;


    public CementBrick(Point point, Dimension size){
        super(point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);
    }

    @Override
    public boolean setImpact(Point2D point, int direction) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point, direction, brickFace.getBounds());
            updateBrick();
            return false;
        }
        return true;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath path = crack.draw();
            path.append(super.brickFace,false);
            brickFace = path;
        }
    }

    @Override
    public void repair(){
        super.repair();
        crack.reset();
    }
}

package Model.Bricks;

import Model.Sounds.SoundFactory;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class DiamondBrick extends Brick {

        private static final Color DEF_INNER = new Color(185, 242, 255);
        private static final Color DEF_BORDER = Color.BLACK;
        private static final int DIAMOND_STRENGTH = 4;

        private Crack crack = new Crack(this, DEF_CRACK_DEPTH, DEF_STEPS);
        private Shape brickFace = super.brickFace;

        public DiamondBrick(Point point, Dimension size){
            super(point, size, DEF_BORDER, DEF_INNER, DIAMOND_STRENGTH);
        }


    @Override
    public boolean setImpact(Point2D point, int direction) {
        if(super.isBroken())
            return false;
        super.impact();

        if(!super.isBroken()){
            crack.makeCrack(point, direction);
            updateBrick();
            SoundFactory.getSoundType("CRACK");
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
    public Shape getBrick() {
        return brickFace;
    }

    @Override
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }


}

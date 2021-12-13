package com.comp2042_cw_hcyss4.Model.Bricks;

import com.comp2042_cw_hcyss4.Model.Sounds.SoundFactory;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Cement Brick Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class CementBrick extends Brick {

    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack = new Crack(this, DEF_CRACK_DEPTH, DEF_STEPS);
    private Shape brickFace = super.brickFace;


    /**
     * Constructor to be called when creating Cement Brick objects
     * @param point X and Y coordinates of the brick
     * @param size size of the Brick
     */
    public CementBrick(Point point, Dimension size){
        super(point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);
    }

    /**
     * Detects if there is an impact on the brick, calls crack.makeCrack if brick is not broken
     * @param point contains the point of impact
     * @param direction  direction of brick that is impacted
     * @return false
     */
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

    /**
     * Updates the cement brick's appearance after a crack is generated
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath path = crack.draw();
            path.append(super.brickFace,false);
            brickFace = path;
        }
    }

    /** Method to get the shape of the brick
     * @return the brick's shape
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Method to restore the strength and state of the brick object, calls crack.reset to restore cracked appearances
     */
    @Override
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }


}

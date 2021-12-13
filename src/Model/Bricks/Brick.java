package Model.Bricks;

import Model.Balls.Ball;
import Model.Sounds.SoundFactory;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Brick Class
 * Created by filippo on 04/09/16.
 * Modified by Shanahan on 09/12/21
 * @author Shanahan
 * @since 09/12/2021
 */
abstract public class Brick  {

    /**
     * Defines the crack depth
     */
    public static final int DEF_CRACK_DEPTH = 1;

    /**
     * Defines the number of steps to be taken when generating a crack
     */
    public static final int DEF_STEPS = 35;


    /**
     * Integer value for impact on the upper side of the brick
     */
    public static final int UP_IMPACT = 100;

    /**
     * Integer value for impact on the lower side of the brick
     */
    public static final int DOWN_IMPACT = 200;

    /**
     * Integer value for impact on the left side of the brick
     */
    public static final int LEFT_IMPACT = 300;

    /**
     * Integer value for impact on the right side of the brick
     */
    public static final int RIGHT_IMPACT = 400;

    protected Shape brickFace;

    private Color borderColour;
    private Color innerColour;

    private int fullStrength;
    private int strength;

    private boolean broken = false;

    /**
     * Brick constructor to be called when creating Brick objects
     * @param position X and Y coordinates of the brick
     * @param size size of the brick
     * @param borderColour colour of the brick's border
     * @param innerColour inner color of the brick
     * @param strength strength of the brick
     */
    public Brick(Point position, Dimension size, Color borderColour, Color innerColour, int strength){
        brickFace = makeBrickFace(position,size);
        this.borderColour = borderColour;
        this.innerColour = innerColour;
        this.fullStrength = this.strength = strength;

    }

    /**
     * Method to define the shape of the brick
     * @param position X and Y coordinates of the brick
     * @param size size of the brick
     * @return brick that has the shape of a rectangle
     */
    protected Shape makeBrickFace(Point position,Dimension size) {
        return new Rectangle(position, size);
    };

    /**
     * Detects if there is an impact on the brick, calls impact() if brick is not broken
     * @param point X and Y coordinates of the brick
     * @param direction direction of brick that is impacted
     * @return the state of the brick, broken or not
     */
    public boolean setImpact(Point2D point , int direction){
        if(broken)
            return false;
        impact();
        return broken;
    }

    /** Method to get the shape of the brick
     * @return the brick's shape
     */
    public Shape getBrick() {
        return brickFace;
    }

    /** Locates the direction after impact between the wall and the ball
     * @param ball the ball object
     * @return an integer value that represents the impact direction
     */
    public final int findImpact(Ball ball){
        if(broken)
            return 0;

        if(brickFace.contains(ball.getRight()))
            return LEFT_IMPACT;
        else if(brickFace.contains(ball.getLeft()))
            return RIGHT_IMPACT;
        else if(brickFace.contains(ball.getUp()))
            return DOWN_IMPACT;
        else if(brickFace.contains(ball.getDown()))
            return UP_IMPACT;
        else
            return 0;
    }

    /** Method to get the state of the brick, broken or unbroken
     * @return true if the brick is broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Method to restore the strength and state of the brick object
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Method to reduces brick strength after an impact,sets brick state to broken if strength reaches 0
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
        if (broken) {
            SoundFactory.getSoundType("BRICK");
        }
    }

    /**
     * Method to return the border colour of the brick
     * @return brick's border colour
     */
    public Color getBorderColor(){
        return borderColour;
    }

    /**
     * Method to return the inner colour of the brick
     * @return brick's inner colour
     */
    public Color getInnerColor(){
        return innerColour;
    }
}






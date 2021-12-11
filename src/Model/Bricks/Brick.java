package Model.Bricks;

import Model.Balls.Ball;
import Model.Sounds.HitSound;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 * Modified by Shanahan on 09/12/21
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    protected Shape brickFace;

    private Color borderColour;
    private Color innerColour;

    private int fullStrength;
    private int strength;

    private boolean broken = false;


    public Brick(Point position, Dimension size, Color borderColour, Color innerColour, int strength){
        brickFace = makeBrickFace(position,size);
        this.borderColour = borderColour;
        this.innerColour = innerColour;
        this.fullStrength = this.strength = strength;

    }

    protected Shape makeBrickFace(Point position,Dimension size) {
        return new Rectangle(position, size);
    };

    public boolean setImpact(Point2D point , int direction){
        if(broken)
            return false;
        impact();
        return broken;
    }

    public Shape getBrick() {
        return brickFace;
    }

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

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
        if (broken) {
            new HitSound();
        }
    }

    public Color getBorderColor(){
        return borderColour;
    }

    public Color getInnerColor(){
        return innerColour;
    }
    

}






package Model.Bricks;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Crack Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class Crack {
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    /**
     * Integer value start generating a crack from the left side of the brick
     */
    public static final int LEFT = 10;

    /**
     * Integer value start generating a crack from the right side of the brick
     */
    public static final int RIGHT = 20;

    /**
     * Integer value start generating a crack from the top side of the brick
     */
    public static final int UP = 30;

    /**
     * Integer value start generating a crack from the bottom side of the brick
     */
    public static final int DOWN = 40;

    /**
     * Integer value for the crack to be generated vertically
     */
    public static final int VERTICAL = 100;

    /**
     * Integer indicator for the crack to be generated horizontally
     */
    public static final int HORIZONTAL = 200;

    private static Random rnd = new Random();
    private final Brick brick;

    private GeneralPath crack;
    private int crackDepth;
    private int steps;


    /**
     * Crack constructor to create a new geometric path in a brick based on parameters
     * @param brick the brick object
     * @param crackDepth the value of the depth of the crack
     * @param steps the number of steps to be taken when generating a crack
     */
    public Crack(Brick brick, int crackDepth, int steps){
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }

    /**
     * Generates a visible crack on the brick object based on the parameters
     * @param point the X and Y coordinats of the starting point of the crack
     * @param direction the direction the crack is generated at
     */
    protected void makeCrack(Point2D point, int direction){
        Rectangle bounds = brick.brickFace.getBounds();
        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point temp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, temp);
                break;

            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                temp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, temp);
                break;

            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                temp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, temp);
                break;

            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                temp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, temp);
                break;

        }
    }

    /**
     * Generates a visible crack on the brick based on the parameters
     * @param start the starting point of the crack
     * @param end the ending point of the crack
     */
    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();
        path.moveTo(start.x, start.y);

        double width = (end.x - start.x) / (double)steps;
        double height = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x, y;

        for(int i = 1; i < steps;i++){

            x = (i * width) + start.x;
            y = (i * height) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path,true);
    }

    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    private int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);

        return  0;

    }

    private Point makeRandomPoint(Point from, Point to, int direction){

        Point out = new Point();
        int position;

        switch(direction){
            case HORIZONTAL:
                position = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(position, to.y);
                break;

            case VERTICAL:
                position = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, position);
                break;
        }
        return out;
    }

    /**
     * Method to return the geometric path taken by the crack
     * @return the geometric path taken by the crack
     */
    public GeneralPath draw(){
        return crack;
    }

    /**
     * Method to reset the state of the crack
     */
    public void reset(){
            crack.reset();
    }
}

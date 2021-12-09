package Model.Balls;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 * Modified by Shanahan on 09/12/21
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    private Point2D up = new Point2D.Double();
    private Point2D down = new Point2D.Double();
    private Point2D left = new Point2D.Double();
    private Point2D right = new Point2D.Double();

    private Color borderColour;
    private Color innerColour;

    private int speedX = 0;
    private int speedY = 0;

    public Ball(Point2D center, int radius, Color innerColour, Color borderColour){
        this.center = center;

        ballFace = makeBall(center, radius);
        this.borderColour = borderColour;
        this.innerColour  = innerColour;

    }

    protected abstract Shape makeBall(Point2D center,int radius);

    private void setLocation(double xCoordinate, double yCoordinate) {

        up.setLocation(center.getX(), center.getY() - (yCoordinate / 2));
        down.setLocation(center.getX(), center.getY() + (yCoordinate / 2));

        left.setLocation(center.getX() - (xCoordinate / 2), center.getY());
        right.setLocation(center.getX() + (xCoordinate / 2), center.getY());
    }

    public void move(){
        center.setLocation((center.getX() + speedX), (center.getY() + speedY));

        RectangularShape temp = (RectangularShape) ballFace;
        double width = temp.getWidth();
        double height = temp.getHeight();

        temp.setFrame((center.getX() -(width/2)), (center.getY() - (height/2)), width, height);
        setPoints(width, height);

        ballFace = temp;
    }

    /* Untested method to be called for moveTo and move
    private RectangularShape setRecFrame(RectangularShape ballFace){
        RectangularShape temp = (RectangularShape) ballFace;
        double width = temp.getWidth();
        double height = temp.getHeight();

        temp.setFrame((center.getX() -(width/2)),(center.getY() - (height/2)), width, height);
        
        return temp;
    }
    */
    
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape temp = (RectangularShape) ballFace;
        double width = temp.getWidth();
        double height = temp.getHeight();

        temp.setFrame((center.getX() -(width/2)),(center.getY() - (height/2)), width, height);
        ballFace = temp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    public void setSpeedX(int s){
        speedX = s;
    }

    public void setSpeedY(int s){
        speedY = s;
    }

    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }

    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    public Color getInnerColor(){
        return innerColour;
    }

    public Color getBorderColor(){
        return borderColour;
    }

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }

    public Point2D getRight() {
        return right;
    }


}

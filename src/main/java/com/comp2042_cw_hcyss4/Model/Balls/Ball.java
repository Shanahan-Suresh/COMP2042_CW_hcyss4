package com.comp2042_cw_hcyss4.Model.Balls;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Ball Class
 * Created by filippo on 04/09/16.
 * Modified by Shanahan on 09/12/21
 * @author Shanahan
 * @since 09/12/2021
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

    /**
     * Ball constructor to be called when creating Ball objects based on parameters
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param innerColour the inner colour of the ball
     * @param borderColour the border colour of the ball
     */
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

    /**
     * Method to control the movement of the ball
     */
    public void move(){
        center.setLocation((center.getX() + speedX), (center.getY() + speedY));

        RectangularShape temp = (RectangularShape) ballFace;
        double width = temp.getWidth();
        double height = temp.getHeight();

        temp.setFrame((center.getX() -(width/2)), (center.getY() - (height/2)), width, height);
        setPoints(width, height);

        ballFace = temp;
    }

    /**
     * Method to move the ball to specific coordinates
     * @param point the coordinates the ball will be moved to
     */
    public void moveTo(Point point){
        center.setLocation(point);

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

    /**
     * Returns the coordinates of the ball, based on it's center
     * @return the coordinates of the ball based on its center
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Returns the shape of the ball
     * @return the shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Sets the ball's x and y speed according to parameters
     * @param x the speed of the ball along the x-axis
     * @param y the speed of the ball along the y-axis
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Sets the ball's x-axis speed
     * @param speed the speed of the ball along the x-axis
     */
    public void setSpeedX(int speed){
        speedX = speed;
    }

    /**
     * Sets the ball's y-axis speed
     * @param speed the speed of the ball along the y-axis
     */
    public void setSpeedY(int speed){
        speedY = speed;
    }

    /**
     * Returns the speed of the ball along the x-axis
     * @return the speed of the ball along the x-axis
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Returns the speed of the ball along the y-axis
     * @return the speed of the ball along the y-axis
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * Method to reverse the speed and direction of the ball along the x-axis
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Method to reverse the speed and direction of the ball along the y-axis
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Returns the inner color of the ball
     * @return the inner color of the ball
     */
    public Color getInnerColor(){
        return innerColour;
    }

    /**
     * Returns the border color of the ball
     * @return the border color of the ball
     */
    public Color getBorderColor(){
        return borderColour;
    }

    /**
     * Returns the value of the upper point of the ball
     * @return the upper point of the ball
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * Returns the value of the lower point of the ball
     * @return the lower point of the ball
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Returns the value of the left point of the ball
     * @return the left point of the ball
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Returns the value of the right point of the ball
     * @return the right point of the ball
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * Sets the center coordinates of the point ball
     * @param point the x and y coordinates to set the ball's center at
     */
    public void setPosition(Point point) {
        this.center = point;
    }
}

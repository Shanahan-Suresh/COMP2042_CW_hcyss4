/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021 Shanahan Suresh
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.comp2042_cw_hcyss4.Model;

import java.awt.*;
import com.comp2042_cw_hcyss4.Model.Balls.Ball;

/**
 * Player Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class Player {

    /**
     * The border color of the player
     */
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();

    /**
     * The inner color of the player
     */
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * Player constructor to create the Player object
     * @param ballPoint the ball position
     * @param width the player's container width
     * @param height the player container height
     * @param container the player's container, in the shape of a rectangle
     */
    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    private Rectangle makeRectangle(int width,int height){
        Point point = new Point( (int)(ballPoint.getX()-(width / 2)), (int)ballPoint.getY());
        return  new Rectangle(point, new Dimension(width, height));
    }

    /** Method to check for imapct between the player and the ball
     * @param ball the ball object
     * @return returns true if ball impacts with player
     */
    public boolean impact(Ball ball){
        return playerFace.contains(ball.getPosition()) && playerFace.contains(ball.getDown()) ;
    }

    /**
     * Method to control the player's movement
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x-(int)playerFace.getWidth() / 2,ballPoint.y);
    }

    /**
     * Method to move the player left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Method to move the player right
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Method to stop the player's movements
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * Returns the player's shape
     * @return player shaper in the form of a rectangle
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * Method to move the player to a specific coordinate
     */
    public void moveTo(Point point){
        ballPoint.setLocation(point);
        playerFace.setLocation(ballPoint.x-(int)playerFace.getWidth() / 2, ballPoint.y);
    }
}

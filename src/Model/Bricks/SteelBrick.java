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
package Model.Bricks;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.awt.Point;

/**
 * Steel Brick Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class SteelBrick extends Brick {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd = new Random();
    private Shape brickFace = super.brickFace;

    /**
     * Constructor to be called when creating Steel Brick objects
     * @param point X and Y coordinates of the brick
     * @param size size of the Brick
     */
    public SteelBrick(Point point, Dimension size){
        super(point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
    }


    /**
     * Detects if there is an impact on the brick, calls crack.makeCrack if brick is not broken
     * @param point contains the point of impact
     * @param direction  direction of brick that is impacted
     * @return false
     */
    public boolean setImpact(Point2D point , int direction){
        if(super.isBroken())
            return false;

        impact();
        return super.isBroken();
    }

    /**
     * Calls impact() method of parent to impact the brick if the random value is lower than steel's probability
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    /** Method to get the shape of the brick
     * @return the brick's shape
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /** Method to get the strength of the brick
     * @return the brick's strength
     */
    public int getStrength() {
        return STEEL_STRENGTH;
    }
}

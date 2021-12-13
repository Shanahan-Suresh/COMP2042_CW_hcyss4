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


public class SteelBrick extends Brick {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd = new Random();
    private Shape brickFace = super.brickFace;

    public SteelBrick(Point point, Dimension size){
        super(point, size, DEF_BORDER, DEF_INNER, STEEL_STRENGTH);
    }


    public boolean setImpact(Point2D point , int direction){
        if(super.isBroken())
            return false;

        impact();
        return super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    public int getStrength() {
        return STEEL_STRENGTH;
    }
}

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
package Model.Walls;

import Model.Balls.*;
import Model.Bricks.*;
import Model.Player;
import static Model.Walls.LevelCreate.*;
import static Model.Walls.WallType.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;



public class Wall {

    private static final int LEVELS_COUNT = 5;

    private Random rnd = new Random();
    private Rectangle area;

    public Brick[] bricks;
    public Crack crack;
    public Ball ball;
    public Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;

    private int brickCount;
    private static int brokenBrickCount = 0;

    private int ballCount;
    private boolean ballLost;

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPosition){

        this.startPoint = new Point(ballPosition);

        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        makeBall(ballPosition);
        int speedX, speedY;

        do{
            speedX = rnd.nextInt(5) - 2;
        }
        while (speedX == 0);

        do{
            speedY = -rnd.nextInt(3);
        }
        while(speedY == 0);

        ball.setSpeed(speedX, speedY);

        player = new Player((Point) ballPosition.clone(),150,10, drawArea);

        area = drawArea;
    }

    private void makeBall(Point2D ballPosition){
        ball = new RubberBall(ballPosition);
    }

    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        Brick[][] temp = new Brick[LEVELS_COUNT][];

        temp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
        temp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        temp[2] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        temp[3] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        temp[4] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, DIAMOND);

        return temp;
    }

    public void move(){
        player.move();
        ball.move();
    }

    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
            brokenBrickCount++;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    private boolean impactWall(){

        for(Brick b : bricks){
            switch(b.findImpact(ball)) {

                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), crack.UP);

                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUp(), crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRight(), crack.RIGHT);

                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeft(), crack.LEFT);
            }
        }

        return false;
    }

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;

        do{
            speedX = rnd.nextInt(5) - 2;
        }
        while(speedX == 0);

        do{
            speedY = -rnd.nextInt(3);
        }
        while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    public void setBallXSpeed(int speed){
        ball.setSpeedX(speed);
    }

    public void setBallYSpeed(int speed){
        ball.setSpeedY(speed);
    }

    public void resetBallCount(){
        ballCount = 3;
    }

    public static void setBrokenBrickCount(int count) {
        brokenBrickCount = count;
    }
    public static int getBrokenBrickCount() {
        return brokenBrickCount;
    }

}

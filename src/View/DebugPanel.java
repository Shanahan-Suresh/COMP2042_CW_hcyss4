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
package View;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import Model.Walls.Wall;

/**
 * DebugPanel Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;

    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private Wall wall;

    /**
     * Debug Panel constructor to create the Debug Panel object, handles most of debug menu logic
     * @param wall the wall object
     */
    public DebugPanel(Wall wall){

        this.wall = wall;

        initialize();

        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);
    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * Set the ball's x and y movement speed via debug slider
     * @param x the ball's movement speed on the x-axis
     * @param y the ball's movement speed on the y-axis
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

    /**
     * Get the ball's x-axis movement speed
     * @retunn the ball's movement speed on the x-axis
     */
    public JSlider getBallSpeedX(){
        return ballXSpeed;
    }

    /**
     * Get the ball's y-axis movement speed
     * @retunn the ball's movement speed on the y-axis
     */
    public JSlider getBallSpeedY(){
        return ballYSpeed;
    }

}

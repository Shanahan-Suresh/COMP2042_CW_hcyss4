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
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Controller.GameBoard;
import Model.Walls.Wall;
import Model.Balls.Ball;

/**
 * Debug Console Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;


    /**
     * Debug Console constructor method to generate the Debug Console object
     * @param owner the game frame, used to link to other menus
     * @param wall the wall object, sets the level
     * @param gameBoard the gameboard, to handle logic
     */
    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel, BorderLayout.CENTER);

        this.pack();
    }

    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x, y);
    }

    /**
     * Method to reactivate the game window, reset ball speed to original value
     * @param windowEvent the event of the game window
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball ball = wall.ball;
        debugPanel.setValues(ball.getSpeedX(), ball.getSpeedY());
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * Method to close the game window, repaints via gameBoard
     * @param windowEvent the event of the game window
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}

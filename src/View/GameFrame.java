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
import java.awt.event.WindowFocusListener;

import Controller.*;

/**
 * Game Frame Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private HighScore highScorePage;
    private InstructionMenu instructionMenu;

    private boolean gaming;

    /**
     * Constructor method to create the Game Frame object via JFrame class
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);
        homeMenu = new HomeMenu(this, new Dimension(450,300));
        instructionMenu = new InstructionMenu(this, new Dimension(450, 300));
        highScorePage = new HighScore(this, new Dimension(450,350));

        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
    }

    /**
     * Method to initialize the GameFrame
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * Method to enable the Game Board and start the game
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * Method to enable the Home Menu from another menu
     * @param menu a JComponent, which is the superclass of any other menu in this game
     */
    public void enableHomeMenu(JComponent menu) {
        this.dispose();
        this.remove(menu);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Method to enable the High Score page from another menu
     * @param menu a JComponent, which is the superclass of any other menu in this game
     */
    public void enableHighScorePage(JComponent menu){
        this.dispose();
        this.remove(menu);
        this.add(highScorePage, BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Method to enable the Instruction from the Home Menu
     */
    public void enableInstructionMenu(){
        this.dispose();
        this.remove(homeMenu);
        this.add(instructionMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    /**
     * Method to set that the GameBoard window has gained focus
     * @param windowEvent the window event status
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * Method to set that the GameBoard window has lost focus
     * @param windowEvent the window event status
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}

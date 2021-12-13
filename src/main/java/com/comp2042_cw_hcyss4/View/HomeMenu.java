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
package com.comp2042_cw_hcyss4.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Home Menu Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to";
    private static final String GAME_TITLE = "Brick Breaker";
    private static final String CREDITS = "Version 0.3";
    private static final String START_TEXT = "Start";
    private static final String EXIT_TEXT = "Exit";
    private static final String INSTRUCTIONS_TEXT = "Instructions";
    private static final String HIGHSCORE_TEXT = "High Scores";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle instructionButton;
    private Rectangle exitButton;
    private Rectangle highsScoreButton;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean exitClicked;
    private boolean instructionClicked;
    private boolean highScoreClicked;


    /**
     * Constructor method to create the home menu
     * @param owner the game frame, used to link to other menus
     * @param area the menu's dimensions
     */
    public HomeMenu(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension buttonDimension = new Dimension(area.width / 2, area.height / 12);
        startButton = new Rectangle(buttonDimension);
        instructionButton = new Rectangle(buttonDimension);
        highsScoreButton = new Rectangle(buttonDimension);
        exitButton = new Rectangle(buttonDimension);
        
        greetingsFont = new Font("Noto Mono", Font.PLAIN, 25);
        gameTitleFont = new Font("Noto Mono", Font.BOLD, 40);
        creditsFont = new Font("Monospaced", Font.PLAIN, 10);
        buttonFont = new Font("Monospaced", Font.PLAIN, startButton.height-2);
    }


    /**
     * Method to paint the home menu, calls drawMenu
     * @param g the graphics renderer
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    private void drawBackground(Graphics2D g){
        Image background = Toolkit.getDefaultToolkit().getImage("src/main/resources/BG.png");
        g.drawImage(background,0,0, getWidth(), getHeight(), this);
    }

    /**
     * Method to draw the home menu
     * @param g2d the graphics renderer (2D)
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawContainer(Graphics2D g2d){
        drawBackground(g2d);
    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,fontRenderContext);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,fontRenderContext);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,fontRenderContext);

        int setX, setY;

        setX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        setY = (int)(menuFace.getHeight() / 6);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS, setX, setY);

        setX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        setY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE, setX, setY);

        setX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        setY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS, setX, setY);
    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D startTextRectangle = buttonFont.getStringBounds(START_TEXT, fontRenderContext);
        Rectangle2D exitTextRectangle = buttonFont.getStringBounds(EXIT_TEXT, fontRenderContext);
        Rectangle2D instructionTextRectangle = buttonFont.getStringBounds(INSTRUCTIONS_TEXT, fontRenderContext);
        Rectangle2D highScoreTextRectangle = buttonFont.getStringBounds(HIGHSCORE_TEXT, fontRenderContext);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y = (int)((menuFace.height * 0.87 - startButton.height) * 0.6);

        //draw start button
        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - startTextRectangle.getWidth()) / 2;
        y = (int)(startButton.getHeight() - startTextRectangle.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);

        if(startClicked){
            Color temp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(temp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.23;

        //draw the instruction button
        instructionButton.setLocation(x,y);

        x = (int)(instructionButton.getWidth() - instructionTextRectangle.getWidth()) / 2;
        y = (int)(instructionButton.getHeight() - instructionTextRectangle.getHeight()) / 2;

        x += instructionButton.x;
        y += instructionButton.y + (instructionButton.height * 0.9);

        if(instructionClicked){
            Color temp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(instructionButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INSTRUCTIONS_TEXT, x, y);
            g2d.setColor(temp);
        }

        else{
            g2d.draw(instructionButton);
            g2d.drawString(INSTRUCTIONS_TEXT, x, y);
        }

        x = instructionButton.x;
        y = instructionButton.y;

        y *= 1.19;

        //draw high score button
        highsScoreButton.setLocation(x, y);

        x = (int)(highsScoreButton.getWidth() - highScoreTextRectangle.getWidth()) / 2;
        y = (int)(highsScoreButton.getHeight() - highScoreTextRectangle.getHeight()) / 2;

        x += highsScoreButton.x;
        y += highsScoreButton.y + (highsScoreButton.height * 0.9);

        if(highScoreClicked){
            Color temp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(highsScoreButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HIGHSCORE_TEXT, x, y);
            g2d.setColor(temp);
        }
        else{
            g2d.draw(highsScoreButton);
            g2d.drawString(HIGHSCORE_TEXT, x, y);
        }

        x = highsScoreButton.x;
        y = highsScoreButton.y;

        y *= 1.16;

        //draw exit button
        exitButton.setLocation(x,y);

        x = (int)(exitButton.getWidth() - exitTextRectangle.getWidth()) / 2;
        y = (int)(exitButton.getHeight() - exitTextRectangle.getHeight()) / 2;

        x += exitButton.x;
        y += exitButton.y + (startButton.height * 0.9);

        if(exitClicked){
            Color temp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(exitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT, x, y);
            g2d.setColor(temp);
        }
        else{
            g2d.draw(exitButton);
            g2d.drawString(EXIT_TEXT, x, y);
        }

    }

    /**
     * Method to determine that the mouse was clicked and calls the game frame to link to corresponding menu
     * @param event the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        if(startButton.contains(point)){
           owner.enableGameBoard();

        }
        else if(instructionButton.contains(point)) {
            owner.enableInstructionMenu();
        }
        else if(highsScoreButton.contains(point)) {
            owner.enableHighScorePage(this);
        }
        else if(exitButton.contains(point)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    /**
     * Method to determine that the mouse was pressed, changes clicked boolean value of corresponding button
     * @param event the mouse event
     */
    @Override
    public void mousePressed(MouseEvent event) {
        Point point = event.getPoint();
        if(startButton.contains(point)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(instructionButton.contains(point)) {
            instructionClicked = true;
            repaint(instructionButton.x,instructionButton.y,instructionButton.width+1,instructionButton.height+1);
        }
        else if(highsScoreButton.contains(point)) {
            highScoreClicked = true;
            repaint(highsScoreButton.x,highsScoreButton.y,highsScoreButton.width+1,highsScoreButton.height+1);
        }
        else if(exitButton.contains(point)){
            exitClicked = true;
            repaint(exitButton.x,exitButton.y,exitButton.width+1,exitButton.height+1);
        }
    }

    /**
     * Method to determine that the mouse click was release, changes clicked boolean value of corresponding button
     * @param event the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x, startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(instructionClicked) {
            instructionClicked = false;
            repaint(instructionButton.x, instructionButton.y, instructionButton.width + 1, instructionButton.height + 1);
        }
        else if(highScoreClicked) {
            highScoreClicked = false;
            repaint(highsScoreButton.x, highsScoreButton.y, highsScoreButton.width + 1, highsScoreButton.height + 1);
        }
        else if(exitClicked){
            exitClicked = false;
            repaint(exitButton.x, exitButton.y,exitButton.width+1,exitButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }


    @Override
    public void mouseDragged(MouseEvent event) {

    }

    /**
     * Method to determine that the mouse was moved, used to display special cursors
     * @param event the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        Point point = event.getPoint();
        if(startButton.contains(point) || exitButton.contains(point) || instructionButton.contains(point)
                || highsScoreButton.contains(point))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}

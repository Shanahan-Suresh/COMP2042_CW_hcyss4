/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2021  Shanahan Suresh
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
package com.comp2042_cw_hcyss4.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import com.comp2042_cw_hcyss4.Model.Balls.Ball;
import com.comp2042_cw_hcyss4.Model.Bricks.Brick;
import com.comp2042_cw_hcyss4.Model.Player;
import com.comp2042_cw_hcyss4.Model.Walls.Wall;
import com.comp2042_cw_hcyss4.View.DebugConsole;
import com.comp2042_cw_hcyss4.View.GameFrame;

/**
 * GameBoard Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class GameBoard extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private Wall wall;

    private String message;
    private String countMessage;
    private String scoreMessage;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;
    private GameFrame owner;


    /**
     * GameBoard constructor to create the GameBoard object, handles most of the game logic
     * @param owner the game frame, used to link to other menus
     */
    public GameBoard(GameFrame owner){
        super();
        this.owner = owner; //added to link to other pages

        strLen = 0;
        showPauseMenu = false;

        owner.setResizable(false);

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        this.initialize();
        message = " ";
        countMessage = " ";
        scoreMessage = " ";

        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner, wall, this);
        //initialize the first level
        wall.nextLevel();

        gameTimer = new Timer (10, e -> startTimer());
    }

    private void startTimer() {
        GameTimer.setGameStart(true);
        wall.move();
        wall.findImpacts();
        countMessage = String.format("Bricks Left: %d Balls Left: %d", wall.getBrickCount(), wall.getBallCount());
        scoreMessage = String.format("Bricks Broken: %d Time: %02dm %02ds", Wall.getBrokenBrickCount(),
                GameTimer.getMinutes(), GameTimer.getSeconds());

        if (wall.isBallLost()) {

            if (wall.ballEnd()) {
                wall.wallReset();
                message = "Game over";
                GameTimer.setGameStart(false);
                HighScore.calculateScore();
                owner.enableHighScorePage(this);
            }

            wall.ballReset();
            gameTimer.stop();
            GameTimer.setGameStart(false);
        }

        else if (wall.isDone()) {

            if (wall.hasLevel()) {
                message = "Go to Next Level";
                gameTimer.stop();
                wall.ballReset();
                wall.wallReset();
                wall.nextLevel();
                GameTimer.setSecondsCounter(GameTimer.getSeconds());
                GameTimer.setMinutesCounter(GameTimer.getMinutes());
            }
            else {
                message = "ALL WALLS DESTROYED";
                gameTimer.stop();
                GameTimer.setGameStart(false);
            }
        }
        repaint();
    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        new GameTimer();
    }


    /**
     * Method to draw the gameboard scene
     * @param g the graphics controller
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);

        g2d.drawString(message,275,240);
        g2d.drawString(countMessage,225,225);
        g2d.drawString(scoreMessage,225,250);

        drawBall(wall.ball, g2d);

        for(Brick brick : wall.bricks)
            if(!brick.isBroken())
                drawBrick(brick, g2d);

        drawPlayer(wall.player, g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color temp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(), getHeight());
        g2d.setColor(temp);
    }

    private void drawBrick(Brick brick, Graphics2D g2d){
        Color temp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(temp);
    }

    private void drawBall(Ball ball, Graphics2D g2d){
        Color temp = g2d.getColor();

        Shape shape = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(shape);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(shape);

        g2d.setColor(temp);
    }

    private void drawPlayer(Player p, Graphics2D g2d){
        Color temp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(temp);
    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite temp = g2d.getComposite();
        Color tempColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, DEF_WIDTH, DEF_HEIGHT);

        g2d.setComposite(temp);
        g2d.setColor(tempColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tempFont = g2d.getFont();
        Color tempColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE, frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE, x, y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE, frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE, x, y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART, x, y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT, x, y);
        g2d.setFont(tempFont);
        g2d.setColor(tempColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Method to detect the key pressed and call its corresponding intended method
     * @param keyEvent the pressed key
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){

            case KeyEvent.VK_A:
                wall.player.moveLeft();
                break;

            case KeyEvent.VK_D:
                wall.player.moveRight();
                break;

            case KeyEvent.VK_ESCAPE:

                showPauseMenu = !showPauseMenu;
                GameTimer.setGameStart(false);
                message = "Focus Lost";
                scoreMessage = " ";
                countMessage = " ";
                repaint();
                gameTimer.stop();
                break;

            case KeyEvent.VK_SPACE:

                if(!showPauseMenu)

                    if(gameTimer.isRunning()) {
                        GameTimer.setGameStart(false);
                        message = "Paused";
                        scoreMessage = " ";
                        countMessage = " ";
                        repaint();
                        gameTimer.stop();
                    }

                    else {
                        message = " ";
                        gameTimer.start();
                    }
                break;

            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);

            default:
                wall.player.stop();
        }
    }

    /**
     * Method to determine that the key is released, stops the player movements
     * @param keyEvent the key
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    /**
     * Method to determine that the mouse was clicked and calls its corresponding intended methods
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;

        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }

        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            GameTimer.setSeconds(GameTimer.getSecondsCounter());
            GameTimer.setMinutes(GameTimer.getMinutesCounter());
            repaint();
        }

        else if(exitButtonRect.contains(p)){
            GameTimer.gameReset();
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Method to determine that the mouse was moved, used to display special cursors
     * @param mouseEvent the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {

            if (exitButtonRect.contains(point) || continueButtonRect.contains(point) || restartButtonRect.contains(point))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());

        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Method to pause/stop the game
     */
    public void onLostFocus(){
        gameTimer.stop();
        GameTimer.setGameStart(false);
        message = "Focus Lost";
        scoreMessage = " ";
        countMessage = " ";
        repaint();
    }
}

package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class InstructionMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String INSTRUCTIONS_TITLE = "How to play";
    private static final String INSTRUCTION_MAIN = "Goal - Break all the bricks without running out of balls";
    private static final String LEFTMOVE_TEXT = "Press 'A' to move left";
    private static final String RIGHTMOVE_TEXT = "Press 'D' to move right";
    private static final String PAUSE_TEXT = "Hit the 'Spacebar' to pause, 'Esc' to bring up pause menu";
    private static final String DEBUG_CONSOLE_TEXT = "Open up the debug console panel using (Shift + Alt + F1)";
    private static final Color TEXT_COLOR = new Color(54, 201, 73);
    private static final Color CLICKED_BUTTON = Color.BLUE;
    private static final String RETURN_TEXT = "Return";

    private Font instructionTitleFont;
    private Font buttonFont;
    private Font textFont;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 300;

    private Rectangle menuFace;
    private Rectangle returnButton;
    private boolean returnClicked;

    private GameFrame owner;
    private Image background;


    public InstructionMenu(GameFrame owner, Dimension area) {

        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        menuFace = new Rectangle(new Point(0,0),area);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;
        owner.setResizable(false);

        Dimension buttonDimension = new Dimension(area.width / 4, area.height / 12);
        returnButton = new Rectangle(buttonDimension);

        instructionTitleFont = new Font("Consolas",Font.PLAIN,40);
        textFont = new Font("Consolas", Font.PLAIN, 16);
        buttonFont = new Font("Monospaced",Font.PLAIN, returnButton.height-2);
    }

    private void drawBackground(Graphics2D g){
        background = Toolkit.getDefaultToolkit().getImage("src/Model/Resources/BG2.png");
        g.drawImage(background,0,0, getWidth(), getHeight(), this);
    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawBackground(g2d);
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawTitle(g2d);
        drawLeftMoveInstructions(g2d);
        drawInstructionMain(g2d);
        drawRightMoveInstructions(g2d);
        drawPauseInstructions(g2d);
        drawDebugConsoleInstructions(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }


    private void drawTitle(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = instructionTitleFont.getStringBounds(INSTRUCTIONS_TITLE, fontRenderContext);
        int xTitle = (int)(menuFace.getWidth() - instructionsRect.getWidth()) / 6;
        int yTitle = (int)(menuFace.getHeight() / 8);

        g2d.setFont(instructionTitleFont);
        g2d.drawString(INSTRUCTIONS_TITLE, xTitle, yTitle);
    }

    private void drawInstructionMain(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(INSTRUCTION_MAIN, fontRenderContext);
        int xText = (int)(menuFace.getWidth() * 0.64 - instructionsRect.getWidth() / 2);
        int yText = (int)(menuFace.getHeight() / 4.35);

        g2d.setFont(textFont);
        g2d.drawString(INSTRUCTION_MAIN, xText, yText);
    }


    private void drawLeftMoveInstructions(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(LEFTMOVE_TEXT, fontRenderContext);
        int xText = (int)(menuFace.getWidth() - instructionsRect.getWidth()) / 6;
        int yText = (int)(menuFace.getHeight() / 3);

        g2d.setFont(textFont);
        g2d.drawString(LEFTMOVE_TEXT, xText, yText);
    }


    private void drawRightMoveInstructions(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(RIGHTMOVE_TEXT, fontRenderContext);
        int xText = (int)(menuFace.getWidth() - instructionsRect.getWidth()) / 6;
        int yText = (int)(menuFace.getHeight() / 2.5);

        g2d.setFont(textFont);
        g2d.drawString(RIGHTMOVE_TEXT, xText, yText);
    }


    private void drawPauseInstructions(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(PAUSE_TEXT, fontRenderContext);
        int xText = (int)(menuFace.getWidth() * 1.3 - instructionsRect.getWidth()) / 2;
        int yText = (int)(menuFace.getHeight() / 1.83);

        g2d.setFont(textFont);
        g2d.drawString(PAUSE_TEXT, xText, yText);
    }


    private void drawDebugConsoleInstructions(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D instructionsRect = textFont.getStringBounds(DEBUG_CONSOLE_TEXT, fontRenderContext);
        int xText = (int)(menuFace.getWidth() * 0.635 - instructionsRect.getWidth() / 2);
        int yText = (int)(menuFace.getHeight() / 1.5);

        g2d.setFont(textFont);
        g2d.drawString(DEBUG_CONSOLE_TEXT, xText, yText);
    }


    private void drawButton(Graphics2D g2d) {
        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(RETURN_TEXT, fontRenderContext);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - returnButton.width) / 8;
        int y = (int) (menuFace.height * 0.87 - returnButton.height) ;

        //draw return button
        returnButton.setLocation(x,y);

        x = (int)(returnButton.getWidth() - buttonTextRect.getWidth()) / 2;
        y = (int)(returnButton.getHeight() - buttonTextRect.getHeight()) / 2;

        x += returnButton.x;
        y += returnButton.y + (returnButton.height * 0.9);

        if(returnClicked){
            Color tmp = g2d.getColor();
            g2d.draw(returnButton);
            g2d.setColor(CLICKED_BUTTON);
            g2d.drawString(RETURN_TEXT, x, y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(returnButton);
            g2d.drawString(RETURN_TEXT, x, y);
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        if(returnButton.contains(point)){
            owner.enableHomeMenu(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        if(returnButton.contains(point)){
            returnClicked = true;
            repaint(returnButton.x, returnButton.y,returnButton.width+1,returnButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(returnClicked){
            returnClicked = false;
            repaint(returnButton.x, returnButton.y,returnButton.width+1,returnButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();

        if(returnButton.contains(point))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}
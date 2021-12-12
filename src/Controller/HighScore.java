package Controller;

import Model.Walls.Wall;
import View.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class HighScore extends JComponent implements MouseListener, MouseMotionListener {

    private Rectangle menuFace;
    private Rectangle returnButton;

    private Font buttonFont;
    private Font greetingFont;
    private Font textFont;
    private Font scoreFont;

    private BasicStroke borderStoke;

    private String scoreText;

    private GameFrame owner;

    private Image image;
    
    private static int i, j;
    private static int[][] score;

    private boolean returnClicked;
    private static final Color CLICKED_BUTTON = Color.GREEN;
    private static final String RETURN_TEXT = "Return";

    public HighScore(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension buttonDimension = new Dimension(area.width / 3, area.height / 13);
        returnButton = new Rectangle(buttonDimension);

        borderStoke = new BasicStroke(2);

        setScore(new int[10][3]);

        greetingFont = new Font("Noto Mono", Font.BOLD,50);
        textFont = new Font("Consolas", Font.PLAIN,15);
        scoreFont = new Font("Consolas", Font.PLAIN,18);
        buttonFont = new Font("Monospaced", Font.PLAIN,20);

        fileRead();
    }

    public static void fileRead(){
        i = 0;
        try{
            File file = new File("src/Model/Resources/HighScore.txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            
            while (scanner.hasNextLine()){
                for (j = 0; j < 3; j++){
                    getScore()[i][j] = scanner.nextInt();
                }
                i++;
            }
            scanner.close();

        } catch (FileNotFoundException e){
            System.out.println("High score file could not be opened");
            e.printStackTrace();
        }
    }

    public static void fileWrite(){
        
        try {
            FileWriter file = new FileWriter("src/Model/Resources/HighScore.txt");
            file.write("Bricks\tMinutes\tSeconds");

            for(i = 0; i < 9; i++){
                if(getScore()[i][0] == 0 && getScore()[i][1] == 0 && getScore()[i][2] == 0){
                    break;
                }
                file.write("\n" + getScore()[i][0] + "\t" + getScore()[i][1] + "\t" + getScore()[i][2]);
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("High score file could not be written to");
            e.printStackTrace();
        }
    }

    public static void calculateScore(){
        int bricks, Minutes, Seconds;

        if(getScore()[9][0] < Wall.getBrokenBrickCount()){

            getScore()[9][0] = Wall.getBrokenBrickCount();
            getScore()[9][1] = GameTimer.getMinutes();
            getScore()[9][2] = GameTimer.getSeconds();
        }

        for (i = 0; i < 9; i++) {

            for (j = i + 1; j < 9; j++) {
                if (getScore()[j][0] > getScore()[i][0]) {
                    bricks = getScore()[i][0];
                    Minutes = getScore()[i][1];
                    Seconds = getScore()[i][2];

                    getScore()[i][0] = getScore()[j][0];
                    getScore()[i][1] = getScore()[j][1];
                    getScore()[i][2] = getScore()[j][2];

                    getScore()[j][0] = bricks;
                    getScore()[j][1] = Minutes;
                    getScore()[j][2] = Seconds;
                }
            }
        }

        for (i = 0; i < 9; i++) {

            for (j = i + 1; j < 9; j++) {
                if (getScore()[j][0] == getScore()[i][0] && getScore()[j][1] < getScore()[i][1]) {
                    bricks = getScore()[i][0];
                    Minutes = getScore()[i][1];
                    Seconds = getScore()[i][2];

                    getScore()[i][0] = getScore()[j][0];
                    getScore()[i][1] = getScore()[j][1];
                    getScore()[i][2] = getScore()[j][2];

                    getScore()[j][0] = bricks;
                    getScore()[j][1] = Minutes;
                    getScore()[j][2] = Seconds;
                }
            }
        }

        for (i = 0; i < 9; i++) {

            for (j = i + 1; j < 9; j++) {
                if (getScore()[j][0] == getScore()[i][0] && getScore()[j][1] == getScore()[i][1] && getScore()[j][2] < getScore()[i][2]) {
                    bricks = getScore()[i][0];
                    Minutes = getScore()[i][1];
                    Seconds = getScore()[i][2];

                    getScore()[i][0] = getScore()[j][0];
                    getScore()[i][1] = getScore()[j][1];
                    getScore()[i][2] = getScore()[j][2];

                    getScore()[j][0] = bricks;
                    getScore()[j][1] = Minutes;
                    getScore()[j][2] = Seconds;
                }
            }
        }

        fileWrite();
    }

    public static int[][] getScore() {
        return score;
    }

    public static void setScore(int[][] score) {
        HighScore.score = score;
    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


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
        try {
            image = Toolkit.getDefaultToolkit().getImage("src/Model/Resources/BG3.png");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }


    private void drawText(Graphics2D g2d){

        g2d.setColor(Color.ORANGE);

        FontRenderContext frc = g2d.getFontRenderContext();

        scoreText = String.format("No.%d %d Bricks, %d Minutes %d Seconds", i+1, getScore()[i][0], getScore()[i][1],
                getScore()[i][2]);

        Rectangle2D greetingsRect = greetingFont.getStringBounds("HIGH SCORES",frc);
        Rectangle2D scoreRect = textFont.getStringBounds(scoreText,frc);

        int setX, setY;

        setX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        setY = (int)(menuFace.getHeight() / 5);

        g2d.setFont(greetingFont);
        g2d.drawString("HIGH SCORES", setX, setY);

        setY += (int) scoreRect.getHeight() * 0.5;

        g2d.setFont(textFont);
        g2d.setColor(Color.WHITE);

        for(i = 0; i < 7; i++){
            if(getScore()[i][0] == 0 && getScore()[i][1] == 0 && getScore()[i][2] == 0){
                break;
            }

            setX = (int)(menuFace.getWidth() - scoreRect.getWidth()) / 2;
            setY += (int) scoreRect.getHeight() * 1.1;

            scoreText = String.format("No(%d) %d Bricks, %d Minutes %d Seconds", i+1,
                    getScore()[i][0], getScore()[i][1], getScore()[i][2]);

            g2d.drawString(scoreText,setX,setY);
        }

        setX = (int) ((menuFace.width * 0.82 - returnButton.width) / 2 * 0.2);
        setY = (int) ((menuFace.height - returnButton.height) * 0.75 * 1.1);

        scoreText = String.format("Score: %02d Bricks, %02d Minutes %02d Seconds", Wall.getBrokenBrickCount()
                , GameTimer.getMinutes(), GameTimer.getSeconds());

        g2d.setFont(scoreFont);
        g2d.setColor(Color.CYAN);
        g2d.drawString(scoreText, setX, setY);

    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext fontRenderContext = g2d.getFontRenderContext();

        Rectangle2D buttonTextRect = buttonFont.getStringBounds(RETURN_TEXT, fontRenderContext);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - returnButton.width) / 2;
        int y = (int) (menuFace.height * 0.94 - returnButton.height) ;

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
    public void mouseClicked(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(returnButton.contains(point)){
            owner.enableHomeMenu(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(returnButton.contains(point)) {
            returnClicked = true;
            repaint(returnButton.x, returnButton.y, returnButton.width + 1, returnButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(returnClicked){
            returnClicked = false;
            repaint(returnButton.x,returnButton.y,returnButton.width+1,returnButton.height+1);
        }
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        if(returnButton.contains(point))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
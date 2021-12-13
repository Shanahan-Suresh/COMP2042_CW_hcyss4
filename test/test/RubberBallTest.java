package test;

import Model.Balls.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {
    RubberBall rubberBall = new RubberBall(new Point());

    @Test
    public void setRubberBallCenter(){
        rubberBall.setPosition(new Point(10,15));
        assertEquals(10,rubberBall.getPosition().getX()); //verify ball X position
        assertEquals(15,rubberBall.getPosition().getY()); //verify ball Y position
    }

    @Test
    public void moveRubberBall(){
        rubberBall.setSpeedX(10);
        rubberBall.setSpeedY(10);
        rubberBall.move();
        assertEquals(5,rubberBall.getBallFace().getBounds().getX()); //verify moving ball's X position
        assertEquals(5,rubberBall.getBallFace().getBounds().getY()); //verify moving ball's Y position
    }

    @Test
    public void moveRubberBallTo(){
        rubberBall.moveTo(new Point(10,15));
        assertEquals(5,rubberBall.getBallFace().getBounds().getX()); //verify ball moves to X position
        assertEquals(10,rubberBall.getBallFace().getBounds().getY()); //verify ball moves to Y position
    }

    @Test
    public void setRubberBallSpeed(){
        rubberBall.setSpeed(10,15); //set speed method
        assertEquals(10, rubberBall.getSpeedX()); //verify ball x speed
        assertEquals(15, rubberBall.getSpeedY()); //verify ball y speed
    }

    @Test
    public void setRubberBallSpeedX(){
        rubberBall.setSpeedX(10); //set speedX method
        assertEquals(10, rubberBall.getSpeedX()); //verify ball x speed
    }

    @Test
    public void setRubberBallSpeedY(){
        rubberBall.setSpeedY(10); //set speedY method
        assertEquals(10, rubberBall.getSpeedY()); //verify ball y speed
    }

    @Test
    public void reverseRubberBallSpeedX(){
        rubberBall.setSpeedX(10);
        rubberBall.reverseX();
        assertEquals(-10, rubberBall.getSpeedX()); //verify X axis reverse is working
        rubberBall.reverseX();
        assertEquals(10, rubberBall.getSpeedX()); //verify X axis reverse is working
    }

    @Test
    public void reverseRubberBallSpeedY(){
        rubberBall.setSpeedY(10);
        rubberBall.reverseY();
        assertEquals(-10, rubberBall.getSpeedY()); //verify Y axis reverse is working
        rubberBall.reverseY();
        assertEquals(10, rubberBall.getSpeedY()); //verify Y axis reverse is working
    }

}
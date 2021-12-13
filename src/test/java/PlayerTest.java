import com.comp2042_cw_hcyss4.Model.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player(new Point(300,430),150,10,new Rectangle(0,0,600,450));

    @Test
    public void movePlayerTo(){
        player.moveTo(new Point (100,430));
        assertEquals(25, player.getPlayerFace().getBounds().getX()); //verify player X movement
        assertEquals(430, player.getPlayerFace().getBounds().getY()); //verify player Y movement
    }

    @Test
    public void movePlayerLeft(){
        player.moveLeft();
        player.move();
        assertEquals(220, player.getPlayerFace().getBounds().getX()); //verify player X movement
        assertEquals(430, player.getPlayerFace().getBounds().getY()); //verify player Y movement
    }

    @Test
    public void movePlayerRight(){
        player.moveRight();
        player.move();
        assertEquals(230, player.getPlayerFace().getBounds().getX()); //verify player X movement
        assertEquals(430, player.getPlayerFace().getBounds().getY()); //verify player Y movement
    }

    @Test
    public void stopPlayer(){
        player.stop();
        player.move();
        assertEquals(225, player.getPlayerFace().getBounds().getX()); //verify player X movement
        assertEquals(430, player.getPlayerFace().getBounds().getY()); //verify player Y movement
    }

}
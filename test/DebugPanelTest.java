import Model.Walls.Wall;
import View.DebugPanel;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DebugPanelTest {
    Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,
            new Point(300,430));
    DebugPanel debugPanel = new DebugPanel(wall);

    @Test
    public void setSliderValues(){
        debugPanel.setValues(1,1);
        assertEquals(1, debugPanel.getBallSpeedX().getValue()); //verify slider to ball x-value
        assertEquals(1, debugPanel.getBallSpeedY().getValue()); //verify slider to ball y-value
    }
}
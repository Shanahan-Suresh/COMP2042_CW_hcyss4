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
import java.awt.*;

/**
 * Graphics Main Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class GraphicsMain {

    /**
     * The main method used to start the game
     */
    public static void main(String[] args){

        EventQueue.invokeLater( () -> {
            try {
                new GameFrame().initialize();
            }

            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}

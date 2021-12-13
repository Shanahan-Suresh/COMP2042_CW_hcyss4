package com.comp2042_cw_hcyss4.Model.Walls;

import com.comp2042_cw_hcyss4.Model.Bricks.CementBrick;
import com.comp2042_cw_hcyss4.Model.Bricks.ClayBrick;
import com.comp2042_cw_hcyss4.Model.Bricks.DiamondBrick;
import com.comp2042_cw_hcyss4.Model.Bricks.SteelBrick;

/**
 * Types of Bricks
 * @see ClayBrick
 * @see SteelBrick
 * @see CementBrick
 * @see DiamondBrick
 */
enum WallType {
    CLAY,
    STEEL,
    CEMENT,
    DIAMOND
}
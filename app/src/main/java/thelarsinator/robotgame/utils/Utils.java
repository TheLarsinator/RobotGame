package thelarsinator.robotgame.utils;

import thelarsinator.robotgame.game.Tile.*;

/**
 * Created by Lars on 15.07.2016.
 */
public class Utils
{

    public Utils()
    {
    }

    public static TileType characterToTileType(char c)
    {
        if(c == 'p')
        {
            return TileType.PLATFORM;
        }
        else if(c == 'g')
        {
            return TileType.GRASS;
        }
        else if(c == 'd')
        {
            return TileType.DIRT;
        }
        else
            return TileType.AIR;
    }

}
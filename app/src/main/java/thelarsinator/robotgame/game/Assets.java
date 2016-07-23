package thelarsinator.robotgame.game;

import thelarsinator.robotgame.framework.Image;
import thelarsinator.robotgame.framework.Music;
import thelarsinator.robotgame.framework.Sound;

/**
 * Created by Lars on 15.07.2016.
 */
public class Assets
{
    public static Image image, background;

    public static Image charJump, charDuck, charWalk, charDead;
    public static Image heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    public static Image grassTile, oceanTile, dirtTile, platformTile;

    public static Image button, menu, splash;

    public static Sound click;
    public static Music theme;

    public static void load(MainActivity m)
    {
        theme = m.getAudio().createMusic("menutheme.mp3");
        theme.setLooping(true);
        theme.setVolume(0.85f);
        theme.play();
    }
}

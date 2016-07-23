package thelarsinator.robotgame.game;

import thelarsinator.robotgame.framework.Game;
import thelarsinator.robotgame.framework.Graphics;
import thelarsinator.robotgame.framework.Graphics.ImageFormat;
import thelarsinator.robotgame.framework.Screen;

/**
 * Created by Lars on 15.07.2016.
 */
public class LoadingScreen extends Screen
{
    public LoadingScreen(Game game)
    {
        super(game);
    }

    @Override
    public void update(float deltaTime)
    {
        Graphics g = game.getGraphics();

        //Load all the assets...
        //Backdrops
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.background = g.newImage("background.png", ImageFormat.RGB565);

        //Player
        Assets.charWalk = g.newImage("robot.png", ImageFormat.ARGB4444);
        Assets.charJump = g.newImage("robot_jump.png", ImageFormat.ARGB4444);
        Assets.charDuck = g.newImage("robot_duck.png", ImageFormat.ARGB4444);
        Assets.charDead = g.newImage("robot_dead.png", ImageFormat.ARGB4444);

        //Heliboy
        Assets.heliboy = g.newImage("heliboy.png", ImageFormat.ARGB4444);
        Assets.heliboy2 = g.newImage("heliboy2.png", ImageFormat.ARGB4444);
        Assets.heliboy3  = g.newImage("heliboy3.png", ImageFormat.ARGB4444);
        Assets.heliboy4  = g.newImage("heliboy4.png", ImageFormat.ARGB4444);
        Assets.heliboy5  = g.newImage("heliboy5.png", ImageFormat.ARGB4444);

        //Tiles
        Assets.grassTile = g.newImage("grasstile.png", ImageFormat.RGB565);
        Assets.dirtTile = g.newImage("dirttile.png", ImageFormat.RGB565);
        Assets.platformTile = g.newImage("platformtile.png", ImageFormat.RGB565);
        Assets.oceanTile = g.newImage("oceantile.png", ImageFormat.RGB565);

        //buttons
        Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);

        //The screen will show the loading splash while this happends....

        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.splash, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}

package thelarsinator.robotgame.game;

import thelarsinator.robotgame.framework.Game;
import thelarsinator.robotgame.framework.Graphics;
import thelarsinator.robotgame.framework.Screen;

/**
 * Created by Lars on 15.07.2016.
 */
public class SplashLoadingScreen extends Screen
{
    public SplashLoadingScreen(Game game)
    {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.splash = g.newImage("splash.jpg", Graphics.ImageFormat.RGB565);

        game.setScreen(new LoadingScreen(game));
    }

    @Override
    public void paint(float deltaTime) {

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

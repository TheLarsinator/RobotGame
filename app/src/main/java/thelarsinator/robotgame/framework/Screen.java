package thelarsinator.robotgame.framework;

/**
 * Created by Lars on 15.07.2016.
 */
public abstract class Screen
{
    protected final Game game;

    public Screen(Game game)
    {
        this.game = game;
    }

    public abstract void update(float deltaTime);

    public abstract void paint(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void backButton();
}

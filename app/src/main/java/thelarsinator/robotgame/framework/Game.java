package thelarsinator.robotgame.framework;

/**
 * Created by Lars on 15.07.2016.
 */
public interface Game
{
    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
}

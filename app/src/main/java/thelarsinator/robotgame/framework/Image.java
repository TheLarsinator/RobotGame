package thelarsinator.robotgame.framework;

/**
 * Created by Lars on 15.07.2016.
 */
public interface Image
{
    public int getWidth();
    public int getHeight();
    public Graphics.ImageFormat getFormat();
    public void dispose();
}

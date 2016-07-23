package thelarsinator.robotgame.game;

/**
 * Created by Lars on 15.07.2016.
 */
public class Background
{
    private int bgX, bgY, speedX, imageWidth;


    public Background(int x, int y, int imageWidth)
    {
        this.bgX = x;
        this.bgY = y;
        this.speedX = 0;
        this.imageWidth = imageWidth;
    }

    public void update()
    {
        bgX += this.speedX;

        if(bgX <= -imageWidth)
        {
            bgX += 2*imageWidth;
        }

        System.out.println("Scrolling");
    }

    public int getBgX()
    {
        return bgX;
    }

    public int getBgY()
    {
        return bgY;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getImageWidth()
    {
        return imageWidth;
    }

    public void setBgX(int bgX)
    {
        this.bgX = bgX;
    }

    public void setBgY(int bgY)
    {
        this.bgY = bgY;
    }

    public void setSpeedX(int sX)
    {
        this.speedX = sX;
    }

    public void setImageWidth(int imageWidth)
    {
        this.imageWidth = imageWidth;
    }

}

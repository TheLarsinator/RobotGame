package thelarsinator.robotgame.game;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Lars on 15.07.2016.
 */
public class HeliBoy extends Enemy
{
    private boolean up = false;
    private int upCount = 0;

    public HeliBoy(int centerX, int centerY)
    {
        setCenterX(centerX);
        setCenterY(centerY);
        setSizeX(96);
        setSizeY(80);
        setMaxHealth(1);
        setCurrentHealth(1);
        setHitBox(new Rect(0, 0, 0, 0));
    }

    @Override
    public void update()
    {
        Random rand = new Random();
        if(!up)
        {
            this.setSpeedY(rand.nextInt(3));
            upCount++;
        }
        else
        {
            this.setSpeedY(-rand.nextInt(3));
            upCount++;
        }

        if(upCount == 10)
        {
            up = !up;
            upCount = 0;
        }

        if(getCenterX() < 800)
            attack();

        setHitBox(new Rect(centerX - sizeX/2, centerY - sizeY/2, sizeX, sizeY));
        super.update();
    }

    @Override
    public void attack()
    {
        Random rand = new Random();
        int a = rand.nextInt(100);
        if(a == 0)
        {
            Projectile p;
            p = new Projectile(getCenterX() - 35, getCenterY() + 7, true);
            p.setSpeedX(-7);
            projectiles.add(p);
        }
        super.attack();
    }
}

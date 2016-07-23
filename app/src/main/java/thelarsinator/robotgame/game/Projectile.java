package thelarsinator.robotgame.game;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Lars on 15.07.2016.
 */

public class Projectile
{

    private int x, y, speedX, speedY, power;
    private boolean visible;
    private boolean isEnemyProjectile;

    private ArrayList<Enemy> enemies = GameScreen.getEnemies();
    private Robot robot = GameScreen.getRobot();

    private Rect r;

    public Projectile(int x, int y, boolean isE)
    {
        this.x = x;
        this.y = y;
        speedX = 0;
        speedY = 0;
        power = 1;
        setEnemyProjectile(isE);
        visible = true;
        r = new Rect(0, 0, 0, 0);
    }

    public void update()
    {
        x += speedX;
        y += speedY;
        r.set(x, y, x+10, y+5);

        if (x < 0 || x > 800) // Left the screen on either side
        {
            visible = false;
        }

        checkCollision();

        if (!isEnemyProjectile)
        {
            for (int i = 0; i < enemies.size(); i++)
            {
                Enemy e = (Enemy) enemies.get(i);
                if (Rect.intersects(r, e.getHitBox()))
                {
                    e.getHit(this);
                }
            }
        } else
        {
            if (this.getX() > robot.getCenterX() - 61 && this.getX() < robot.getCenterX() + 61)
            {
                if (this.getY() > robot.getCenterY() - 63 && this.getY() < robot.getCenterY() + 63)
                {
                    robot.getHit(this);
                }
            }
        }
    }

    public void checkCollision()
    {
        if (isEnemyProjectile)
        {
            if (Rect.intersects(r, robot.upperBody) || Rect.intersects(r, robot.lowerBody) || Rect.intersects(r, robot.rightHand)
                    || Rect.intersects(r, robot.leftHand) || Rect.intersects(r, robot.footright) || Rect.intersects(r, robot.footleft))
            {
                visible = false;
                robot.getHit(this);
            }
        } else
        {
            for (int i = 0; i < enemies.size(); i++)
            {
                Enemy e = (Enemy) enemies.get(i);
                if (Rect.intersects(r, e.getHitBox()))
                {
                    visible = false;
                    e.getHit(this);
                }
            }
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getSpeedY()
    {
        return speedY;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setSpeedX(int speedX)
    {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY)
    {
        this.speedY = speedY;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public boolean isEnemyProjectile()
    {
        return isEnemyProjectile;
    }

    public void setEnemyProjectile(boolean isEnemyProjectile)
    {
        this.isEnemyProjectile = isEnemyProjectile;
    }

}


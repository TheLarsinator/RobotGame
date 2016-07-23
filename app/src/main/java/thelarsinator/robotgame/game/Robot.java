package thelarsinator.robotgame.game;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Lars on 15.07.2016.
 */

public class Robot
{
    private int moveSpeed = 5;
    private int jumpSpeed = -15;

    private int centerX = 100;
    private int centerY = 300;
    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;
    private boolean readyToFire = true;

    private int maxHealth, currentHealth;
    boolean isDead = false;

    private int speedX = 0;
    private int speedY = 0;
    public static Rect upperBody = new Rect(0, 0, 0, 0);
    public static Rect lowerBody = new Rect(0, 0, 0, 0);
    public static Rect rightHand = new Rect(0, 0, 0, 0);
    public static Rect leftHand = new Rect(0, 0, 0, 0);
    public static Rect footleft = new Rect(0,0,0,0);
    public static Rect footright = new Rect(0,0,0,0);
    public static Rect yellowRed = new Rect(0, 0, 0, 0);

    private static Background bg1 = GameScreen.getBg1();
    private static Background bg2 = GameScreen.getBg2();

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    public void update()
    {
        // Moves Character or Scrolls Background accordingly.
        if (speedX < 0)
        {
            centerX += speedX;

        } else if (speedX == 0)
        {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
        } else
        {
            if (centerX <= 150)
            {
                centerX += speedX;
            } else
            {
                bg1.setSpeedX(-moveSpeed/5);
                bg2.setSpeedX(-moveSpeed/5);
            }
        }

        // Update Y pos
        centerY += speedY;

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60)
        {
            centerX = 61;
        }

        // Handles Jumping
        speedY++;
        if(speedY > 3)
            jumped = true;

        upperBody.set(centerX - 34, centerY - 63, centerX + 34, centerY);
        lowerBody.set(upperBody.left, upperBody.top + 63, upperBody.left+68, upperBody.top + 128);
        rightHand.set(upperBody.left - 26, upperBody.top+32, upperBody.left, upperBody.top+52);
        leftHand.set(upperBody.left + 68, upperBody.top+32, upperBody.left+94, upperBody.top+52);
        yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
        footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
        footright.set(centerX, centerY + 20, centerX+50, centerY+35);
    }

    public void moveRight()
    {
        if (!ducked)
            speedX = moveSpeed;
    }

    public void moveLeft()
    {
        if (!ducked)
            speedX = -moveSpeed;
    }

    public void stop()
    {
        if (!isMovingRight() && !isMovingLeft())
            speedX = 0;

        if (isMovingRight() == false && isMovingLeft() == true)
            moveLeft();

        if (isMovingRight() == true && isMovingLeft() == false)
            moveRight();
    }

    public void stopRight()
    {
        setMovingRight(false);
        stop();
    }

    public void stopLeft()
    {
        setMovingLeft(false);
        stop();
    }

    public void jump()
    {
        if (jumped == false && !isDead)
        {
            if (ducked)
            {
                speedY = (int) (1.6 * jumpSpeed);
            } else
                speedY = jumpSpeed;
            jumped = true;
        }

    }

    public void shoot()
    {
        if (readyToFire)
        {
            if (!isDucked())
            {
                Projectile p;
                if (this.isJumped())
                {
                    p = new Projectile(centerX + 35, centerY - 40, false);
                    p.setSpeedY(-7);
                    p.setSpeedX(7);
                } else
                {
                    p = new Projectile(centerX + 50, centerY - 25, false);
                    p.setSpeedX(7);
                }
                projectiles.add(p);
            }
        }
    }

    public void getHit(Projectile projectile)
    {
        if (projectile.isEnemyProjectile() && currentHealth != 0)
        {
            currentHealth--;
            projectile.setVisible(false);
            if (currentHealth == 0)
                this.setDead(true);
        }
    }

    // Getters and setters
    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public boolean isJumped()
    {
        return jumped;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getSpeedY()
    {
        return speedY;
    }

    public void setCenterX(int centerX)
    {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY)
    {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped)
    {
        this.jumped = jumped;
    }

    public void setSpeedX(int speedX)
    {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY)
    {
        this.speedY = speedY;
    }

    public int getMoveSpeed()
    {
        return moveSpeed;
    }

    public int getJumpSpeed()
    {
        return jumpSpeed;
    }

    public boolean isMovingLeft()
    {
        return movingLeft;
    }

    public boolean isMovingRight()
    {
        return movingRight;
    }

    public boolean isDucked()
    {
        return ducked;
    }

    public void setMoveSpeed(int moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }

    public void setJumpSpeed(int jumpSpeed)
    {
        this.jumpSpeed = jumpSpeed;
    }

    public void setMovingLeft(boolean movingLeft)
    {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight)
    {
        this.movingRight = movingRight;
    }

    public void setDucked(boolean ducked)
    {
        this.ducked = ducked;
    }

    public ArrayList<Projectile> getProjectiles()
    {
        return projectiles;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth)
    {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void setDead(boolean isDead)
    {
        this.isDead = isDead;
    }

    public boolean isReadyToFire()
    {
        return readyToFire;
    }

    public void setReadyToFire(boolean readyToFire)
    {
        this.readyToFire = readyToFire;
    }

}


package thelarsinator.robotgame.game;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Lars on 15.07.2016.
 */
public class Enemy {

    protected int maxHealth, currentHealth, power, speedX, speedY, centerX, centerY, hitBoxSize;
    private Background bg = GameScreen.getBg1();
    private Robot robot = GameScreen.getRobot();

    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 5;
    private boolean dead = false;

    protected int sizeX, sizeY;

    protected ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private boolean isDead = false;


    private int movementSpeed;

    // Behavioral Methods
    public void update() {
        follow();
        centerX += speedX;
        centerY += speedY;
        speedX = bg.getSpeedX() * 5 + movementSpeed;
        r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

        if (Rect.intersects(r, Robot.yellowRed)) {
            checkCollision();
        }


    }

    public void die()
    {
        setDead(true);
    }

    public void getHit(Projectile p)
    {
        currentHealth -= p.getPower();
        p.setVisible(false); //Destroy bullet
        if(currentHealth == 0)
        {
            die();
            GameScreen.score += 100;
        }
    }

    private void checkCollision() {
        if (Rect.intersects(r, robot.upperBody) || Rect.intersects(r, robot.lowerBody) || Rect.intersects(r, robot.rightHand)
                || Rect.intersects(r, robot.leftHand) || Rect.intersects(r, robot.footright) || Rect.intersects(r, robot.footleft)) {

        }
    }

    public void follow() {

        if (centerX < -95 || centerX > 810){
            movementSpeed = 0;
        }

        else if (Math.abs(robot.getCenterX() - centerX) < 5) {
            movementSpeed = 0;
        }

        else {

            if (robot.getCenterX() >= centerX) {
                movementSpeed = 1;
            } else {
                movementSpeed = -1;
            }
        }

    }

    public void attack() {

    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    public int getPower()
    {
        return power;
    }

    public int getSpeedX()
    {
        return speedX;
    }

    public int getSpeedY()
    {
        return speedY;
    }

    public int getCenterX()
    {
        return centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public Background getBg()
    {
        return bg;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth)
    {
        this.currentHealth = currentHealth;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void setSpeedX(int speedX)
    {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY)
    {
        this.speedY = speedY;
    }

    public void setCenterX(int centerX)
    {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY)
    {
        this.centerY = centerY;
    }

    public void setBg(Background bg)
    {
        this.bg = bg;
    }

    public int getHitBoxSize()
    {
        return hitBoxSize;
    }

    public void setHitBoxSize(int hitBoxSize)
    {
        this.hitBoxSize = hitBoxSize;
    }

    public boolean isDead()
    {
        return dead;
    }

    public void setDead(boolean isDead)
    {
        this.dead = isDead;
    }

    public Rect getHitBox()
    {
        return r;
    }

    public int getSizeX()
    {
        return sizeX;
    }

    public int getSizeY()
    {
        return sizeY;
    }

    public void setHitBox(Rect hitBox)
    {
        this.r = hitBox;
    }

    public void setSizeX(int sizeX)
    {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY)
    {
        this.sizeY = sizeY;
    }

    public ArrayList<Projectile> getProjectiles()
    {
        return projectiles;
    }
}

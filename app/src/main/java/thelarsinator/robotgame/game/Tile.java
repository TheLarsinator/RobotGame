package thelarsinator.robotgame.game;

import android.graphics.Rect;

import thelarsinator.robotgame.framework.Image;

/**
 * Created by Lars on 15.07.2016.
 */
public class Tile {
    public static enum TileType {
        GRASS, OCEAN, DIRT, PLATFORM, AIR
    }

    private TileType tileType;
    private int tileX, tileY, speedX;
    public Image tileImage;

    private Background bg = GameScreen.getBg1();
    private Robot robot = GameScreen.getRobot();

    private Rect r;

    public Tile(int x, int y, TileType tileType) {
        this.tileX = x;
        this.tileY = y;
        this.tileType = tileType;

        r = new Rect();
        switch (tileType) {
            case GRASS:
                tileImage = Assets.grassTile;
                break;
            case OCEAN:
                tileImage = Assets.oceanTile;
                break;
            case DIRT:
                tileImage = Assets.dirtTile;
                break;
            case PLATFORM:
                tileImage = Assets.platformTile;
                break;
            case AIR:
                tileImage = null;
                break;
        }
    }

    public void checkVerticalCollision(Rect rtop, Rect rbot) {
        if (Rect.intersects(rtop, r))
        {
            if(tileType != TileType.PLATFORM) {
                robot.setSpeedY(0);
                robot.setCenterY(tileY + 40 + 64);
            }
        }

        if (Rect.intersects(rbot, r) && tileType != TileType.OCEAN && tileType != TileType.AIR) {
            robot.setJumped(false);
            robot.setSpeedY(0);
            robot.setCenterY(tileY - 63);

        }
    }

    public void checkSideCollision(Rect rleft, Rect rright, Rect leftfoot, Rect rightfoot) {
        if (tileType != TileType.OCEAN && tileType != TileType.AIR) {
            if (Rect.intersects(r, rleft)) {
                robot.setCenterX(tileX + 102);

                robot.setSpeedX(0);

            } else if (Rect.intersects(r, leftfoot)) {
                robot.setCenterX(tileX + 85);
                robot.setSpeedX(0);
            }

            if (Rect.intersects(r, rright)) {
                robot.setCenterX(tileX - 62);

                robot.setSpeedX(0);
            } else if (Rect.intersects(r, rightfoot)) {
                robot.setCenterX(tileX - 45);
                robot.setSpeedX(0);
            }
        }
    }

    public void update() {
        switch (tileType) {
            case GRASS:
            case PLATFORM:
            case AIR:
            case DIRT:
                speedX = bg.getSpeedX() * 5;
                break;
            case OCEAN:
                if (bg.getSpeedX() == 0)
                    speedX = -1;
                else
                    speedX = -2;
                break;
        }

        tileX += speedX;
        r.set(tileX, tileY, tileX+40, tileY+40);

        if (Rect.intersects(r, Robot.yellowRed) && (tileType != TileType.OCEAN && tileType != TileType.AIR)) {
            checkVerticalCollision(Robot.upperBody, Robot.lowerBody);
            checkSideCollision(Robot.rightHand, Robot.leftHand, Robot.footleft, Robot.footright);
        }
    }

    public TileType getTileType() {
        return tileType;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }
}


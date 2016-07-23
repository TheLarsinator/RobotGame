package thelarsinator.robotgame.game;

/**
 * Created by Lars on 15.07.2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;

import thelarsinator.robotgame.framework.Game;
import thelarsinator.robotgame.framework.Graphics;
import thelarsinator.robotgame.framework.Image;
import thelarsinator.robotgame.framework.Input;
import thelarsinator.robotgame.framework.Input.TouchEvent;
import thelarsinator.robotgame.framework.Screen;
import thelarsinator.robotgame.utils.Utils;

public class GameScreen extends Screen
{
    enum GameState {
        Idle, Ready, Running, Paused, GameOver
    }

    public static GameState state = GameState.Ready;

    //Variables
    private static Background bg1, bg2;
    private static Robot robot;

    private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private static ArrayList<Tile> tiles = new ArrayList<Tile>();

    private Image currentSprite, character, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    private Animation hanim;

    private int BGWIDTH = 2160;
    int livesLeft = 3;
    public static int score = 0;

    Paint paint, paint2;

    public GameScreen(Game game)
    {
        super(game);
        bg1 = new Background(0, 0, BGWIDTH);
        bg2 = new Background(BGWIDTH, 0, BGWIDTH);

        robot = new Robot();
        robot.setMaxHealth(20);
        robot.setCurrentHealth(20);

        heliboy = Assets.heliboy;
        heliboy2 = Assets.heliboy2;
        heliboy3 = Assets.heliboy3;
        heliboy4 = Assets.heliboy4;
        heliboy5 = Assets.heliboy5;

        //Heli animations
        hanim = new Animation();
        hanim.addFrame(heliboy, 100);
        hanim.addFrame(heliboy2, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy5, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy2, 100);

        character = Assets.charWalk;
        character2 = Assets.charJump;
        character3 = Assets.charDuck;

        currentSprite = character;

        loadMap();

        //The paints
        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);

        state = GameState.Ready;
    }

    private void loadMap() {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        Scanner scanner = new Scanner(MainActivity.map);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // no more lines to read
            if (line == null) {
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }
        height = lines.size();

        for (int j = 0; j < 12; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++)
            {
                if (i < line.length()) {
                    char ch = line.charAt(i);
                    System.out.println(ch);
                    if (ch == 'p' || ch == 'd' || ch == 'g')
                    {
                        Tile t = new Tile(i * 40, j * 40, Utils.characterToTileType(ch));
                        tiles.add(t);
                    } else if (ch == 'h')
                    {
                        HeliBoy h = new HeliBoy(i * 40, j * 40);
                        enemies.add(h);
                    }
                }
            }
        }
    }

    @Override
    public void update(float deltaTime)
    {
        List touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List touchEvents)
    {
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

        // This is identical to the update() method from our Unit 2/3 game.

        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {

                if (inBounds(event, 0, 285, 65, 65)) {
                    robot.jump();
                }

                else if (inBounds(event, 0, 350, 65, 65)) {

                    robot.shoot();
                    robot.setReadyToFire(false);
                }

                else if (inBounds(event, 0, 415, 65, 65))
                {
                    if (!robot.isJumped())
                    {
                        robot.setDucked(true);
                        robot.setSpeedX(0);
                    }
                }

                if (event.x > 400) {
                    // Move right.
                    robot.moveRight();
                    robot.setMovingRight(true);

                }

            }

            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, 415, 65, 65)) {
                    robot.setDucked(false);
                }

                if (inBounds(event, 0, 0, 35, 35)) {
                    pause();

                }
                else if (inBounds(event, 0, 350, 65, 65)) {

                    robot.setReadyToFire(true);
                }

                if (event.x > 400) {
                    robot.stopRight();
                }
            }

        }

        // 2. Check miscellaneous events like death:

        if (livesLeft == 0) {
            state = GameState.GameOver;
        }

        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
        robot.update();
        if (robot.isJumped() && !robot.isDead())
            currentSprite = character2;
        else if (robot.isDucked())
            currentSprite = character3;
        else if (robot.isDead())
            currentSprite = character;
        else
            currentSprite = character;

        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy e = (Enemy) enemies.get(i);
            if (!e.isDead())
                e.update();
            else
                enemies.remove(i);
        }

        ArrayList<ArrayList<Projectile>> projectileLists = new ArrayList<ArrayList<Projectile>>();
        projectileLists.add(robot.getProjectiles());

        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy e = (Enemy) enemies.get(i);
            projectileLists.add(e.getProjectiles());
        }

        for (int i = 0; i < projectileLists.size(); i++)
        {
            for (int j = 0; j < projectileLists.get(i).size(); j++)
            {
                Projectile p = (Projectile) projectileLists.get(i).get(j);
                if (p.isVisible())
                    p.update();
                else
                    projectileLists.get(i).remove(j);
            }

        }

        bg1.update();
        bg2.update();
        updateTiles();
        animate();

        if (robot.getCenterY() > 500 || robot.isDead()) {
            livesLeft--;
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 800, 240)) {

                    if (!inBounds(event, 0, 0, 35, 35)) {
                        resume();
                    }
                }

                if (inBounds(event, 0, 240, 800, 240)) {
                    nullify();
                    goToMenu();
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                if (inBounds(event, 0, 0, 800, 480)) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    private void updateTiles() {

        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = (Tile) tiles.get(i);
            t.update();
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
        paintTiles(g);

        ArrayList<ArrayList<Projectile>> projectileLists = new ArrayList<ArrayList<Projectile>>();
        projectileLists.add(robot.getProjectiles());

        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy e = (Enemy) enemies.get(i);
            projectileLists.add(e.getProjectiles());
        }

        for (int i = 0; i < projectileLists.size(); i++)
        {
            for (int j = 0; j < projectileLists.get(i).size(); j++)
            {
                Projectile p = (Projectile) projectileLists.get(i).get(j);
                g.drawRect(p.getX(), p.getY(), 10, 5, Color.BLACK);
            }

        }
        // First draw the game elements.

        g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63);

        for (int i = 0; i < enemies.size(); i++)
        {
            Enemy e = (Enemy) enemies.get(i);
            g.drawImage(hanim.getImage(), e.getCenterX() - 48, e.getCenterY() - 48);
        }

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void paintTiles(Graphics g) {
        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = (Tile) tiles.get(i);
            g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
        }
    }

    public void animate() {
        hanim.update(50);
    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        bg1 = null;
        bg2 = null;
        robot = null;
        paint2 = null;

        for(int i = 0; i < enemies.size(); i++)
        {
            enemies.remove(i);
        }

        enemies = new ArrayList<Enemy>();

        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.remove(i);
        }

        tiles = new ArrayList<Tile>();

        currentSprite = null;
        character = null;
        character2 = null;
        character3 = null;
        heliboy = null;
        heliboy2 = null;
        heliboy3 = null;
        heliboy4 = null;
        heliboy5 = null;
        hanim = null;
        score = 0;

        // Call garbage collector to clean up memory.
        System.gc();
        state = GameState.Idle;
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);

    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
        g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
        g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
        g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 400, 240, paint2);
        g.drawString("Tap to return.", 400, 290, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }

    public static Background getBg1() {
        // TODO Auto-generated method stub
        return bg1;
    }

    public static Background getBg2() {
        // TODO Auto-generated method stub
        return bg2;
    }

    public static Robot getRobot() {
        // TODO Auto-generated method stub
        return robot;
    }

    public static GameState getGameState()
    {
        return state;
    }

    public static void setGameState(GameState gameState)
    {
        state = gameState;
    }

    public static ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

}

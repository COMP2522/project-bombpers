package org.bcit.comp2522.project;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Window class - is the main class of the game.
 */
public class Window extends PApplet {

    public PImage enemyStandardSprite;
    public PImage enemySlowSprite;
    public PImage enemyFastSprite;
    private InputHandler inputHandler;


    /**
     * Declares a projectile image to store the projectile image.
     */
    private static final String PROJECTILE_IMAGE = "../img/bullet.png";

    private static final int CHAR_RESIZE_WIDTH = 2;
    private static final float CHAR_RESIZE_HEIGHT = 1.5f;
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;

    /**
     * Declares a collectionManager to store the sprites.
     */
    private PImage projectileImage;
    CollectionManager collectionManager;
    public HPDisplay hpDisplay;
    public EnemySpawner enemySpawner;
    public DangerLevel dangerLevel;
    public KillCounter killCounter;
    /**
     * Declares a score variable to store the score.
     */
    public Score score;


    /**
     * Declares a background to store the background.
     */
    private Background background;

    /**
     * Declares a variable to hold the GameState to transition between states.
     */
    public GameState stateOfGame = GameState.STARTMENU;

    /**
     * Declares a menu handler to use to handel menus.
     */
    public MenuHandler menuhandler = new MenuHandler(stateOfGame, this);

    /**
     * Creates a window of size 500 x 500 pixels.
     */
    public void settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

  /**
   * Setup of the game.
   */
  public void setup() {
    score = new Score(width / 2, 30, this);
    // Initialize the Player and collectionManager
    this.init();
    // Initialize the input handler singleton
    inputHandler = InputHandler.getInstance(collectionManager,this);
    Player.setPlayerHitboxSize(0.1f);
    noStroke();
        // Create the background object
        background = new Background(this);
        //Create the score object
        //score = new Score(180, 30, myScore, this);
        // HP Display
        hpDisplay = new HPDisplay(this, collectionManager);
        // Enemy Spawner
        enemySpawner = new EnemySpawner(collectionManager, this);
        dangerLevel = new DangerLevel(this, enemySpawner);
        projectileImage = loadImage(PROJECTILE_IMAGE);
        killCounter = new KillCounter(this);
    }

    /**
     * Initializes the  collectionManager and adds the created player to it.
     */
    public void init() {
        collectionManager = CollectionManager.getInstance();
        enemyStandardSprite = loadImage(EnemyConfig.ENEMY_STANDARD_SPRITE);
        enemySlowSprite = loadImage(EnemyConfig.ENEMY_SLOW_SPRITE);
        enemyFastSprite = loadImage(EnemyConfig.ENEMY_FAST_SPRITE);
        CollectionManager.player = Player.getPlayerInstance(this);
        collectionManager.getSprites().add(CollectionManager.player);
        score.resetScore();
        // Reset the player's position
        PVector originalPosition = new PVector((float) this.width / 2, (float) this.height / 2);
        collectionManager.getPlayer().setPosition(originalPosition);
        new Thread(() -> {
            SaveHandler s = new SaveHandler();
            s.autoSave();
        }).start();
    }


    /**
     * If a key is pressed,  the corresponding isPressed variable will be true to
     * "tell" that key was pressed.
     *
     * @param event is the key that was pressed.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        inputHandler.keyPressed(event);
        // Update the player's direction
        updatePlayerDirection();
    }

    /**
     * If a key is released,  the corresponding isPressed variable will be false to
     * make sure it does not move when the key is not pressed.
     *
     * @param event is the key that was released.
     */

    @Override
    public void keyReleased(KeyEvent event) {
        inputHandler.keyReleased(event);
        // Update the player's direction
        updatePlayerDirection();
    }

    /**
     * Updates the player's direction based on the key pressed.
     */
    private void updatePlayerDirection() {
        PVector newDirection = inputHandler.updatePlayerDirection();
        collectionManager.getPlayer().setDirection(newDirection);
    }

    @Override
    public void mousePressed() {
        inputHandler.mousePressed(projectileImage);
    }

    /**
     * Draws everything in the window.
     */
    public void draw() {
        // If the game is in the start menu, pause menu, or end game menu, create the menu
        if (stateOfGame == GameState.STARTMENU || stateOfGame == GameState.PAUSE
                || stateOfGame == GameState.ENDGAME) {
            stateOfGame = menuhandler.createMenu(stateOfGame, score.getCurrentScore(), score.getHighScore());
        } else if (stateOfGame == GameState.STARTGAME) {
            background.draw();
            hpDisplay.draw();
            // If key 'p' is pressed, pause the game
            if (keyPressed) {
                if (key == 'p' || key == 'P') {
                    //state to pause
                    stateOfGame = GameState.PAUSE;
                }
            }
            // Create the sprites and draw as well as update them
            for (Sprite sprite : collectionManager.getSprites()) {
                sprite.update();
                sprite.draw();
            }
            collectionManager.getProjectiles().removeIf(projectile -> {
                boolean toRemove = projectile.getPosition().x < 0 || projectile.getPosition().x > width
                        || projectile.getPosition().y < 0 || projectile.getPosition().y > height;
                if (toRemove) {
                    collectionManager.getSprites().remove(projectile);
                }
                return toRemove;
            });
            ArrayList<Enemy> toRemove = new ArrayList<>();
            ArrayList<Projectile> projectilesToRemove = new ArrayList<>();
            for (Enemy enemy : collectionManager.getEnemies()) {
                if (enemy.checkCollisionWithPlayer(collectionManager.getPlayer())) {
                    toRemove.add(enemy);
                    collectionManager.getPlayer().setHealth(collectionManager.getPlayer().getHealth() - enemy.getDamage());
                    hpDisplay.damage(enemy.getDamage());

                    if (collectionManager.getPlayer().getHealth() <= 0) {
                        stateOfGame = GameState.ENDGAME;
                        collectionManager.getPlayer().setHealth(Player.PLAYER_HEALTH);
                        hpDisplay.update();
                        for (Enemy enemyRemain : collectionManager.getEnemies()) {
                            toRemove.add(enemyRemain);
                            enemySpawner.countReset();
                        }
                    }
                }
                for (Projectile projectile : collectionManager.getProjectiles()) {
                    projectile.collide(projectile, enemy);
                    if (projectile.isDead() && enemy.isDead()) {
                        toRemove.add(enemy);
                        killCounter.killPlus();
                        enemySpawner.decreaseEnemCount();
                        enemySpawner.updateSpawnModifier(score);
                        projectilesToRemove.add(projectile);
                        score.incrementScore(score.getCurrentScore(), enemy);
                        dangerLevel.update();
                        if (score.getCurrentScore() >= score.getHighScore()) {
                            score.setHighScore(score.getCurrentScore());
                        }
                    }
                }
            }
            // Remove the enemies that have collided with the player
            for (Enemy enemy : toRemove) {
                collectionManager.getEnemies().remove(enemy);
                collectionManager.getSprites().remove(enemy);
            }
            for (Projectile projectile : projectilesToRemove) {
                collectionManager.getProjectiles().remove(projectile);
                collectionManager.getSprites().remove(projectile);
            }
            // Spawns new enemies mid-game
            enemySpawner.spawnEnemy();
            dangerLevel.draw();

            // Kill Counter for enemies
            killCounter.draw(this);
            score.drawUserInterface(stateOfGame);
        }
    }

    /**
     * main method.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        String[] appletArgs = new String[]{"eatBubbles"};
        Window eatBubbles = new Window();
        PApplet.runSketch(appletArgs, eatBubbles);
    }
}
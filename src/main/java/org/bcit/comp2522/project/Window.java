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
        // Initialize the Player and collectionManager
        this.init();
        inputHandler = InputHandler.getInstance(collectionManager, this);
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
        switch (stateOfGame) {
            case STARTMENU, ENDGAME -> drawStartOrEndGameMenu();
            case PAUSE -> drawPauseMenu();
            case STARTGAME -> drawGame();
        }
    }

    private void drawStartOrEndGameMenu() {
        score = new Score(width / 2, 30, this);
        PVector originalPosition = new PVector((float) this.width / 2, (float) this.height / 2);
        collectionManager.getPlayer().setPosition(originalPosition);
        stateOfGame = menuhandler.createMenu(stateOfGame, score.getCurrentScore(), score.getHighScore());
    } //UIhandler

    private void drawPauseMenu() {
        stateOfGame = menuhandler.createMenu(stateOfGame, score.getCurrentScore(), score.getHighScore());
    } // UIhandler

    private void drawGame() {
        background.draw();
        score.displayScore(stateOfGame);
        hpDisplay.draw();

        if (keyPressed) {
            if (key == 'p' || key == 'P') {
                stateOfGame = GameState.PAUSE;
            }
        }

        updateSprites();
        handleCollisions();
        enemySpawner.spawnEnemy();
        dangerLevel.draw();
        killCounter.draw(this);
    }//UIhandler or Keephere?

    private void updateSprites() {
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
    } // CollectionManager
    private void handleCollisions() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        ArrayList<Projectile> projectilesToRemove = new ArrayList<>();

        handlePlayerEnemyCollisions(enemiesToRemove);
        handleProjectileEnemyCollisions(enemiesToRemove, projectilesToRemove);
        removeCollidedObjects(enemiesToRemove, projectilesToRemove);
    }//CollisionHandler

    private void handlePlayerEnemyCollisions(ArrayList<Enemy> enemiesToRemove) {
        for (Enemy enemy : collectionManager.getEnemies()) {
            if (enemy.checkCollisionWithPlayer(collectionManager.getPlayer())) {
                enemiesToRemove.add(enemy);
                collectionManager.getPlayer().setHealth(collectionManager.getPlayer().getHealth() - enemy.getDamage());
                hpDisplay.damage(enemy.getDamage());

                if (collectionManager.getPlayer().getHealth() <= 0) {
                    stateOfGame = GameState.ENDGAME;
                    collectionManager.getPlayer().setHealth(Player.PLAYER_HEALTH);
                    hpDisplay.update();
                    enemiesToRemove.addAll(collectionManager.getEnemies());
                    enemySpawner.countReset();
                }
            }
        }
    } //CollisionHandler

    private void handleProjectileEnemyCollisions(ArrayList<Enemy> enemiesToRemove, ArrayList<Projectile> projectilesToRemove) {
        for (Projectile projectile : collectionManager.getProjectiles()) {
            for (Enemy enemy : collectionManager.getEnemies()) {
                projectile.collide(projectile, enemy);
                if (projectile.isDead()) {
                    projectilesToRemove.add(projectile);
                    if (enemy.isDead()) {
                        enemiesToRemove.add(enemy);
                        handleEnemyDeath(enemy);
                    }
                }
            }
        }
    } //CollisionHandler

    private void handleEnemyDeath(Enemy enemy) {
        killCounter.killPlus();
        enemySpawner.decreaseEnemCount();
        enemySpawner.updateSpawnModifier(score);

        score.incrementScore(score.getCurrentScore(), enemy);
        score.displayScore(stateOfGame);
        dangerLevel.update();
        if (score.getCurrentScore() >= score.getHighScore()) {
            score.setHighScore(score.getCurrentScore());
        }
    } //CollisionHandler

    private void removeCollidedObjects(ArrayList<Enemy> enemiesToRemove, ArrayList<Projectile> projectilesToRemove) {
        for (Enemy enemy : enemiesToRemove) {
            collectionManager.getEnemies().remove(enemy);
            collectionManager.getSprites().remove(enemy);
        }
        for (Projectile projectile : projectilesToRemove) {
            collectionManager.getProjectiles().remove(projectile);
            collectionManager.getSprites().remove(projectile);
        }
    } //CollisionHandler

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
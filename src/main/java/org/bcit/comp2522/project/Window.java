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
  CollisionHandler collisionHandler;
  public EnemySpawner enemySpawner;
  /**
   * Declares a score variable to store the score.
   */
  public UIHandler uiHandler;
  //public Score score;
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

  private DatabaseHandler db;

  /**
   * Setup of the game.
   */
  public void setup() {
    //score = new Score(WINDOW_WIDTH / 2, 30, this);
    // Initialize the Player and collectionManager
    this.init();
    inputHandler = InputHandler.getInstance(collectionManager, this);
    noStroke();
    // Create the background object
    background = new Background(this);
    //Create the score object
    //score = new Score(180, 30, myScore, this);
    projectileImage = loadImage(PROJECTILE_IMAGE);

  }

  /**
   * Initializes the  collectionManager and adds the created player to it.
   */
  public void init() {
    collectionManager = CollectionManager.getInstance();
    CollectionManager.player = Player.getPlayerInstance(this);
    collectionManager.getSprites().add(CollectionManager.player);
    enemySpawner = new EnemySpawner(collectionManager, this);
    uiHandler = new UIHandler(this, this, stateOfGame, enemySpawner);
    this.db = DatabaseHandler.getInstance(uiHandler, collectionManager);
    collisionHandler = new CollisionHandler(collectionManager, this, uiHandler);
    uiHandler.getScore().setHighScore(db.load());
    //collectionManager.setHighScore(db.load()); //This is used for DB purposes.
    enemyStandardSprite = loadImage(EnemyConfig.ENEMY_STANDARD_SPRITE);
    enemySlowSprite = loadImage(EnemyConfig.ENEMY_SLOW_SPRITE);
    enemyFastSprite = loadImage(EnemyConfig.ENEMY_FAST_SPRITE);
    // Reset the player's position
    PVector originalPosition = new PVector((float) this.width / 2, (float) this.height / 2);
    collectionManager.getPlayer().setPosition(originalPosition);
    new Thread(() -> {
      SaveHandler s = new SaveHandler();
      s.autoSave(collectionManager, uiHandler.getScore());
    }).start();
    uiHandler.getScore().resetScore();
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
    inputHandler.pauseGameOnPKeyPressed(event);
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
      case STARTMENU, PAUSE, ENDGAME ->
          stateOfGame = menuhandler.createMenu(stateOfGame, uiHandler.getScore().getCurrentScore(),
              uiHandler.getScore().getHighScore());
      case STARTGAME -> drawGame();
    }
  }

  public void drawGame() {
    background.draw();
    uiHandler.draw(stateOfGame);

    drawAndUpdateSprites();

    removeOffscreenProjectiles();
    collisionHandler.handleCollisions();

    enemySpawner.spawnerActivate();
  }

  public void drawAndUpdateSprites() {
    for (Sprite sprite : collectionManager.getSprites()) {
      sprite.update();
      sprite.draw();
    }
  }

  public void removeOffscreenProjectiles() {
    collectionManager.getProjectiles().removeIf(projectile -> {
      boolean toRemove = projectile.getPosition().x < 0 || projectile.getPosition().x
              > width || projectile.getPosition().y < 0 || projectile.getPosition().y > height;
      if (toRemove) {
        collectionManager.getSprites().remove(projectile);
      }
      return toRemove;
    });
  }

  public void handleGameOver(ArrayList<Enemy> enemiesToRemove) {
    stateOfGame = GameState.ENDGAME;
    collectionManager.getPlayer().setHealth(Player.PLAYER_HEALTH);
    uiHandler.getHPDisplay().update();
    enemiesToRemove.addAll(collectionManager.getEnemies());
    enemySpawner.countReset();
    uiHandler.getDangerLevel().resetDangerLevel();
  }

  public void updateGameParametersAfterEnemyDeath(Enemy enemy) {
    enemySpawner.decreaseEnemyCount();
    enemySpawner.updateSpawnModifier(uiHandler.getScore());
    uiHandler.getScore().incrementScore(uiHandler.getScore().getCurrentScore(), enemy);
    uiHandler.getScore().displayScore(stateOfGame);
    uiHandler.getDangerLevel().update();
    updateHighScoreIfNeeded();
  }

  private void updateHighScoreIfNeeded() {
    if (uiHandler.getScore().getCurrentScore() >= uiHandler.getScore().getHighScore()) {
      uiHandler.getScore().setHighScore(uiHandler.getScore().getCurrentScore());
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
package org.bcit.comp2522.project;

import java.util.List;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Window class - is the main class of the game.
 *
 * @author Brett Reader, Sukhraj Sidhu, Ozan Yurtisigi, Benny Li, Armarjot Singha
 * @version 1.0
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
  private CollectionManager collectionManager;
  private CollisionHandler collisionHandler;
  public EnemySpawner enemySpawner;
  /**
   * Declares a score variable to store the score.
   */
  public UiHandler uiHandler;
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

  public Window() {
    super();
  }

  @Override
  public void settings() {
    size(WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  @Override
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
    uiHandler = new UiHandler(this, this, stateOfGame, enemySpawner);
    uiHandler.getHpDisplay().update();
    final DatabaseHandler db = DatabaseHandler.getInstance(uiHandler, collectionManager);
    collisionHandler = new CollisionHandler(collectionManager, this, uiHandler);
    uiHandler.getScore().setHighScore(db.load());
    enemyStandardSprite = loadImage(EnemyConfig.ENEMY_STANDARD_SPRITE);
    enemySlowSprite = loadImage(EnemyConfig.ENEMY_SLOW_SPRITE);
    enemyFastSprite = loadImage(EnemyConfig.ENEMY_FAST_SPRITE);
    // Reset the player's position
    final PVector originalPosition = new PVector((float) this.width / 2, (float) this.height / 2);
    collectionManager.getPlayer().setPosition(originalPosition);
    new Thread(() -> {
      final SaveHandler s = new SaveHandler(uiHandler);
      s.autoSave();
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
    inputHandler.pauseGameOnPauseKeyPressed(event);
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
    final PVector newDirection = inputHandler.updatePlayerDirection();
    collectionManager.getPlayer().setDirection(newDirection);
  }

  @Override
  public void mousePressed() {
    inputHandler.mousePressed(projectileImage);
  }

  @Override
  public void draw() {
    switch (stateOfGame) {
      case STARTMENU,
          PAUSE,
          ENDGAME -> stateOfGame = menuhandler.createMenu(
          stateOfGame,
          uiHandler.getScore().getCurrentScore(),
          uiHandler.getScore().getHighScore()
      );
      case STARTGAME -> drawGame();
      default ->
          Logger.getLogger(Window.class.getName()).warning("Invalid state of game: " + stateOfGame);
    }
  }

  /**
   * draws the game.
   */
  public void drawGame() {
    background.draw();
    uiHandler.draw(stateOfGame);

    drawAndUpdateSprites();

    removeOffscreenProjectiles();
    collisionHandler.handleCollisions();

    enemySpawner.spawnerActivate();
  }

  /**
   * Draws and updates the sprites.
   */
  public void drawAndUpdateSprites() {
    for (final Sprite sprite : collectionManager.getSprites()) {
      sprite.update();
      sprite.draw();
    }
  }

  /**
   * Removes the projectiles that are offscreen.
   */
  public void removeOffscreenProjectiles() {
    collectionManager.getProjectiles().removeIf(projectile -> {
      final boolean toRemove =
          projectile.getPosition().x < 0
              || projectile.getPosition().x > width
              || projectile.getPosition().y < 0
              || projectile.getPosition().y > height;
      if (toRemove) {
        collectionManager.getSprites().remove(projectile);
      }
      return toRemove;
    });
  }

  /**
   * Handles the game over.
   *
   * @param enemiesToRemove is the list of enemies to remove.
   */
  public void handleGameOver(List<Enemy> enemiesToRemove) {
    stateOfGame = GameState.ENDGAME;
    collectionManager.getPlayer().setHealth(Player.PLAYER_HEALTH);
    uiHandler.getHpDisplay().update();
    enemiesToRemove.addAll(collectionManager.getEnemies());
    enemySpawner.countReset();
    uiHandler.getDangerLevel().resetDangerLevel();
  }

  /**
   * Updates the game parameters after an enemy dies.
   *
   * @param enemy is the enemy that died.
   */
  public void updateGameParametersAfterEnemyDeath(Enemy enemy) {
    enemySpawner.decreaseEnemyCount();
    enemySpawner.updateSpawnModifier(uiHandler.getScore());
    uiHandler.getScore().incrementScore(enemy.enemyType);
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
    final String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}
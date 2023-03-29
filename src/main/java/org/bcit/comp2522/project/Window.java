package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Window class - is the main class of the game.
 */
public class Window extends PApplet {

  /**
   * Variable to check if the left key is pressed. Set to false by default.
   */
  private boolean isLeftPressed = false;
  /**
   * Variable to check if the right key is pressed.Set to false by default.
   */
  private boolean isRightPressed = false;
  /**
   * Variable to check if the up key is pressed.Set to false by default.
   */
  private boolean isUpPressed = false;
  /**
   * Variable to check if the down key is pressed.Set to false by default.
   */
  private boolean isDownPressed = false;
  /**
   * Number of enemy types.
   */
  private static final int ENEM_TYPES = 3;
  /**
   * Maximum number of enemies.
   */
  private static final int ENEM_MAX = 10;
  /**
   * Maximum number of standard type enemies.
   */
  private static final int ENEM_STANDARD_MAX = 5;
  /**
   * Maximum number of fast type enemies.
   */
  private static final int ENEM_FAST_MAX = 10;
  /**
   * Maximum number of slow type enemies.
   */
  private static final int ENEM_SLOW_MAX = 5;
  /**
   * Sets the different types of enemies to start off at 0.
   */
  private static int curr_enem_standard = 0;
  private static int curr_enem_fast = 0;
  private static int curr_enem_slow = 0;
  /**
   * Sets the score to 0.
   */
  public PImage enemyStandardSprite;
  public PImage enemySlowSprite;
  public PImage enemyFastSprite;
  private static int myScore = 0;
  /**
   * Sets the high score to 0.
   */
  private static int high = 0;

  /**
   * Declares a projectile image to store the projectile image.
   */
  private static final String PROJECTILE_IMAGE = "../img/bullet.png";

  private static final int CHAR_RESIZE_WIDTH = 2;
  private static final float CHAR_RESIZE_HEIGHT = 1.5f;

  /**
   * Declares a collectionManager to store the sprites.
   */
  private PImage projectileImage;
  CollectionManager collectionManager;

  private EnemySpawner enemySpawner;
  /**
   * Declares a score variable to store the score.
   */
  private ConcurrentLinkedQueue<Projectile> projectiles = new ConcurrentLinkedQueue<>();

  private Menu menu, menu2, menu3, menu4;
  private Score score;

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
   * Declares a random variable to use to generate random numbers.
   */
  private Random rngsus = new Random();

  /**
   * Creates a window of size 500 x 500 pixels.
   */
  public void settings() {
    size(500, 500);
  }

  /**
   * Setup of the game.
   */
  public void setup() {
    // Initialize the PLayer and collectionManager
    this.init();

    noStroke();

    // Create the background object
    background = new Background(this);
    //Create the score object
    score = new Score(180, 30, myScore, this);
    // Enemy Spawner
    enemySpawner = new EnemySpawner(collectionManager, this);
    projectileImage = loadImage(PROJECTILE_IMAGE);
  }

  /**
   * Initializes the  collectionManager and adds the created player to it.
   */
  public void init() {
    collectionManager = CollectionManager.getInstance();
    enemyStandardSprite = loadImage(EnemyConfig.ENEMY_STANDARD_SPRITE);
    enemySlowSprite = loadImage(EnemyConfig.ENEMY_SLOW_SPRITE);
    enemyFastSprite = loadImage(EnemyConfig.ENEMY_FAST_SPRITE);
    collectionManager.player = new Player(this);
//    PImage characterSprite = loadImage("../img/idle_01.png");
    collectionManager.getSprites().add(collectionManager.player);
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
    // Get the key code of the key that was pressed
    int keyCode = event.getKeyCode();
    // Check if the key code is the same as any of the switch cases and do the corresponding action
    switch (keyCode) {
      case LEFT -> isLeftPressed = true;
      case RIGHT -> isRightPressed = true;
      case UP -> isUpPressed = true;
      case DOWN -> isDownPressed = true;
      default -> System.out.println(); // switch needed a default case, it does nothing.
    }
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
    // Get the key code of the key that was pressed
    int keyCode = event.getKeyCode();
    // Check if the key code is the same as any of the switch cases and do the corresponding action
    switch (keyCode) {
      case LEFT -> isLeftPressed = false;
      case RIGHT -> isRightPressed = false;
      case UP -> isUpPressed = false;
      case DOWN -> isDownPressed = false;
      default -> System.out.println(); // switch needed a default case, it does nothing.
    }
    // Update the player's direction
    updatePlayerDirection();
  }

  /**
   * Updates the player's direction based on the key pressed.
   */
  private void updatePlayerDirection() {
    int directionX = 0;
    int directionY = 0;
    // Check if the key is pressed and update the direction accordingly
    if (isLeftPressed) {
      directionX--;
    }
    if (isRightPressed) {
      directionX++;
    }
    if (isUpPressed) {
      directionY--;
    }
    if (isDownPressed) {
      directionY++;
    }
    // If the direction is not 0,0, set the player's direction to the new direction
    if (directionX != 0 || directionY != 0) {
      collectionManager.getPlayer().setDirection(new PVector(directionX, directionY));
    } else {
      collectionManager.getPlayer().setDirection(new PVector(0, 0));
    }
  }

  @Override
  public void mousePressed() {
    if (stateOfGame == GameState.STARTGAME && mouseButton == LEFT) {
      PVector mousePosition = new PVector(mouseX, mouseY);
      PVector playerPosition = collectionManager.getPlayer().getPosition();
      PVector direction = PVector.sub(mousePosition, playerPosition).normalize();

      PVector projectileStartPosition = new PVector(
              playerPosition.x + collectionManager.getPlayer().getSize() / CHAR_RESIZE_WIDTH - Projectile.PROJECTILE_SIZE / CHAR_RESIZE_WIDTH,
              playerPosition.y + collectionManager.getPlayer().getSize() / CHAR_RESIZE_HEIGHT - Projectile.PROJECTILE_SIZE / CHAR_RESIZE_WIDTH
      );


      Projectile projectile = new Projectile(this, projectileStartPosition, direction, projectileImage);
      projectiles.add(projectile);
      collectionManager.getSprites().add(projectile);
    }
  }

  /**
   * Draws everything in the window.
   */
  public void draw() {
    // If the game is in the start menu, pause menu, or end game menu, create the menu
    if (stateOfGame == GameState.STARTMENU || stateOfGame == GameState.PAUSE
        || stateOfGame == GameState.ENDGAME) {


      if (stateOfGame == GameState.STARTMENU || stateOfGame == GameState.ENDGAME) {
        myScore = 0;
      }

      stateOfGame = menuhandler.createMenu(stateOfGame, score);
      // Reset the player's position
      PVector originalPosition = new PVector((float) this.width / 2, (float) this.height / 2);
      collectionManager.getPlayer().setPosition(originalPosition);

    } else if (stateOfGame == GameState.STARTGAME) {
      // If the game is in the start game state, create the game
      //Reset the score to 0
      if (myScore == 0) {
        myScore = 0;
      }
      background.draw();
      score.displayScore(stateOfGame);
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

      projectiles.removeIf(projectile -> {
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
        for (Projectile projectile : projectiles) {
          projectile.collide(projectile, enemy);
          if (projectile.isDead() && enemy.isDead()) {
            toRemove.add(enemy);
            score.setCurrentScore(++myScore);
            EnemySpawner.decreaseEnemCount();
            projectilesToRemove.add(projectile);
//            if (enemy instanceof EnemyStandard) {
//              curr_enem_standard--;
//              score.setCurrentScore(++myScore);
//            }
//            if (enemy instanceof EnemyFast) {
//              curr_enem_fast--;
//              myScore += 2;
//              score.setCurrentScore(myScore);
//            }
//            if (enemy instanceof EnemySlow) {
//              curr_enem_slow--;
//              myScore += 3;
//              score.setCurrentScore(myScore);
//            }
            score.displayScore(stateOfGame);
            score.setHighScore(myScore);
            if (myScore >= high) {
              high = score.getHighScore();
            }
          }

        }
      }
      for (Enemy enemy : toRemove) {
        collectionManager.getEnemies().remove(enemy);
        collectionManager.getSprites().remove(enemy);
      }
      for (Projectile projectile : projectilesToRemove) {
        projectiles.remove(projectile);
        collectionManager.getSprites().remove(projectile);
      }

      // Spawns new enemies mid-game
      enemySpawner.spawnEnemy();

    } else if (stateOfGame == GameState.PAUSE) {
      // If the game is in the pause state, show the score and pause menu
      if (myScore >= score.getHighScore()) {
        score.setHighScore(myScore);
      }
      score.displayScore(stateOfGame);
    } else {
      if (myScore >= score.getHighScore()) {
        score.setHighScore(myScore);
      }
      score.displayScore(stateOfGame);
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
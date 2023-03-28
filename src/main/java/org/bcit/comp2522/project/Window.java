package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Random;
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
  private static int myScore = 0;
  /**
   * Sets the high score to 0.
   */
  private static int high = 0;
  /**
   * Sets the player health to 5.
   */
  public int health = 5;
  /**
   * Declares a collection manager.
   */
  CollectionManager collectionManager;
  /**
   * Declares a score variable to store the score.
   */
  private Score score;

  /**
   * Declares a background to store the background.
   */
  private Background background;
  /**
   * Declares a variable to hold the GameState to transition between states.
   */
  public GameState  stateOfGame =  GameState.startMenu;
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
    // Create the background object
    background = new Background(this);
    //Create the score object
    score = new Score(180, 30, myScore, this);
  }

  /**
   * Initializes the  collectionManager and adds the created player to it.
   */
  public void init() {
    collectionManager = new CollectionManager();
    collectionManager.player = new Player(this);
    PImage characterSprite = loadImage("../img/idle_01.png");
    collectionManager.getSprites().add(collectionManager.player);
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

  /**
   * Draws everything in the window.
   */
  public void draw() {
    // If the game is in the start menu, pause menu, or end game menu, create the menu
    if (stateOfGame == GameState.startMenu || stateOfGame == GameState.pause
            || stateOfGame == GameState.endGame) {


      if (stateOfGame == GameState.startMenu || stateOfGame == GameState.endGame) {
        myScore = 0;
      }

      stateOfGame = menuhandler.createMenu(stateOfGame, score);
      // Reset the player's position
      PVector originalPosition = new PVector((float) this.width / 2, (float) this.height / 2);
      collectionManager.getPlayer().setPosition(originalPosition);

    } else if (stateOfGame == GameState.startGame) {
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
          stateOfGame = GameState.pause;
        }
      }
      // Create the sprites and draw as well as update them
      for (Sprite sprite : collectionManager.getSprites()) {
        sprite.update();
        sprite.draw();
      }
      //Create an array list to hold the enemies that need to be removed
      ArrayList<Enemy> toRemove = new ArrayList<>();
      // Check if the player has collided with any of the enemies and remove them if they have
      //Also decrease the health of the player
      for (Enemy enemy : collectionManager.getEnemies()) {
        if (Enemy.collided(collectionManager.getPlayer(), enemy)) {
          toRemove.add(enemy);
          health--;
          if (health == 0) {
            stateOfGame = GameState.endGame;
            health = 5;
          }
        }
      }
      // Remove the enemies that have collided with the player
      for (Enemy enemy : toRemove) {
        if (collectionManager.getPlayer().compareTo(enemy) > 0) {
          //Depending on the type of enemy, decrease the current number of that type of enemy
          //and increase the score.
          if (enemy instanceof EnemyStandard) {
            curr_enem_standard--;
            score.setCurrentScore(++myScore);
          }
          if (enemy instanceof EnemyFast) {
            curr_enem_fast--;
            score.setCurrentScore(++myScore);
          }
          if (enemy instanceof EnemySlow) {
            curr_enem_slow--;
            myScore += 2;
            score.setCurrentScore(myScore);
          }
          // Remove the enemy from the array list to make them "disappear" (not drawn or updated)
          collectionManager.getEnemies().remove(enemy);
          collectionManager.getSprites().remove(enemy);
          //Set the high score to the current score
          // if the current score is higher than the high score
          if (myScore >= high) {
            score.setHighScore(myScore);
            high = score.getHighScore();
          }
        }
      }
      // Spawns new enemies mid-game
      while (collectionManager.getEnemies().size() < ENEM_MAX) {
        // Determine which enemy type to spawn
        int spawnType = rngsus.nextInt(ENEM_TYPES + 1);

        while ((spawnType == 0 && curr_enem_standard >= ENEM_STANDARD_MAX)
            || (spawnType == 1 && curr_enem_fast >= ENEM_FAST_MAX)
            || (spawnType == 2 && curr_enem_slow >= ENEM_SLOW_MAX)) {

          spawnType = rngsus.nextInt(ENEM_TYPES + 1);

        }
        // Spawn the enemy depending on the spawn type
        //Make this clearer by getting rid of magic numbers for spawn type
        if (spawnType == 0) {
          Enemy newEnemy = new EnemyStandard(this, collectionManager.getPlayer());
          curr_enem_standard++;
          collectionManager.getEnemies().add(newEnemy);
          collectionManager.getSprites().add(newEnemy);
        } else if (spawnType == 1) {
          Enemy newEnemy = new EnemyFast(this, collectionManager.getPlayer());
          curr_enem_fast++;
          collectionManager.getEnemies().add(newEnemy);
          collectionManager.getSprites().add(newEnemy);
        } else if (spawnType == 2) {
          Enemy newEnemy = new EnemySlow(this, collectionManager.getPlayer());
          curr_enem_slow++;
          collectionManager.getEnemies().add(newEnemy);
          collectionManager.getSprites().add(newEnemy);
        }

      }

    } else if (stateOfGame == GameState.pause) {
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
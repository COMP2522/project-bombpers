package org.bcit.comp2522.project;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import org.bcit.comp2522.project.enemies.Enemy;
import org.bcit.comp2522.project.enemies.EnemyFast;
import org.bcit.comp2522.project.enemies.EnemySlow;
import org.bcit.comp2522.project.enemies.EnemyStandard;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;


//import javax.swing.plaf.MenuBarUI; *unused import - consider deleting?*

/**
 * Window class - is the main class of the game.
 */
public class Window extends PApplet {

  private boolean isLeftPressed = false;
  private boolean isRightPressed = false;
  private boolean isUpPressed = false;
  private boolean isDownPressed = false;
  private static final int ENEM_TYPES = 3;
  private static final int ENEM_MAX = 30;
  private static final int ENEM_STANDARD_MAX = 20;
  private static final int ENEM_FAST_MAX = 10;
  private static final int ENEM_SLOW_MAX = 5;
  private static int curr_enem_standard = 0;
  private static int curr_enem_fast = 0;
  private static int curr_enem_slow = 0;
  private static int myScore = 0;
  private static int high = 0;
  ArrayList<Sprite> sprites;
  ArrayList<Enemy> enemies;
  Player player;

  private Background background;
  private int minSize = 15; // should be a local variable in the context it's currently used in.

  private int maxSize = 20; /*this isn't used?*/
  private int state = 0;
  private Random rngsus = new Random();

  /**
   * Creates a window of size 500 x 500 pixels.
   */
  public void settings() {
    size(500, 500);
    // This will make the game fullscreen however, it will make the game lag
    //fullScreen();
  }

  //  public void mousePressed() {
  //    background(64);
  //  }

  /**
   * Setup of the game.
   */
  public void setup() {
    this.init();

    // Create the background object
    background = new Background(this);
  }

  /**
   * Initializes the  Sprites of the game.
   */
  public void init() {
    enemies = new ArrayList<>();
    sprites = new ArrayList<>();
  }

  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT -> isLeftPressed = true;
      case RIGHT -> isRightPressed = true;
      case UP -> isUpPressed = true;
      case DOWN -> isDownPressed = true;
      default -> System.out.println(); // switch needed a default case, it does nothing.
    }
    updatePlayerDirection();
  }

  @Override
  public void keyReleased(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT -> isLeftPressed = false;
      case RIGHT -> isRightPressed = false;
      case UP -> isUpPressed = false;
      case DOWN -> isDownPressed = false;
      default -> System.out.println(); // switch needed a default case, it does nothing.
    }
    updatePlayerDirection();
  }

  private void updatePlayerDirection() {
    int directionX = 0;
    int directionY = 0;
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
    if (directionX != 0 || directionY != 0) {
      player.setDirection(new PVector(directionX, directionY));
    } else {
      player.setDirection(new PVector(0, 0));
    }
  }



  /**
   * Draws everything in the window.
   */

  public void draw() {
    Menu menu = new Menu(50, 145, "Welcome!", this);
    Menu menu2 = new Menu(30, 120, "Game Over!", this);
    Menu menu3 = new Menu(80, 120, "Paused!", this);
    Menu menu4 = new Menu(50, 120, "Pick a Character!", this);
    Score score = new Score(180, 30, myScore, this);

    background.draw();
    //Start Screen
    if (state == 0) {
      menu.displayMenu(state, 100);
      if (mousePressed && (mouseButton == LEFT)
          && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);
        //for the if statement that has the game animations
        mousePressed = false;
        state = 4;
        score.setHighScore(0);
      }
    } else if (state == 4) { //Pick a character
      menu4.displayMenu(state, 60);
      PImage characterSprite = loadImage("../img/idle_01.png");
      if (mousePressed
          && (mouseButton == LEFT)
          && ((mouseX >= 120
          && mouseX < 312)
          && (mouseY >= 199
          && mouseY <= 244))) {
        player = new Speedy(new PVector((float) this.width / 2, (float) this.height / 2),
            new PVector(0, 0),
            50,
            2.5f,
            new Color(0, 255, 0),
            this, 5, 2, 1,
            "speedy",
            characterSprite);
        sprites.add(player);
        background(0);
        state = 1;
      }
      if (mousePressed
          && (mouseButton == LEFT)
          && ((mouseX >= 120
          && mouseX < 312)
          && (mouseY >= 299
          && mouseY <= 344))) {
        player = new Tank(new PVector((float) this.width / 2, (float) this.height / 2),
            new PVector(0, 0),
            50,
            0.5f,
            new Color(0, 255, 0),
            this, 50, 2, 1,
            "Tank",
            characterSprite);
        sprites.add(player);
        background(0);
        state = 1;
        //To get hovering just do above if statement but don't check for mousePressed
      }
    } else if (state == 1) { //Game starts
      if (keyPressed) {
        if (key == 'p' || key == 'P') {
          //state to pause
          state = 3;
        }
      }
      //      Projectile bullet = new Projectile(1,1,1,mouseX,mouseY,1,this);
      //      bullet.setXPosition(player.getXPosition());
      //      bullet.setYPosition(player.getYPosition());
      //      bullet.setSize(30);
      //      bullet.setDirection(new PVector(0,100));
      //      bullet.draw();

      score.displayScore(state);

      // this was overwriting and making the whole backyard black
      //      background(0);
      for (Sprite sprite : sprites) {
        sprite.update();
        sprite.draw();
      }

      ArrayList<Enemy> toRemove = new ArrayList<>();
      for (Enemy enemy : enemies) {
        if (Enemy.collided(player, enemy)) {
          toRemove.add(enemy);

        }
      }
      for (Enemy enemy : toRemove) {
        if (player.compareTo(enemy) > 0) {
          if (enemy instanceof EnemyStandard) {
            curr_enem_standard--;
            // All enemy types are instances of Enemy_Standard //TODO: Should be instances of Enemy.
            // So all subtypes will have +1 score from base type
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
          enemies.remove(enemy);
          sprites.remove(enemy);
          //player.sizeUp(enemy.size);

          score.displayScore(state);
          score.setHighScore(myScore);
          if (myScore >= high) {
            high = score.getHighScore();
          }

        } else {
          init();

        }
      }
      // Spawns new enemies mid-game
      while (enemies.size() < ENEM_MAX) {

        // Randomize position and orientation of enemy
        PVector position = new PVector(random(0, width), random(0, height));
        PVector direction = new PVector(random(-1, 1), random(-1, 1));

        // Determine which enemy type to spawn
        int spawnType = rngsus.nextInt(ENEM_TYPES);

        while ((spawnType == 0 && curr_enem_standard >= ENEM_STANDARD_MAX)
            || (spawnType == 1 && curr_enem_fast >= ENEM_FAST_MAX)
            || (spawnType == 2 && curr_enem_slow >= ENEM_SLOW_MAX)) {

          spawnType = rngsus.nextInt(ENEM_TYPES + 1);

        }
        if (spawnType == 0) {
          EnemyStandard newEnemy = new EnemyStandard(position, direction, this);
          curr_enem_standard++;

          // Add enemy to current list
          enemies.add(newEnemy);
          sprites.add(newEnemy);
        } else if (spawnType == 1) {
          EnemyFast newEnemy = new EnemyFast(position, direction, this);
          curr_enem_fast++;

          // Add enemy to current list
          enemies.add(newEnemy);
          sprites.add(newEnemy);
        } else if (spawnType == 2) {
          EnemySlow newEnemy = new EnemySlow(position, direction, this);
          curr_enem_slow++;

          // Add enemy to current list
          enemies.add(newEnemy);
          sprites.add(newEnemy);
        }
      }

    } else if (state == 3) { //Pause screen
      if (myScore >= score.getHighScore()) {
        score.setHighScore(high);
      }
      menu3.displayMenu(state, 100);

      score.displayScore(state);

      if (mousePressed && (mouseButton == LEFT)
          && (mouseX >= 120 && mouseX < 312)
          && (mouseY >= 199 && mouseY <= 244)) {
        background(0);

        //score needs to be 0, so it's reset everytime you restart
        //score = 0;
        //for the if statement that has the game animations
        state = 1;
      }
    } else {
      //End scenario when game ends goes to end screen

      if (myScore >= score.getHighScore()) {
        score.setHighScore(high);
      }
      menu2.displayMenu(state, 90);
      score.displayScore(state);

      if (mousePressed && (mouseButton == LEFT)
          && (mouseX >= 120 && mouseX < 312)
          && (mouseY >= 199 && mouseY <= 244)) {
        background(0);
        // reset score to 0

        myScore = 0;
        //for the if statement that has the game animations
        state = 1;
      }
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
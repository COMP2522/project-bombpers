package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.bcit.comp2522.project.enemies.Enemy;
import org.bcit.comp2522.project.enemies.EnemyFast;
import org.bcit.comp2522.project.enemies.EnemySlow;
import org.bcit.comp2522.project.enemies.EnemyStandard;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * Window class - is the main class of the game.
 */
public class Window extends PApplet {

  private boolean isLeftPressed = false;
  private boolean isRightPressed = false;
  private boolean isUpPressed = false;
  private boolean isDownPressed = false;
  private static final int ENEM_TYPES = 3;
  private static final int ENEM_MAX = 3;
  private static final int ENEM_STANDARD_MAX = 10;
  private static final int ENEM_FAST_MAX = 10;
  private static final int ENEM_SLOW_MAX = 5;
  private static int curr_enem_standard = 0;
  private static int curr_enem_fast = 0;
  private static int curr_enem_slow = 0;
  public PImage enemyStandardSprite;
  public PImage enemySlowSprite;
  public PImage enemyFastSprite;
  private static int myScore = 0;
  private static int high = 0;
  CollectionManager collectionManager;
  private ConcurrentLinkedQueue<Projectile> projectiles = new ConcurrentLinkedQueue<>();

  private Menu menu, menu2, menu3, menu4;
  private Score score;

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
  }

  /**
   * Setup of the game.
   */
  public void setup() {
    this.init();

    noStroke();

    // Create the background object
    background = new Background(this);

    // Create the Menu Object
    menu = new Menu(50, 145, "Welcome!", this);
    menu2 = new Menu(30, 120, "Game Over!", this);
    menu3 = new Menu(80, 120, "Paused!", this);
    menu4 = new Menu(50, 120, "Pick a Character!", this);
    score = new Score(180, 30, myScore, this);
  }

  /**
   * Initializes the  Sprites of the game.
   */
  public void init() {
    collectionManager = new CollectionManager();
    enemyStandardSprite = loadImage(EnemyStandard.ENEMY_SPRITE);
    enemySlowSprite = loadImage(EnemySlow.ENEMY_SPRITE);
    enemyFastSprite = loadImage(EnemyFast.ENEMY_SPRITE);
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
      collectionManager.getPlayer().setDirection(new PVector(directionX, directionY));
    } else {
      collectionManager.getPlayer().setDirection(new PVector(0, 0));
    }
  }
  @Override
  public void mousePressed() {
    if (state == 1 && mouseButton == LEFT) {
      System.out.println("shot");
      PVector mousePosition = new PVector(mouseX, mouseY);
      PVector playerPosition = collectionManager.getPlayer().getPosition();
      PVector direction = PVector.sub(mousePosition, playerPosition).normalize();

      Projectile projectile = new Projectile(this, playerPosition, direction);
      projectiles.add(projectile);
      collectionManager.getSprites().add(projectile);
    }
  }

  /**
   * Draws everything in the window.
   */

  public void draw() {
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
      if (mousePressed
          && (mouseButton == LEFT)
          && ((mouseX >= 120
          && mouseX < 312)
          && (mouseY >= 199
          && mouseY <= 244))) {
        collectionManager.player = new Player(this);
        collectionManager.getSprites().add(collectionManager.player);
        background(0);
        state = 1;
      }
    } else if (state == 1) { //Game starts
      if (keyPressed) {
        if (key == 'p' || key == 'P') {
          //state to pause
          state = 3;
        }
      }
      score.displayScore(state);

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
            projectilesToRemove.add(projectile);
            if (enemy instanceof EnemyStandard) {
              curr_enem_standard--;
              score.setCurrentScore(++myScore);
            }
            if (enemy instanceof EnemyFast) {
              curr_enem_fast--;
              myScore += 2;
              score.setCurrentScore(myScore);
            }
            if (enemy instanceof EnemySlow) {
              curr_enem_slow--;
              myScore += 3;
              score.setCurrentScore(myScore);
            }
            score.displayScore(state);
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
      while (collectionManager.getEnemies().size() < ENEM_MAX) {

        // Determine which enemy type to spawn
        int spawnType = rngsus.nextInt(ENEM_TYPES + 1);

        while ((spawnType == 0 && curr_enem_standard >= ENEM_STANDARD_MAX)
            || (spawnType == 1 && curr_enem_fast >= ENEM_FAST_MAX)
            || (spawnType == 2 && curr_enem_slow >= ENEM_SLOW_MAX)) {

          spawnType = rngsus.nextInt(ENEM_TYPES + 1);

        }
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
        state = 1;
      }
    } else {

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
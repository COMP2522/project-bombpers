package org.bcit.comp2522.project;

import org.bcit.comp2522.project.enemies.Enemy_Base;
import org.bcit.comp2522.project.enemies.Enemy_Fast;
import org.bcit.comp2522.project.enemies.Enemy_Slow;
import org.bcit.comp2522.project.enemies.Enemy_Standard;

import java.util.Iterator;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.plaf.MenuBarUI;
import java.awt.*;
import java.util.ArrayList;

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
  ArrayList<Enemy_Base> enemies;
  Player player;
  Wall wall;
  private Background background;
  private int minSize = 15;
  private int maxSize = 20;
  private int state = 0;
  private Random rngsus = new Random();

  @Override
  public void mousePressed() {
    if (state == 1) { // Game is running
      PVector position = new PVector(player.getXPosition(), player.getYPosition());
      PVector direction = PVector.sub(new PVector(mouseX, mouseY), position).normalize();
      float size = 30;
      float speed = 50;
      Color color = new Color(255, 255, 0); // Choose a color for the projectile
      int health = 1;
      int damage = 1;

      Projectile bullet = new Projectile(position, direction, size, speed, color, this, health, damage);
      player.getProjectiles().add(bullet); // Add the bullet to the player's projectiles list
      sprites.add(bullet); // Add the bullet to the sprites list for drawing and updating
    }
  }


  public void settings() {
    size(500, 500);
    // This will make the game fullscreen however, it will make the game lag
//    fullScreen();
  }

/*  public void mousePressed() {
    background(64);
  }*/

  public void setup() {
    this.init();
    // Create the background object
    background = new Background(this);
  }

  public void init() {
    enemies = new ArrayList<Enemy_Base>();
    sprites = new ArrayList<Sprite>();
    wall = new Wall(
        new PVector(200, 100),
        new PVector(0, 0),
        minSize + 50,
        1.2f,
        new Color(60, 150, 197),
        this);
    sprites.add(wall);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT:
        isLeftPressed = true;
        break;
      case RIGHT:
        isRightPressed = true;
        break;
      case UP:
        isUpPressed = true;
        break;
      case DOWN:
        isDownPressed = true;
        break;
    }
    updatePlayerDirection();
  }

  @Override
  public void keyReleased(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case LEFT:
        isLeftPressed = false;
        break;
      case RIGHT:
        isRightPressed = false;
        break;
      case UP:
        isUpPressed = false;
        break;
      case DOWN:
        isDownPressed = false;
        break;
    }
    updatePlayerDirection();
  }

  private void updatePlayerDirection() {
    int xDirection = 0;
    int yDirection = 0;
    if (isLeftPressed) {
      xDirection--;
    }
    if (isRightPressed) {
      xDirection++;
    }
    if (isUpPressed) {
      yDirection--;
    }
    if (isDownPressed) {
      yDirection++;
    }
    if (xDirection != 0 || yDirection != 0) {
      player.setDirection(new PVector(xDirection, yDirection));
    } else {
      player.setDirection(new PVector(0, 0));
    }
  }



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
    } else if (state == 4){ //Pick a character
      menu4.displayMenu(state,60);
      PImage characterSprite = loadImage("../img/idle_01.png");
      if ( mousePressed  && (mouseButton == LEFT) && ((mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244))){
        player = new Speedy(new PVector(this.width/2,this.height/2),
                new PVector(0,0),
                50,
                2.5f,
                new Color(0,255,0),
                this, 5, 2, 1,
                "speedy",
                characterSprite);
        sprites.add(player);
        background(0);
        state = 1;
      }
      if( mousePressed  && (mouseButton == LEFT) && ((mouseX >= 120 && mouseX < 312) && (mouseY >= 299 && mouseY <= 344))){
          player = new Tank(new PVector(this.width/2,this.height/2),
                  new PVector(0,0),
                  50,
                  0.5f,
                  new Color(0,255,0),
                  this, 50, 2, 1,
                  "Tank",
                  characterSprite);
          sprites.add(player);
        background(0);
        state = 1;
        //To get hovering just do above if statement but don't check for mousePressed
      }
    } else if (state == 1) { // Game starts
      if (keyPressed) {
        if (key == 'p' || key == 'P') {
          // State to pause
          state = 3;
        }
      }

      score.displayScore(state);

      for (Sprite sprite : sprites) {
        sprite.update();
        sprite.draw();
        if (wall.collided(wall, sprite)) {
          wall.bounce(sprite);
        }
      }

      // Check for collisions between projectiles and enemies
      for (Iterator<Projectile> projIter = player.getProjectiles().iterator(); projIter.hasNext();) {
        Projectile projectile = projIter.next();
        boolean projectileRemoved = false;

        for (Iterator<Enemy_Base> enemyIter = enemies.iterator(); enemyIter.hasNext();) {
          Enemy_Base enemy = enemyIter.next();

          if (Sprite.collided(projectile, enemy)) {
            projIter.remove();
            enemyIter.remove();
            sprites.remove(enemy);
            projectileRemoved = true;

            // Update the score and high score
            myScore++;
            if (myScore > high) {
              high = myScore;
            }

            break;
          }
        }

        // Check for collisions between projectiles and the wall
        if (!projectileRemoved && Sprite.collided(projectile, wall)) {
          projectile.bounce();
        }
      }

      ArrayList<Enemy_Base> toRemove = new ArrayList<Enemy_Base>();
      for (Enemy_Base enemyBase : enemies) {
        if (Sprite.collided(player, enemyBase)) {
          toRemove.add(enemyBase);
        }
      }

      for (Enemy_Base enemyBase : toRemove) {
        // (Existing code for handling enemy collisions)

        // Remove only the enemy, not the player
        enemies.remove(enemyBase);
        sprites.remove(enemyBase);
      }

      // Spawns new enemies mid-game
      while (enemies.size() < ENEM_MAX) {
        // Randomize position and orientation of enemy
        PVector position = new PVector(random(0, width), random(0, height));
        PVector direction = new PVector(random(-1, 1), random(-1, 1));

        // Determine which enemy type to spawn
        int spawnType = rngsus.nextInt(ENEM_TYPES);
        while (
                (spawnType == 0 && curr_enem_standard >= ENEM_STANDARD_MAX) ||
                        (spawnType == 1 && curr_enem_fast >= ENEM_FAST_MAX) ||
                        (spawnType == 2 && curr_enem_slow >= ENEM_SLOW_MAX)
        ) {
          spawnType = rngsus.nextInt(ENEM_TYPES);
        }
        if (spawnType == 0) {
          Enemy_Standard newEnemy = new Enemy_Standard(
                  position,
                  direction,
                  this);
          curr_enem_standard++;
          // Add enemy to current list
          enemies.add(newEnemy);
          sprites.add(newEnemy);
        } else if (spawnType == 1) {
          Enemy_Fast newEnemy = new Enemy_Fast(
                  position,
                  direction,
                  this);
          curr_enem_fast++;
          // Add enemy to current list
          enemies.add(newEnemy);
          sprites.add(newEnemy);
        } else if (spawnType == 2) {
          Enemy_Slow newEnemy = new Enemy_Slow(
                  position,
                  direction,
                  this);
          curr_enem_slow++;
          // Add enemy to current list
          enemies.add(newEnemy);
          sprites.add(newEnemy);
        }
      }
    }
    else if (state == 3) { //Pause screen
      if (myScore >= score.getHighScore()) {
        score.setHighScore(high);
      }
      menu3.displayMenu(state, 100);
      score.displayScore(state);

      if (mousePressed && (mouseButton == LEFT)
          && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);

        //score needs to be 0 so it si reset everytime you restart
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
          && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);
        // reset score to 0
        myScore = 0;
        //for the if statement that has the game animations
        state = 1;
      }
    }
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}

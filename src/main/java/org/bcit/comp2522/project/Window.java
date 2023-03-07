package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

/**
 * Lab-02 starter code.
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 * @author paul_bucci
 */
public class Window extends PApplet {
  ArrayList<Sprite> sprites;
  ArrayList<Sprite> enemies;
  Sprite wall;
  Sprite player;
  int numEnemies = 10;
  int minSize = 10;
  int maxSize = 20;
  float moveSpeed = 10;

  Projectile projectile = new Projectile(0, 0, 0, this);

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }


  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    this.init();
  }

  /**
   * initializes the sprites.
   */
  public void init() {
    enemies = new ArrayList<>();
    sprites = new ArrayList<>();
//    wall = new Wall(new PVector(random(this.width), random(this.height)),
//        new PVector(0, 0), random(50, 100), 0,
//        new Color(255, 255, 0), this);

    player = new Player(new PVector(this.width / 2, this.height / 2),
        new PVector(0, 1), minSize + 1, 2,
        new Color(0, 255, 0), this);

    for (int i = 0; i < numEnemies; i++) {
      float x, y;
      if (random(2) < 1) { // Spawn on the left or right edge
        x = random(2) < 1 ? 0 : width;
        y = random(height);
      } else { // Spawn on the top or bottom edge
        x = random(width);
        y = random(2) < 1 ? 0 : height;
      }
      enemies.add(new Enemy(
              new PVector(x, y),
              new PVector(random(-1, 1), random(-1, 1)),
              random(minSize, maxSize), random(0, 2),
              new Color(255, 0, 0), this));
    }


//    for (int i = 0; i < numEnemies; i++) {
//      enemies.add(new Enemy(
//          new PVector(random(0, this.width), random(0, this.height)),
//          new PVector(random(-1, 1), random(-1, 1)),
//          random(minSize, maxSize), random(0, 2),
//          new Color(255, 0, 0), this));
//    }
    sprites.addAll(enemies);
    sprites.add(player);
//    sprites.add(wall);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    switch (keyCode) {
      case LEFT ->
        // move left
              player.setPosition(player.getPosition().add(new PVector(-moveSpeed, 0)));
      case RIGHT ->
        // move right
              player.setPosition(player.getPosition().add(new PVector(moveSpeed, 0)));
      case UP ->
        // move up
              player.setPosition(player.getPosition().add(new PVector(0, -moveSpeed)));
      case DOWN ->
        // move down
              player.setPosition(player.getPosition().add(new PVector(0, moveSpeed)));
    }

//    int keyCode = event.getKeyCode();
//    switch (keyCode) {
//      case LEFT ->
//        // handle left
//          player.setDirection(
//              player.getDirection().rotate(-Window.PI / 16));
//      case RIGHT ->
//        // handle right
//          player.setDirection(
//              player.getDirection().rotate(Window.PI / 16));
//    }
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
      projectile.draw();
//      if (Sprite.collided(sprite, wall)) {
//        sprite.direction.rotate(PApplet.HALF_PI);
//      }
    }
    ArrayList<Sprite> toRemove = new ArrayList<>();
    for (Sprite enemy : enemies) {
//      enemy.chasePlayer((Player) player);
      if (Sprite.collided(player, enemy)) {
        toRemove.add(enemy);
      }

    }
    for (Sprite enemy : toRemove) {
      // TODO: implement compareTo and equals to make this work
      if (player.compareTo(enemy) == 1) {
        enemies.remove(enemy);
        sprites.remove(enemy);
        player.setSize(player.getSize() + 3);
      } else {
        init();
      }
      if (enemies.size() == 0) {
        init();
      }
    }
  }

  public void mousePressed() {
    float processingMouseX = mouseX;
    float processingMouseY = mouseY;
    PVector mousePos = new PVector(processingMouseX, processingMouseY);
    PVector playerPos = player.getPosition();
    PVector dir = PVector.sub(mousePos, playerPos).normalize().mult(moveSpeed);
    Projectile projectile = new Projectile(playerPos, dir, 2, 2, new Color(255, 255, 255), this);
    sprites.add(projectile);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"window"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }
}
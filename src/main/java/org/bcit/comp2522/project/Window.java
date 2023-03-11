package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {
  ArrayList<Sprite> sprites;
  ArrayList<Enemy> enemies;
  Player player;
  Wall wall;
  int numEnemies = 10;
  int minSize = 15;
  int maxSize = 20;

  public void settings() {
    size(500, 500);
  }

  public void mousePressed() {
    background(64);
  }

  public void setup() {
    this.init();
  }

  public void init() {
    //TODO change player constructor to match sprite class
    enemies = new ArrayList<Enemy>();
    sprites = new ArrayList<Sprite>();
    player = new Player(
            new PVector(this.width/2,this.height/2),
            new PVector(0,1),
            minSize + 1,
            2,
            new Color(0,255,0),
            this, 5, 2, 1,
            "player");

    wall = new Wall(
            new PVector(200,100),
            new PVector(0,0),
            minSize + 50,
            2,
            new Color(60,150,197),
            this);

    for (int i = 0; i < numEnemies; i++) {
      enemies.add(new Enemy(
              new PVector(random(0, this.width), random(0, this.height)),
              new PVector(random(-1, 1), random(-1,1)),
              random(minSize, maxSize),
              random(0,2),
              new Color(255, 0, 0),
              this,
              5, 2, "enemy"
      ));
    }
    sprites.addAll(enemies);
    sprites.add(player);
    sprites.add(wall);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    int keyCode = event.getKeyCode();
    switch( keyCode ) {
      case LEFT:
        // handle left
        player.setDirection(new PVector(-1, 0));
        break;
      case RIGHT:
        // handle right
        player.setDirection(new PVector(1, 0));
        break;
      case UP:
        player.setDirection(new PVector(0, -1));
        break;
      case DOWN:
        player.setDirection(new PVector(0, 1));
        break;
    }
  }

  public void draw() {
    background(0);
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
      if (wall.collided(wall, sprite)) {
        wall.bounce(sprite);
        //System.out.println("Monkey");
      }
    }
    ArrayList<Enemy> toRemove = new ArrayList<Enemy>();
    for (Enemy enemy : enemies) {
      if (Enemy.collided(player, enemy)) {
        toRemove.add(enemy);
      }
    }
    for (Enemy enemy : toRemove) {
      if (player.compareTo(enemy) > 0) {
        enemies.remove(enemy);
        sprites.remove(enemy);
        //player.sizeUp(enemy.size);
      } else {
        init();
      }
    }
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }

}

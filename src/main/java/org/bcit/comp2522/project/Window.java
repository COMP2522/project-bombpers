package org.bcit.comp2522.project;

import org.bcit.comp2522.project.enemies.Enemy_Base;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.plaf.MenuBarUI;
import java.awt.*;
import java.util.ArrayList;

public class Window extends PApplet {
  ArrayList<Sprite> sprites;
  ArrayList<Enemy_Base> enemies;
  Player player;
  Wall wall;
  private Background background;
  int numEnemies = 10;
  int minSize = 15;
  int maxSize = 20;
  int state = 0;

  public void settings() {
    size(500, 500);
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
    //TODO change player constructor to match sprite class
    enemies = new ArrayList<Enemy_Base>();
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
      enemies.add(new Enemy_Base(
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
    Menu menu = new Menu(50, 145, "Welcome!", this);
    Menu menu2 = new Menu(30, 120, "Game Over!", this);
    Menu menu3 = new Menu(80, 120, "Paused!", this);
    Menu menu4 = new Menu(50, 120, "Pick a Character!", this);
    background.draw();
    //Start Screen
    if(state == 0) {
      menu.displayMenu(state,100);
      if (mousePressed  && (mouseButton == LEFT)
              && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);
        //score needs to be 0 so it is reset everytime you restart
        //Score score = new Score(0)
        //for the if statement that has the game animations
        mousePressed = false;
        state = 4;
      }

    } else if (state == 4){
      menu4.displayMenu(state,60);
      if ( mousePressed  && (mouseButton == LEFT)
              && ((mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244))
        || mousePressed  && (mouseButton == LEFT)
              && ((mouseX >= 120 && mouseX < 312) && (mouseY >= 299 && mouseY <= 344)) ){
        background(0);
        state = 1;
        //To get hovering just do above if statement but don't check for mousePressed
      }

    } else if(state == 1) { //Game starts
      if (keyPressed) {
        if (key == 'p' || key == 'P') {
          //state to pause
          state = 3;
        }
      }
      // this was over writing and making the whole backyard black
//      background(0);
      for (Sprite sprite : sprites) {
        sprite.update();
        sprite.draw();
        if (wall.collided(wall, sprite)) {
          wall.bounce(sprite);
          //System.out.println("Monkey");
        }
      }
      ArrayList<Enemy_Base> toRemove = new ArrayList<Enemy_Base>();
      for (Enemy_Base enemyBase : enemies) {
        if (Enemy_Base.collided(player, enemyBase)) {
          toRemove.add(enemyBase);
        }
      }
      for (Enemy_Base enemyBase : toRemove) {
        if (player.compareTo(enemyBase) > 0) {
          enemies.remove(enemyBase);
          sprites.remove(enemyBase);
          //player.sizeUp(enemy.size);
        } else {
          init();
        }
      }
    } else if(state == 3) { //Pause screen
      menu3.displayMenu(state,100);
      if (mousePressed && (mouseButton == LEFT)
              && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);

        //score needs to be 0 so it si reset everytime you restart
        //score = 0;
        //for the if statement that has the game animations
        state = 1;
      }
    }
     else {
      //End scenario when game ends goes to end screen

      menu2.displayMenu(state,90);
      if (mousePressed  && (mouseButton == LEFT)
              && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);

        //score needs to be 0 so it si reset everytime you restart
        //score = 0;
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

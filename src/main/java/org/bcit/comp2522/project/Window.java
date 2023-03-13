package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.plaf.MenuBarUI;
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
  int state = 0;

  public void settings() {
    size(500, 500);
  }

/*  public void mousePressed() {
    background(64);
  }*/

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
            minSize + 2,
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
  int myScore = 0;
int high = 0;
  public void draw() {
    Menu menu = new Menu(50, 145, "Welcome!", this);
    Menu menu2 = new Menu(30, 120, "Game Over!", this);
    Menu menu3 = new Menu(80, 120, "Paused!", this);
    Menu menu4 = new Menu(50, 120, "Pick a Character!", this);

    Score score = new Score(180,30,myScore,  this);

    //Start Screen
    if(state == 0) {
      menu.displayMenu(state,100);
      if (mousePressed  && (mouseButton == LEFT)
              && (mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244)) {
        background(0);
        //for the if statement that has the game animations
        mousePressed = false;
        state = 4;
        score.setHighScore(0);
      }

    } else if (state == 4){ //Pick a character
      menu4.displayMenu(state,60);
      if ( mousePressed  && (mouseButton == LEFT)
              && ((mouseX >= 120 && mouseX < 312) && (mouseY >= 199 && mouseY <= 244))
        || mousePressed  && (mouseButton == LEFT)
              && ((mouseX >= 120 && mouseX < 312) && (mouseY >= 299 && mouseY <= 344)) ){
        background(0);
        state = 1;
        //To get hovering just do above if statement but don't check for mousePressed
      }

    } else if(state == 1){ //Game starts
      if (keyPressed) {
        if (key == 'p' || key == 'P') {
          //state to pause
          state = 3;
        }
      }

      background(0);
      Projectile bullet = new Projectile(1,1,1,mouseX,mouseY,1,this);
      bullet.setXPosition(player.getXPosition());
      bullet.setYPosition(player.getYPosition());
      bullet.setSize(30);
      bullet.setDirection(new PVector(0,100));
      bullet.draw();


      score.displayScore(state);
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
          score.setCurrentScore(myScore++);

          score.displayScore(state);

          score.setHighScore(myScore);
          if(myScore >= high) {
            high = score.getHighScore();
          }
        } else {
          //state to end
          state  = 2;
          init();

        }
      }
    } else if(state == 3) { //Pause screen
      if(myScore >= score.getHighScore()) {
        score.setHighScore(high);
      }
      menu3.displayMenu(state,100);
      score.displayScore(state);

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
      if(myScore >= score.getHighScore()) {
        score.setHighScore(high);
      }
      menu2.displayMenu(state,90);
      score.displayScore(state);

      if (mousePressed  && (mouseButton == LEFT)
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

package org.bcit.comp2522.project;

//import org.bcit.comp2522.project.enemies.Enemy;
import processing.core.PImage;
import processing.core.PVector;

public class Player extends Sprite {
  PImage characterSprite;
  public static final float RESIZE = 1.0f;
  public static final int PLAYER_HEALTH = 5;
  public static final int PLAYER_DAMAGE = 1;
  public static final float PLAYER_SIZE = 65;
  public static final float PLAYER_SPEED = 1.5f;
  public static final String PLAYER_SPRITE = "../img/idle_0.png";

  public Player(Window window) {
    super(window);
    this.health = PLAYER_HEALTH;
    this.damage = PLAYER_DAMAGE;
    this.position = new PVector((float) window.width / 2, (float) window.height / 2);
    this.direction = new PVector(0, 0);
    this.size = PLAYER_SIZE;
    this.speed = PLAYER_SPEED;
    this.characterSprite = window.loadImage(PLAYER_SPRITE);
  }


  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  @Override
  public void compareTo() {

  }

  /**
   *
   */
  @Override
  public void draw() {
    window.image(characterSprite, position.x, position.y, size * RESIZE, size * RESIZE);
  }

  /**
   *
   */
  @Override
  public void move() {
    if (window.keyPressed) {
      if (window.key == 'w' || window.key == 'W') {
        position.y -= speed;
      }
      if (window.key == 'a' || window.key == 'A') {
        position.x -= speed;
      }
      if (window.key == 's' || window.key == 'S') {
        position.y += speed;
      }
      if (window.key == 'd' || window.key == 'D') {
        position.x += speed;
      }
    }
  }

}

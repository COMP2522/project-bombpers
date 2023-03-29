package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.io.Serializable;

/**
 * Player class - is a child of the Sprite class that represents the player.
 */
public class Player extends Sprite {
  /**
   * The image of the player.
   */
  PImage characterSprite;
  /**
   * The resize factor of the player so when enemies collide with the player,
   * the player hitbox is fits the image.
   */
  public static final float RESIZE = 1.0f;
  /**
   * The health of the player.
   */
  public static final int PLAYER_HEALTH = 5;
  /**
   * The damage caused by the player.
   */
  public static final int PLAYER_DAMAGE = 1;
  /**
   * The size of the player.
   */
  public static final float PLAYER_SIZE = 65;
  /**
   * The speed of the player.
   */
  public static final float PLAYER_SPEED = 1.5f;
  /**
   * The location of the player image.
   */
  public static final String PLAYER_SPRITE = "../img/idle_0.png";

  /**
   * Constructor for the Player class.
   *
   * @param window the window where the player is drawn
   */
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
   * Draws the player by drawing the image of the player.
   */
  @Override
  public void draw() {
    window.image(characterSprite, position.x, position.y, size * RESIZE, size * RESIZE);
  }

  /**
   * Moves the player by checking if the player pressed a key and moving the player
   * in the direction of the key pressed.
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

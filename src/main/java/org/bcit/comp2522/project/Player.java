package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Player class - is a child of the Sprite class that represents the player.
 */
public class Player extends Sprite {
  /**
   * The image of the player.
   */
  PImage characterSprite;
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
  public static final float PLAYER_SIZE = 30;
  /**
   * The speed of the player.
   */
  public static final float PLAYER_SPEED = 1.5f;
  /**
   * The location of the player image.
   */
  public static final String PLAYER_SPRITE = "../img/idle_0v2.png";

  private static Player player;

  private float imageSize;


  /**
   * Constructor for the Player class.
   *
   * @param window the window where the player is drawn
   */
  private Player(Window window) {
    super(window);
    this.health = PLAYER_HEALTH;
    this.damage = PLAYER_DAMAGE;
    this.position = new PVector((float) window.width / 2, (float) window.height / 2);
    this.direction = new PVector(0, 0);
    this.speed = PLAYER_SPEED;
    this.characterSprite = window.loadImage(PLAYER_SPRITE);
    this.imageSize = PLAYER_SIZE;
  }

  public static Player getPlayerInstance(Window window) {
    if (player == null) {
      player = new Player(window);
    }
    return player;
  }


  @Override
  public void collide(Sprite one, Sprite two) {

  }

  /**
   * Draws the player by drawing the image of the player.
   */
  @Override
  public void draw() {
    window.image(characterSprite, position.x, position.y, imageSize, imageSize);
  }

  /**
   * Moves the player by checking if the player pressed a key and moving the player
   * in the direction of the key pressed.
   */
  @Override
  public void move() {
  }
}

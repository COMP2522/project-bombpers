package org.bcit.comp2522.project;

import processing.core.PVector;


/**
 * Sprite class - is the parent class of all classes that can move, collide, and be drawn.
 */
public abstract class Sprite implements Collidable, Movable, Drawable {
  /** The health of the Sprite. */
  protected int health;
  /** The damage caused by the Sprite. */
  protected int damage;
  /** The position of the Sprite. */
  protected PVector position;

  /** The direction of the Sprite. */
  protected PVector direction;

  /** The size of the Sprite. */

  protected float size;
  /** The x-position of the Sprite. */
  protected int positionX;

  /** The y-position of the Sprite. */
  protected int positionY;

  /** The speed of the Sprite. */
  protected float speed;

  /** The window that the Sprite will be drawn on. */

  protected Window window;

  /**
   * Constructor for Sprite.
   *
   * @param window the window that the Sprite will be drawn on
   */
  public Sprite(Window window) {
    this.window = window;
  }
  /**
   * Gets the position vector of the Sprite.
   *
   * @return the health of the Sprite
   */

  public PVector getPosition() {
    return position.copy();
  }

  /**
   * Checks to see if two sprites have collided.
   *
   * @param a the first sprite
   * @param b the second sprite
   * @return true if the sprites have collided, false otherwise
   */
  public static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    return distance <= (a.getSize() + b.getSize()) / 1.9;
  }

  /**
   * Checks to see if a sprite has collided with a wall.
   */
  public void bounce() {
    if (this.position.x <= 0
        || this.position.x >= window.width
        || this.position.y <= 0
        || this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  /**
   * Updates the Sprite on screen by moving, drawing, and bouncing when necessary.
   */
  protected void update() {
    move();
    draw();
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }


  /**
   * Draws the Sprite.
   */
  public void draw() {
    window.pushStyle();
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }
  //DO we need this compare to function?

  @Override
  public int compareTo(Sprite enemy) {
    if (Float.compare(this.size, enemy.getSize()) < 0) {
      return -1;
    } else if (Float.compare(this.size, enemy.getSize()) >= 0) {
      return 1;
    }
    return 0;
  }

  /**
   * gets the direction of the sprite.
   */
  public PVector getDirection() {
    return direction.copy();
  }

  /**
   * sets the direction of the sprite.
     */
  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  /**
   * gets the health of the sprite.
     */
  public int getHealth() {
    return health;
  }

  /**
   * sets the health of the sprite.
     */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * gets the damage caused by the sprite.
     */
  public int getDamage() {
    return damage;
  }

  /**
   * sets the damage caused by the sprite.
     */
  public void setDamage(int damage) {
    this.damage = damage;
  }

  /**
   * gets the size of the sprite.
     */
  public float getSize() {
    return size;
  }

  /**
   * sets the size of the sprite.
     */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * sets the position of the sprite.
     */
  public void setPosition(PVector cord) {
    this.position = cord;
  }

  /**
   * gets the x-position of the sprite.
     */
  public int getPositionX() {
    return positionX;
  }

  /**
   * sets the x-position of the sprite.
     */
  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  /**
   * gets the y-position of the sprite.
     */
  public int getPositionY() {
    return positionY;
  }

  /**
   * sets the y-position of the sprite.
     */
  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}
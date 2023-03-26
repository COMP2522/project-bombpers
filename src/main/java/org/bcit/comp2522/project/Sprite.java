package org.bcit.comp2522.project;

import java.awt.Color;
import processing.core.PVector;


/**
 * Sprite class - is the parent class of all classes that can move, collide, and be drawn.
 */
public abstract class Sprite implements Collidable, Movable, Drawable {
  protected int health;
  protected int damage;
  protected PVector position;
  protected PVector direction;
  protected Color color;
  protected float size;
  protected int positionX;
  protected int positionY;
  protected float speed;
  protected Window window;

  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }

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
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public float getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }
  public void setPosition(PVector cord) {this.position = cord;}

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}
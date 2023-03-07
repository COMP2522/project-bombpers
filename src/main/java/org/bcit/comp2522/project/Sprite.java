package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;



/**
 * Class represents a sprite concept.
 *
 * @author brett, sukh GitHub Classroom
 * @version 1.0
 */
public abstract class Sprite implements Drawable {
  protected PVector position;
  protected PVector direction;

  protected float size;
  protected float speed;
  protected Color color;
  protected Window window;

  /**
   * Constructor for a sprite.
   *
   * @param position the position
   * @param direction the direction
   * @param size the size
   * @param speed the speed
   * @param color the color
   * @param window the window it's in
   */
  public Sprite(PVector position, PVector direction, float size, float speed,
                Color color, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }

  /**
   * makes the sprite bounce off things.
   */
  public void bounce() {
    if (this.position.x <= 0
        || this.position.x >= window.width
        || this.position.y <= 0
        || this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public PVector getPosition() {
    return position.copy();
  }

  public void update() {
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  /**
   * gets the size of the sprite.
   *
   * @return the size of the sprite
   */
  public float getSize() {
    return size;
  }

  /**
   * Checks to see if sprites have collided.
   *
   * @param a the first sprite to check
   * @param b the second sprite to check
   * @return true if the sprites collided
   */
  public static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    return distance <= (a.getSize() / 2 + b.getSize() / 2);
  }

  public abstract void draw();

  public void setDirection(PVector direction) {
    this.direction = direction;
  }

  public abstract int compareTo(Sprite enemy);

  public void setSize(float size) {
    this.size = size;
  }

  public void chasePlayer(Player player) {
    PVector playerPosition = player.getPosition();
    PVector directionToPlayer = playerPosition.sub(this.position);
    directionToPlayer.normalize();
    this.direction = directionToPlayer;
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

}



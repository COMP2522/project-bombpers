package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;
import java.util.Objects;


/**
 * Child of a sprite.
 *
 * @author sukh, brett.
 * @version 1.0
 */
public class Enemy extends Sprite implements Movable, Comparable {

  /**
   * Child constructor for a Sprite that is an enemy.
   *
   * @param position  the position
   * @param direction the direction
   * @param size      the size
   * @param speed     the speed
   * @param color     the color
   * @param window    the window it's in
   */
  public Enemy(PVector position, PVector direction, float size, float speed,
               Color color, Window window) {

    super(position, direction, size, speed, color, window);

  }

  /**
   * draws the enemy.
   */
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  @Override
  public int compareTo(Sprite enemy) {
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sprite sprite = (Sprite) o;
    return Float.compare(sprite.size, size) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(size);
  }


  @Override
  public void move() {
    super.update();
  }

}

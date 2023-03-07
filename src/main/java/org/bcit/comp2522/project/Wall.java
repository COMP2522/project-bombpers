package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

/**
 * Class represents a wall concept.
 *
 * @author brett, sukh
 * @version 1.0
 */
public class Wall extends Sprite {

  /**
   * Makes a wall from the parent sprite.
   *
   * @param position the position
   * @param direction the direction
   * @param size the size
   * @param speed the speed
   * @param color the color
   * @param window the window it's in
   */
  public Wall(PVector position, PVector direction, float size, float speed,
              Color color, Window window) {
    super(position, direction, size, speed, color, window);
  }

  /**
   * draws the wall.
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


}

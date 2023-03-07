package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;
import java.util.Objects;


/**
 * Class represents a player concept.
 *
 * @author brett, sukh
 * @version 1.0
 */
public class Player extends Sprite implements Movable, Comparable {

  public Player(PVector position, PVector direction, float size, float speed,
                Color color, Window window) {

    super(position, direction, size, speed, color, window);
    this.speed = speed;
    this.direction = new PVector(0, 0);
  }


  /**
   * draws the player to the window.
   */
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }


  @Override
  public int compareTo(Sprite o) {
    try {
      if (o == null) {
        throw new ClassCastException("Not possible");
      }
    } catch (ClassCastException e) {
      e.getMessage();
    }
    Sprite sprite = (Enemy) o;
    return Float.compare(this.getSize(), sprite.getSize());
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
    float deltaX = 0;
    float deltaY = 0;

    if (window.keyPressed) {
      if (window.key == 'a' || window.key == 'A' || window.keyCode == window.LEFT) {
        deltaX -= speed;
      }
      if (window.key == 'd' || window.key == 'D' || window.keyCode == window.RIGHT) {
        deltaX += speed;
      }
      if (window.key == 'w' || window.key == 'W' || window.keyCode == window.UP) {
        deltaY -= speed;
      }
      if (window.key == 's' || window.key == 'S' || window.keyCode == window.DOWN) {
        deltaY += speed;
      }
    }

    PVector newPosition = new PVector(position.x + deltaX, position.y + deltaY);

    // Check if the new position is within the boundaries of the window
    if (newPosition.x < size / 2) {
      newPosition.x = size / 2;
    }
    if (newPosition.x > window.width - size / 2) {
      newPosition.x = window.width - size / 2;
    }
    if (newPosition.y < size / 2) {
      newPosition.y = size / 2;
    }
    if (newPosition.y > window.height - size / 2) {
      newPosition.y = window.height - size / 2;
    }

    position = newPosition;

//    // Save the current position in case we need to reset it later
//    PVector prevPosition = new PVector(position.x, position.y);
//
//    // Update the position of the player based on their direction and speed
//    super.update();
//
//    // Check if the player has hit the left or right wall
//    if (position.x <= size/2 || position.x >= window.width - size/2) {
//      // If so, reverse the x direction
//      direction.x = -direction.x;
//
//      // Reset the position to the previous one to avoid going past the wall
//      position = prevPosition;
//    }
//
//    // Check if the player has hit the top or bottom wall
//    if (position.y <= size/2 || position.y >= window.height - size/2) {
//      // If so, reverse the y direction
//      direction.y = -direction.y;
//
//      // Reset the position to the previous one to avoid going past the wall
//      position = prevPosition;
//    }
  }

  public void setPosition(PVector position) {
    this.position = position;
  }

}



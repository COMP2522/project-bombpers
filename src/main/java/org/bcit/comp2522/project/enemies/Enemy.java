package org.bcit.comp2522.project.enemies;

import java.awt.Color;
import org.bcit.comp2522.project.Sprite;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

/**
 * Class representing the base enemy.
 *
 * @version 3/15/2023
 */
public class Enemy extends Sprite {
  protected String name;

  public Enemy(String name, int health, int damage, PVector position, PVector direction, float size, float speed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
    this.name = name;
    this.health = health;
    this.damage = damage;
  }

  //  public Enemy_Base(PVector position,
  //                    PVector direction,
  //                    float size,
  //                    float speed,
  //                    Color color,
  //                    Window window,
  //                    int health,
  //                    int damage,
  //                    String name) {
  //    super(position,
  //          direction,
  //          size,
  //          speed,
  //          color,
  //          window,
  //          health,
  //          damage);
  //    this.name = name;
  //  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method for two Enemy Sprites colliding.
   *
   * @param one the first sprite
   * @param two the second sprite
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  /**
   * compareTo method for EnemyBase.
   */
  @Override
  public void compareTo() {
    //TODO: Implement this method
  }

  /**
   * draws the enemy.
   */
  @Override
  public void draw() {
    window.noStroke();  // disable stroke
    window.fill(color.getRGB());  // set fill color
    window.ellipse(position.x, position.y, size, size);  // draw a circle at the enemy position
  }


  /**
   * moves the enemy.
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }
}

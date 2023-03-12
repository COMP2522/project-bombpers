package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite{
  protected String name;

  public Enemy(PVector position, PVector direction,
               float size, float speed, Color color,
               Window window, int health, int damage,
               String name) {
    super(position, direction, size, speed, color, window, health, damage);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param one
   * @param two
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void compareTo() {
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void draw() {
    window.noStroke();  // disable stroke
    window.fill(color.getRGB());  // set fill color
    window.ellipse(position.x, position.y, size, size);  // draw a circle at the enemy position
  }


  /**
   *
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }
}
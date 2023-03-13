package org.bcit.comp2522.project;

import java.awt.*;

public class Projectile extends Sprite {
  protected int speed;

  protected int size;
  protected int damage;
  protected Window w;
  public Projectile(int health, int damage, int size, int xPosition, int yPosition, int speed, Window window) {
    super(health, damage, size, xPosition, yPosition);
    this.speed = speed;
    w = window;
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

//    w.noStroke();  // disable stroke
//    w.fill(color.getRGB());  // set fill color
//    w.ellipse(position.x, position.y, size, size);

  }

  /**
   *
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }

  private void disappear() {
    //TODO: Implement this method
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public float getSize() {
    return size;
  }

  @Override
  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public void setDamage(int damage) {
    this.damage = damage;
  }
}

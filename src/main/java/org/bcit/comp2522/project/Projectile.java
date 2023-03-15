package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Projectile extends Sprite {
  protected float speed;

  protected float size;
  protected int damage;
  protected Window w;
  public Projectile(PVector position, PVector direction, float size, float speed, Color color, Window window, int health, int damage) {
    super(position, direction, size, speed, color, window, health, damage);
  }

  /**
   * @param one
   * @param two
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement collision logic
  }

  /**
   *
   */
  @Override
  public void compareTo() {
    //TODO: Implement comparison logic, if needed
  }

  /**
   *
   */
  @Override
  public void draw() {
    // Draw the projectile
    window.pushStyle();
    window.fill(color.getRGB());
    window.ellipse(position.x, position.y, size, size);
    window.popStyle();
  }

  /**
   *
   */
  @Override
  public void move() {
    position.add(direction.copy().mult(speed));
    bounce();
  }

  private void disappear() {
    //TODO: Implement this method
  }

  public float getSpeed() {
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

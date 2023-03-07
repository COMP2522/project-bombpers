package org.bcit.comp2522.project;

public class Projectile extends Sprite {
  protected int speed;

  protected int size;
  protected int damage;

  public Projectile(int health, int damage, int size, int xPosition, int yPosition, int speed) {
    super(health, damage, size, xPosition, yPosition);
    this.speed = speed;
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
    //TODO: Implement this method
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
  public int getSize() {
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

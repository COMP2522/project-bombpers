package org.bcit.comp2522.project;

public abstract class Sprite implements Collidable, Movable, Drawable {
  protected int health;
  protected int damage;

  protected int size;
  protected int XPosition;
  protected int YPosition;

  public Sprite(int health, int damage, int size, int xPosition, int yPosition) {
    this.health = health;
    this.damage = damage;
    this.size = size;
    XPosition = xPosition;
    YPosition = yPosition;
  }

  protected void update() {
    move();
    draw();
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

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getXPosition() {
    return XPosition;
  }

  public void setXPosition(int XPosition) {
    this.XPosition = XPosition;
  }

  public int getYPosition() {
    return YPosition;
  }

  public void setYPosition(int YPosition) {
    this.YPosition = YPosition;
  }
}
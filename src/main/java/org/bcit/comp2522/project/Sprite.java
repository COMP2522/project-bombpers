package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public abstract class Sprite implements Collidable, Movable, Drawable {
  protected int health;
  protected int damage;
  protected PVector position;
  protected PVector direction;
  protected Color color;
  protected float size;
  protected int XPosition;
  protected int YPosition;
  protected float speed;
  protected Window window;

  public PVector getPosition() {
    return position.copy();
  }

  // For making constructors down the chain easier
  public Sprite(){}

  // Wall constructor
  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }
  /*
  Projectile constructor
   */
  public Sprite(int health, int damage, int size, int xPosition, int yPosition) {
    this.health = health;
    this.damage = damage;
    this.size = size;
    XPosition = xPosition;
    YPosition = yPosition;
  }

  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Window window,
                int health, int damage) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
    this.health = health;
    this.damage = damage;
  }

  public static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    if (distance <= (a.getSize() + b.getSize())) {
      return true;
    }
    return false;
  }

  public void bounce() {
    if (this.position.x <= 0 ||
            this.position.x >= window.width ||
            this.position.y <= 0 ||
            this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  protected void update() {
    move();
    draw();
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
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

  public float getSize() {
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
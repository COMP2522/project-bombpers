package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Projectile extends Sprite{

  private float x, y; // position of the projectile
  private float speed; // speed of the projectile
  private float angle; // direction of the projectile in degrees

  public Projectile(float x, float y, int speed, Window window) {
    super(new PVector(x, y), new PVector(0, 0), 10, speed, Color.RED, window);
    this.x = x;
    this.y = y;
    this.speed = speed;
  }

  public Projectile(PVector playerPos, PVector dir, int speed, int i, Color color, Window window) {
    super(playerPos, dir, 10, speed, color, window);
  }

  public void update (float mouseX, float mouseY){
    angle = (float) Math.atan2(mouseY - y, mouseX - x);

    float velX = (float) (speed * Math.cos(angle));
    float velY = (float) (speed * Math.sin(angle));

    x += velX;
    y += velY;
  }

  @Override
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

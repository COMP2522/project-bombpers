package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Player extends Sprite implements Comparable<Enemy>{
  private int level;
  private String name;

  public Player(PVector position, PVector direction,
                float size, float speed, Color color,
                Window window, int health, int damage,
                int level, String name) {
    super(position, direction, size, speed, color, window, health, damage);
    this.level = level;
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
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
    window.fill(color.getRGB());
    window.ellipse(position.x, position.y, size, size);
  }

  /**
   *
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }

  private void aim(){
    //TODO: Implement this method
  }

  private void shoot(){
    //TODO: Implement this method
  }

  @Override
  public int compareTo(Sprite enemy) {
    if (Float.compare(this.size, enemy.getSize()) < 0) {
      return -1;
    } else if (Float.compare(this.size, enemy.getSize()) >= 0) {
      return 1;
    }
    return 0;
  }
}

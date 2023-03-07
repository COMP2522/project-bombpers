package org.bcit.comp2522.project;

public class Enemy extends Sprite{
  protected String name;

  public Enemy(int health, int damage, int size, int xPosition, int yPosition, String name) {
    super(health, damage, size, xPosition, yPosition);
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
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }
}

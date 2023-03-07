package org.bcit.comp2522.project;

public class Player extends Sprite{
  private int level;
  private String name;

  public Player(int health, int damage, int size,  int xPosition,
                int yPosition, int level, String name) {
    super(health, damage, size, xPosition, yPosition);
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
    //TODO: Implement this method
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

}

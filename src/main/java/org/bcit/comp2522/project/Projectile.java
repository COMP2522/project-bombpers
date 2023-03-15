package org.bcit.comp2522.project;

/**
 * Projectile class - is a child of the Sprite class.
 */
public class Projectile extends Sprite {

  protected int speed;
  protected int size;
  protected int damage;
  protected Window window;

  /**
   * Constructor for the Projectile class.
   *
   * @param health    - the health of the projectile
   * @param damage    - the damage the projectile does
   * @param size      - the size of the projectile
   * @param positionX - the x position of the projectile
   * @param positionY - the y position of the projectile
   * @param speed     - the speed of the projectile
   * @param window    - the window of the projectile
   */
  public Projectile(int health,
                    int damage,
                    int size,
                    int positionX,
                    int positionY,
                    int speed,
                    Window window) {
    super(health, damage, size, positionX, positionY);
    this.speed = speed;
    this.window = window;
  }

  /**
   * Checks if the projectile hits something.
   *
   * @param one - the first sprite to collide
   * @param two - the second sprite to collide
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  /**
   * Compares the projectile to the enemy.
   */
  @Override
  public void compareTo() {
    //TODO: Implement this method
  }

  /**
   * Draws the projectile.
   */
  @Override
  public void draw() {
  //    w.noStroke();  // disable stroke
  //    w.fill(color.getRGB());  // set fill color
  //    w.ellipse(position.x, position.y, size, size);
  }

  /**
   * Moves the projectile.
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }

  /**
   * Makes the projectile disappear.
   */
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

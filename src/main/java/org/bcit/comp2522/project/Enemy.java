package org.bcit.comp2522.project;

import org.bcit.comp2522.project.Sprite;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;
import processing.core.PImage;


public abstract class Enemy extends Sprite {
  protected Sprite player;
  protected PImage enemySprite;
  private boolean dead;

  public Enemy(Window window, Sprite player, PImage sprite) {
    super(window);
    this.direction = new PVector(0, 0);
    this.player = player;
    this.enemySprite = sprite;
  }

  /**
   * Method for two Enemy Sprites colliding.
   *
   * @param one the first sprite
   * @param two the second sprite
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  /**
   * compareTo method for EnemyBase.
   */
  @Override
  public void compareTo() {
    //TODO: Implement this method
  }

  /**
   * draws the enemy.
   */
  @Override
  public void draw() {
    window.image(enemySprite, position.x, position.y, size, size);
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  /**
   * moves the enemy.
   */
  @Override
  public void move() {
    PVector directionToPlayer = PVector.sub(player.getPosition(), position);
    directionToPlayer.normalize();
    directionToPlayer.mult(speed);
    direction = directionToPlayer;
    position.add(direction);
  }
}
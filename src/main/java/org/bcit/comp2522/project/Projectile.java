package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Projectile class where we create projectiles that works with Player and Enemy
 * classes.
 *
 * @author Ozan Yurtisigi, Sukhraj Sidhu, Brett Reader, Amarjot Singha, Benny Li
 * @version 1.0
 */
public class Projectile extends Sprite {
  public static final float PROJECTILE_SIZE = 15;
  public static final float PROJECTILE_SPEED = 5.0f;
  public static final int PROJECT_BASE_DAMAGE = 1;
  private final PImage projectileImage;
  private boolean dead;

  /**
   * Constructor for our projectile.
   *
   * @param window    the window where the projectile is in
   * @param position  x and y of the projectile
   * @param direction where the projectile will go
   * @param image     sprite of the projectile
   */
  public Projectile(Window window, PVector position, PVector direction, PImage image) {
    super(window);
    this.position = position.copy();
    this.direction = direction.copy();
    this.size = PROJECTILE_SIZE;
    this.speed = PROJECTILE_SPEED;
    this.damage = PROJECT_BASE_DAMAGE;
    this.projectileImage = image;
  }

  @Override
  public void move() {
    this.position.add(PVector.mult(direction, speed));
  }

  @Override
  public void draw() {
    window.image(projectileImage, position.x, position.y, size, size);
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  @Override
  public void collide(Sprite one, Sprite two) {
    if (one instanceof Projectile projectile && two instanceof Enemy enemy) {
      final PVector projectileCenter = projectile.getCenterPosition();
      final PVector enemyCenter = enemy.getCenterPosition();
      final float minDistance = projectile.getSize() / ConstantManager.CUT_HITBOX_IN_HALF
          + enemy.getSize() / ConstantManager.CUT_HITBOX_IN_HALF;

      if (PVector.dist(projectileCenter, enemyCenter) < minDistance) {
        projectile.setDead(true);
        enemy.setHealth(enemy.getHealth() - this.damage);
        if (enemy.getHealth() <= ConstantManager.ZERO) {
          enemy.setDead(true);
        }
      }
    } else if (one instanceof Enemy && two instanceof Projectile) {
      collide(two, one);
    }
  }

}


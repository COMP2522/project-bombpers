
package org.bcit.comp2522.project;

import processing.core.PVector;
import processing.core.PImage;

public class Projectile extends Sprite {
    public static final float PROJECTILE_SIZE = 15;
    public static final float PROJECTILE_SPEED = 5.0f;
    public static final int PROJECT_BASE_DAMAGE = 1;
    private final PImage projectileImage;
    private boolean dead;

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
        final int CUT_BOX_IN_HALF = 2;
        if (one instanceof Projectile projectile && two instanceof Enemy enemy) {
            PVector projectileCenter = projectile.getCenterPosition();
            PVector enemyCenter = enemy.getCenterPosition();
            float minDistance = (projectile.getSize() / CUT_BOX_IN_HALF) + (enemy.getSize() / CUT_BOX_IN_HALF);

            if (PVector.dist(projectileCenter, enemyCenter) < minDistance) {
                projectile.setDead(true);
                enemy.setHealth(enemy.getHealth() - this.damage);
                if (enemy.getHealth() <= 0) {
                    enemy.setDead(true);
                }
            }
        } else if (one instanceof Enemy && two instanceof Projectile) {
            collide(two, one);
        }
    }

  @Override
  public int getDamage() {
    return damage;
  }


  public void setDamage(int damage) {
    this.damage = damage;
  }
}


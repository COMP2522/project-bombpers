package org.bcit.comp2522.project;

import processing.core.PVector;
import processing.core.PImage;

public class Projectile extends Sprite {
    public static final float PROJECTILE_SIZE = 10;
    public static final float PROJECTILE_SPEED = 5.0f;
    private boolean dead;
    private final PImage projectileImage;

    public Projectile(Window window, PVector position, PVector direction, PImage image) {
        super(window);
        this.position = position.copy();
        this.direction = direction.copy();
        this.size = PROJECTILE_SIZE;
        this.speed = PROJECTILE_SPEED;
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

            if (PVector.dist(projectile.getPosition(), enemy.getPosition()) < (projectile.getSize() / 2) + (enemy.getSize() / 2)) {
                projectile.setDead(true);
                enemy.setDead(true);
            }
        } else if (one instanceof Enemy && two instanceof Projectile) {
            collide(two, one);
        }
    }

    @Override
    public void compareTo() {
        // TODO: Implement this method
    }

  @Override
  public int getDamage() {
    return damage;
  }


  public void setDamage(int damage) {
    this.damage = damage;
  }
}



package org.bcit.comp2522.project;

import processing.core.PVector;

public class Projectile extends Sprite {
    public static final float PROJECTILE_SIZE = 10;
    public static final float PROJECTILE_SPEED = 5.0f;
    private boolean dead;

    public Projectile(Window window, PVector position, PVector direction) {
        super(window);
        this.position = position.copy();
        this.direction = direction.copy();
        this.size = PROJECTILE_SIZE;
        this.speed = PROJECTILE_SPEED;
    }

    @Override
    public void move() {
        this.position.add(PVector.mult(direction, speed));
    }

    @Override
    public void draw() {
        window.pushStyle();
        window.fill(255, 0, 0);
        window.ellipse(position.x, position.y, size, size);
        window.popStyle();
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public void collide(Sprite one, Sprite two) {
        if (one instanceof Projectile && two instanceof Enemy) {
            Projectile projectile = (Projectile) one;
            Enemy enemy = (Enemy) two;

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
}


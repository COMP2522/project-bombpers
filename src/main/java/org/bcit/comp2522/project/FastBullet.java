package org.bcit.comp2522.project;

/**
 * FastBullet class - is a child of the Projectile class.
 */
public class FastBullet extends Projectile {
  public FastBullet(int health,
                    int damage,
                    int size,
                    int positionX,
                    int positionY,
                    int speed,
                    Window window1) {
    super(health, damage, size, positionX, positionY, speed, window1);
  }
}

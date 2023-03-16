package org.bcit.comp2522.project;

/**
 * RapidBullet class - is a child of the Projectile class.
 */
public class RapidBullet extends Projectile {
  public RapidBullet(int health,
                     int damage,
                     int size,
                     int positionX,
                     int positionY,
                     int speed,
                     Window window1) {
    super(health, damage, size, positionX, positionY, speed, window1);
  }
}

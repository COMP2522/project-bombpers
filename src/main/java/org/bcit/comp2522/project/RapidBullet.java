package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class RapidBullet extends Projectile {
  public RapidBullet(PVector position, PVector direction, float size, float speed, Color color, Window window, int health, int damage) {
    super(position, direction, size, speed, color, window, health, damage);
  }
}

package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_StrongSlow extends Enemy_Standard {
  protected static final int BASEHEALTH_ENEM_STRONGSLOW = 6;


  public Enemy_StrongSlow(PVector position, PVector direction,
                          float size, float speed, Color color,
                          Window window, int health, int damage,
                          String name) {
    super(position, direction, size, speed, color, window, health, damage, name);
    this.health = BASEHEALTH_ENEM_STRONGSLOW + health;
  }
}

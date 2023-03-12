package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_WeakFast extends Enemy_Standard {
  protected static final int BASEHEALTH_ENEM_WEAKFAST = 2;


  public Enemy_WeakFast(PVector position, PVector direction,
                        float size, float speed, Color color,
                        Window window, int health, int damage,
                        String name) {
    super(position, direction, size, speed, color, window, health, damage, name);
    this.health = BASEHEALTH_ENEM_WEAKFAST + health;
  }
}

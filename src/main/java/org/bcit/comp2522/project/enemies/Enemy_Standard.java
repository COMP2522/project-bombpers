package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Standard extends Enemy_Base {
  private static final int BASEHEALTH_ENEM = 4;

  public Enemy_Standard(PVector position, PVector direction,
                        float size, float speed, Color color,
                        Window window, int health, int damage,
                        String name) {
    super(position, direction, size, speed, color, window, health, damage, name);
    this.name = name;
    this.health = BASEHEALTH_ENEM + health;
  }
}

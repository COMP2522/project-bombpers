package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Slow extends Enemy_Standard {
  protected static final String NAME_ENEM_SLOW = "Big Hanji";
  protected static final int HP_ENEM_SLOW = 6;
  protected static final int DMG_ENEM_SLOW = 4;
  protected static final float SIZE_ENEM_SLOW = 20f;
  protected static final float SPEED_ENEM_SLOW = 0.9f;
  protected static final Color COLOR_ENEM_SLOW = new Color(230, 100, 0);

  public Enemy_Slow(){
    this.name = NAME_ENEM_SLOW;
    this.health = HP_ENEM_SLOW;
    this.damage = DMG_ENEM_SLOW;
    this.size = SIZE_ENEM_SLOW;
    this.speed = SPEED_ENEM_SLOW;
  }

  public Enemy_Slow(PVector position, PVector direction, Window window) {
    this.name = NAME_ENEM_SLOW;
    this.health = HP_ENEM_SLOW;
    this.damage = DMG_ENEM_SLOW;
    this.size = SIZE_ENEM_SLOW;
    this.speed = SPEED_ENEM_SLOW;
    this.color = COLOR_ENEM_SLOW;
    this.position = position;
    this.direction = direction;
    this.window = window;
  }

  public Enemy_Slow(String name,
                    int health,
                    int damage,
                    float size,
                    float speed,
                    Color color,
                    PVector position,
                    PVector direction,
                    Window window) {
    this.name = name;
    this.health = health;
    this.damage = damage;
    this.size = size;
    this.speed = speed;
    this.color = color;
    this.position = position;
    this.direction = direction;
    this.window = window;
  }
}

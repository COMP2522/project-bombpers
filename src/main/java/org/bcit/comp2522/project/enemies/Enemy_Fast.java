package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Fast extends Enemy_Standard {
  protected static final String BASE_NAME_ENEM_FAST = "Sanic the Hanji";
  protected static final int BASE_HP_ENEM_FAST = 2;
  protected static final int BASE_DMG_ENEM_FAST = 1;
  protected static final float BASE_SIZE_ENEM_FAST = 7.5f;
  protected static final float BASE_SPEED_ENEM_FAST = 1.6f;
  protected static final Color BASE_COLOR_ENEM_FAST = new Color(0, 200, 255);

  public Enemy_Fast(){
    this.name = BASE_NAME_ENEM_FAST;
    this.health = BASE_HP_ENEM_FAST;
    this.damage = BASE_DMG_ENEM_FAST;
    this.size = BASE_SIZE_ENEM_FAST;
    this.speed = BASE_SPEED_ENEM_FAST;
    this.color = BASE_COLOR_ENEM_FAST;
  }

  public Enemy_Fast(PVector position, PVector direction, Window window) {
    this.name = BASE_NAME_ENEM_FAST;
    this.health = BASE_HP_ENEM_FAST;
    this.damage = BASE_DMG_ENEM_FAST;
    this.size = BASE_SIZE_ENEM_FAST;
    this.speed = BASE_SPEED_ENEM_FAST;
    this.color = BASE_COLOR_ENEM_FAST;
    this.position = position;
    this.direction = direction;
    this.window = window;
  }

  public Enemy_Fast(
      String name,
      int health,
      int damage,
      float size,
      float speed,
      Color color,
      PVector position,
      PVector direction,
      Window window
  ) {
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

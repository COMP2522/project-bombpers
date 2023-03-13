package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Standard extends Enemy_Base {

  // TODO: Default spawn parameters once things are set in place
  public Enemy_Standard(){}

  public Enemy_Standard(PVector position, PVector direction, Window window) {
    this.position = position;
    this.direction = direction;
    this.window = window;
  }

    public Enemy_Standard(String name,
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

package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class WeakFastEnemy extends Enemy {
//  public WeakFastEnemy(int health, int damage, int size, int xPosition, int yPosition, String name) {
//    super(health, damage, size, xPosition, yPosition, name);
//  }
public WeakFastEnemy(PVector position, PVector direction,
             float size, float speed, Color color,
             Window window, int health, int damage,
             String name) {
  super(position, direction, size, speed, color, window, health, damage, name);
}
}

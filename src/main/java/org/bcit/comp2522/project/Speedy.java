package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

public class Speedy extends Player{
  public Speedy(PVector position, PVector direction, float size, float speed, Color color, Window window, int health, int damage, int level, String name, PImage characterSprite) {
    super(position, direction, size, speed, color, window, health, damage, level, name, characterSprite);
  }
}

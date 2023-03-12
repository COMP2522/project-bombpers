package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Standard extends Enemy_Base {
  private static final int BASE_HEALTH_ENEM = 4;
  private static final int BASE_DMG_ENEM = 4;

  // TODO: Default spawn parameters once things are set in place
  public Enemy_Standard(){
    this.name = "ModusPonens"; // Placeholder name for now
    this.health = BASE_HEALTH_ENEM;
    this.damage = BASE_DMG_ENEM;
    // TODO: spawn somewhere near player
    //this.size = size;
    //this.speed = speed;
    //this.window = window;
    //this.color = color;
  }

  public Enemy_Standard(PVector position, PVector direction) {
    this.health = BASE_HEALTH_ENEM;
    this.damage = BASE_DMG_ENEM;
    this.position = position;
    this.direction = direction;
  }
}

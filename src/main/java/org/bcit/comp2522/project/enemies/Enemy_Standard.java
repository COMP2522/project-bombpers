package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Standard extends Enemy_Base {
  protected static final String BASE_NAME_ENEM = "Modus Ponens";
  protected static final int BASE_HP_ENEM = 4;
  protected static final int BASE_DMG_ENEM = 2;
  protected static final float BASE_SIZE_ENEM = 1.0f;
  protected static final float BASE_SPEED_ENEM = 1.2f;

  // TODO: Default spawn parameters once things are set in place
  public Enemy_Standard(){
    this.name = BASE_NAME_ENEM; // Placeholder name for now
    this.health = BASE_HP_ENEM;
    this.damage = BASE_DMG_ENEM;
    this.size = BASE_SIZE_ENEM;
    this.speed = BASE_SPEED_ENEM;
    // TODO: spawn somewhere near player
    //this.position = position;
    //this.direction = direction;
    //this.window = window;
    //this.color = color;
  }

  public Enemy_Standard(PVector position, PVector direction) {
    this.health = BASE_HP_ENEM;
    this.damage = BASE_DMG_ENEM;
    this.position = position;
    this.direction = direction;
    //this.size = size;
    //this.speed = speed;
    //this.window = window;
    //this.color = color;
  }
}

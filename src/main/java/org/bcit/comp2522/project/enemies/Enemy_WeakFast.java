package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_WeakFast extends Enemy_Standard {
  protected static final int BASE_HP_ENEM_WEAKFAST = 2;
  protected static final int BASE_DMG_ENEM_WEAKFAST = 1;
  protected static final float BASE_SIZE_ENEM_WEAKFAST = 0.8f;
  protected static final float BASE_SPEED_ENEM_WEAKFAST = 1.5f;

  public Enemy_WeakFast(){
    this.health = BASE_HP_ENEM_WEAKFAST;
    this.damage = BASE_DMG_ENEM_WEAKFAST;
    this.size = BASE_SIZE_ENEM_WEAKFAST;
    this.speed = BASE_SPEED_ENEM_WEAKFAST;
  }
}

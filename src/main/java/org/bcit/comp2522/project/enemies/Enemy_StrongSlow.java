package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_StrongSlow extends Enemy_Standard {
  protected static final int BASE_HP_ENEM_STRONGSLOW = 6;
  protected static final int BASE_DMG_ENEM_STRONGSLOW = 4;
  protected static final float BASE_SIZE_ENEM_STRONGSLOW = 0.8f;
  protected static final float BASE_SPEED_ENEM_STRONGSLOW = 1.5f;

  public Enemy_StrongSlow(){
    this.health = BASE_HP_ENEM_STRONGSLOW;
    this.damage = BASE_DMG_ENEM_STRONGSLOW;
    this.size = BASE_SIZE_ENEM_STRONGSLOW;
    this.speed = BASE_SPEED_ENEM_STRONGSLOW;
  }
}

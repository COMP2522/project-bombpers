package org.bcit.comp2522.project.enemies;

public class Enemy_Fast extends Enemy_Standard {
  protected static final String BASE_NAME_ENEM_FAST = "Sanic the Hanji";
  protected static final int BASE_HP_ENEM_FAST = 2;
  protected static final int BASE_DMG_ENEM_FAST = 1;
  protected static final float BASE_SIZE_ENEM_FAST = 0.8f;
  protected static final float BASE_SPEED_ENEM_FAST = 1.6f;

  public Enemy_Fast(){
    this.health = BASE_HP_ENEM_FAST;
    this.damage = BASE_DMG_ENEM_FAST;
    this.size = BASE_SIZE_ENEM_FAST;
    this.speed = BASE_SPEED_ENEM_FAST;
  }
}

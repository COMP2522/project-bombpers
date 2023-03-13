package org.bcit.comp2522.project.enemies;

public class Enemy_Slow extends Enemy_Standard {
  protected static final String BASE_NAME_ENEM_SLOW = "Big Hanji";
  protected static final int BASE_HP_ENEM_SLOW = 6;
  protected static final int BASE_DMG_ENEM_SLOW = 4;
  protected static final float BASE_SIZE_ENEM_SLOW = 1.5f;
  protected static final float BASE_SPEED_ENEM_SLOW = 0.9f;

  public Enemy_Slow(){
    this.name = BASE_NAME_ENEM_SLOW;
    this.health = BASE_HP_ENEM_SLOW;
    this.damage = BASE_DMG_ENEM_SLOW;
    this.size = BASE_SIZE_ENEM_SLOW;
    this.speed = BASE_SPEED_ENEM_SLOW;
  }
}

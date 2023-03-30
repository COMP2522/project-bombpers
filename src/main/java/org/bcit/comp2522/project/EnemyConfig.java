package org.bcit.comp2522.project;

public class EnemyConfig {
  // Number of Enemy Types
  public static final int ENEM_TYPES = 3;

  // Standard Enemy
  public static final int ENEM_STANDARD_TYPE = 1;
  public static final int ENEMY_STANDARD_HEALTH = 1;
  public static final int ENEMY_STANDARD_DAMAGE = 1;
  public static final float ENEMY_STANDARD_SIZE = 64f;
  public static final float ENEMY_STANDARD_SPEED = 0.8f;
  public static final String ENEMY_STANDARD_SPRITE = "../img/enemy_standard_00.png";

  // Fast Enemy
  public static final int ENEM_FAST_TYPE = 2;
  public static final int ENEMY_FAST_HEALTH = 1;
  public static final int ENEMY_FAST_DAMAGE = 1;
  public static final float ENEMY_FAST_SIZE = 64f;
  public static final float ENEMY_FAST_SPEED = 1.0f;
  public static final String ENEMY_FAST_SPRITE = "../img/enemy_fast_00.png";

  // Slow Enemy
  public static final int ENEM_SLOW_TYPE = 3;
  public static final int ENEMY_SLOW_HEALTH = 6;
  public static final int ENEMY_SLOW_DAMAGE = 4;
  public static final float ENEMY_SLOW_SIZE = 64f;
  public static final float ENEMY_SLOW_SPEED = 0.6f;
  public static final String ENEMY_SLOW_SPRITE = "../img/enemy_slow_00.png";
}

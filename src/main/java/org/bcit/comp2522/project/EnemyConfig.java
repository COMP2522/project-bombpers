package org.bcit.comp2522.project;

public class EnemyConfig {
  // Number of Enemy Types
  public static final int ENEMY_TYPES = 3;

  // Standard Enemy
  public static final int ENEMY_STANDARD_TYPE = 1;
  public static final int ENEMY_STANDARD_HEALTH = 2;
  public static final int ENEMY_STANDARD_DAMAGE = 1;
  public static final float ENEMY_STANDARD_SIZE = 30f;
  public static final float ENEMY_STANDARD_SPEED = 0.7f;
  public static final String ENEMY_STANDARD_SPRITE = "../img/es_idle_0v2.png";

  // Fast Enemy
  public static final int ENEMY_FAST_TYPE = 2;
  public static final int ENEMY_FAST_HEALTH = 1;
  public static final int ENEMY_FAST_DAMAGE = 1;
  public static final float ENEMY_FAST_SIZE = 30f;
  public static final float ENEMY_FAST_SPEED = 1.0f;
  public static final String ENEMY_FAST_SPRITE = "../img/fly_0v2.png";

  // Slow Enemy
  public static final int ENEMY_SLOW_TYPE = 3;
  public static final int ENEMY_SLOW_HEALTH = 4;
  public static final int ENEMY_SLOW_DAMAGE = 2;
  public static final float ENEMY_SLOW_SIZE = 30f;
  public static final float ENEMY_SLOW_SPEED = 0.5f;
  public static final String ENEMY_SLOW_SPRITE = "../img/eslow_idle_0v2.png";
}

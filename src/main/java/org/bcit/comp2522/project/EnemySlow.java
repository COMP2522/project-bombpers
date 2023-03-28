package org.bcit.comp2522.project;

import processing.core.PVector;

import java.util.Random;


/**
 * Slow enemy class - is a child of the EnemyStandard class.
 */
public class EnemySlow extends Enemy {
  public static final int ENEMY_HEALTH = 6;
  public static final int ENEMY_DAMAGE = 4;
  public static final float ENEMY_SIZE = 64f;
  public static final float ENEMY_SPEED = 0.6f;
  public static final String ENEMY_SPRITE = "../img/eslow_idle_0.png";

  public EnemySlow(Window window, Sprite player) {
    super(window, player, window.enemySlowSprite);
    Random random = new Random();
    int randomY = random.nextInt(window.height);
    this.position = new PVector(window.width, randomY);
    this.health = ENEMY_HEALTH;
    this.damage = ENEMY_DAMAGE;
    this.size = ENEMY_SIZE;
    this.speed = ENEMY_SPEED;
//    this.enemySprite = window.loadImage(ENEMY_SPRITE);
  }
}
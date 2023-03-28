package org.bcit.comp2522.project;


import processing.core.PVector;

import java.util.Random;


/**
 * Standard enemy class - is a child of the Enemy class.
 */
public class EnemyStandard extends Enemy {

  public static final int ENEMY_HEALTH = 1;
  public static final int ENEMY_DAMAGE = 1;
  public static final float ENEMY_SIZE = 64f;
  public static final float ENEMY_SPEED = 0.8f;
  public static final String ENEMY_SPRITE = "../img/es_idle_0.png";

  public EnemyStandard(Window window, Sprite player) {
    super(window, player, window.enemyStandardSprite);
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
package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Sprite;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.util.Random;

/**
 * Fast enemy class - is a child of the EnemyStandard class.
 */
public class EnemyFast extends Enemy {
  public static final int ENEMY_HEALTH = 1;
  public static final int ENEMY_DAMAGE = 1;
  public static final float ENEMY_SIZE = 64f;
  public static final float ENEMY_SPEED = 1.0f;
  public static final String ENEMY_SPRITE = "../img/fly_0.png";

  public EnemyFast(Window window, Sprite player) {
    super(window, player, window.enemyFastSprite);
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
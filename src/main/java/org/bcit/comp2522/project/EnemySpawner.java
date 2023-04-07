package org.bcit.comp2522.project;

import java.util.Random;
import processing.core.PImage;
import processing.core.PVector;

public class EnemySpawner {
  private int spawnModifier = ConstantManager.ZERO;
  /**
   * Maximum number of enemies.
   */
  private int enemy_max = ConstantManager.BASE_WAVE_COUNT + spawnModifier;
  private int curr_enemy_count;
  private final Random randomNumber = new Random();
  private final CollectionManager collectionManager;
  private final Window window;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  public void spawnerActivate() {
    if (spawnAvailable()) {
      int diceRoll = randomNumber.nextInt(EnemyConfig.ENEMY_TYPES) + 1;
      switch (diceRoll) {
        case EnemyConfig.ENEMY_STANDARD_TYPE -> spawnEnemy(
            window.enemyStandardSprite,
            EnemyConfig.ENEMY_STANDARD_TYPE,
            EnemyConfig.ENEMY_STANDARD_HEALTH,
            EnemyConfig.ENEMY_STANDARD_DAMAGE,
            EnemyConfig.ENEMY_STANDARD_SIZE,
            EnemyConfig.ENEMY_STANDARD_SPEED
        );
        case EnemyConfig.ENEMY_FAST_TYPE -> spawnEnemy(
            window.enemyFastSprite,
            EnemyConfig.ENEMY_FAST_TYPE,
            EnemyConfig.ENEMY_FAST_HEALTH,
            EnemyConfig.ENEMY_FAST_DAMAGE,
            EnemyConfig.ENEMY_FAST_SIZE,
            EnemyConfig.ENEMY_FAST_SPEED
        );
        case EnemyConfig.ENEMY_SLOW_TYPE -> spawnEnemy(
            window.enemySlowSprite,
            EnemyConfig.ENEMY_SLOW_TYPE,
            EnemyConfig.ENEMY_SLOW_HEALTH,
            EnemyConfig.ENEMY_SLOW_DAMAGE,
            EnemyConfig.ENEMY_SLOW_SIZE,
            EnemyConfig.ENEMY_SLOW_SPEED
        );
        default -> System.out.println("Invalid spawn type");
      }
    }
  }

  public void spawnEnemy(
      PImage sprite,
      int enemyType,
      int health,
      int damage,
      float size,
      float speed
  ) {
    Enemy newEnemy = new Enemy(
        window,
        sprite,
        enemyType,
        health,
        damage,
        size,
        speed,
        randomizePosition()
    );
    addToCollection(newEnemy);
    increaseEnemyCount();
  }

  public void addToCollection(Enemy newEnemy) {
    collectionManager.getEnemies().add(newEnemy);
    collectionManager.getSprites().add(newEnemy);
  }

  public boolean spawnAvailable() {
    return this.curr_enemy_count <= this.enemy_max;
  }

  public PVector randomizePosition() {
    int randomY = randomNumber.nextInt(Window.WINDOW_HEIGHT);
    return new PVector(Window.WINDOW_WIDTH, randomY);
  }

  public void increaseEnemyCount() {
    this.curr_enemy_count++;
  }

  public void decreaseEnemyCount() {
    this.curr_enemy_count--;
  }

  public void updateSpawnModifier(Score score) {
    this.spawnModifier = score.getCurrentScore() / ConstantManager.TIER_THRESHOLD;
    this.enemy_max = ConstantManager.BASE_WAVE_COUNT + spawnModifier;
  }

  public void countReset() {
    this.curr_enemy_count = ConstantManager.NO_ENEMIES;
  }

  public int getCount() {
    return this.curr_enemy_count;
  }

  public int getSpawnModifier() {
    return spawnModifier;
  }
}

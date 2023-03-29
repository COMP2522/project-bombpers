package org.bcit.comp2522.project;

import processing.core.PVector;
import java.util.Random;

public class EnemySpawner {

  /**
   * Number of enemy types.
   */
  private static final int ENEM_TYPES = 3;

  /**
   * int IDs for different enemy types
   */
  private static final int ENEM_STANDARD = 1;
  private static final int ENEM_FAST = 2;
  private static final int ENEM_SLOW = 3;
  /**
   * Maximum number of enemies.
   */
  private static final int ENEM_MAX = 10;
  private static  int curr_enem_count;
  private Random rngsus = new Random();
  private CollectionManager collectionManager;
  private Window window;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  public void spawnEnemy() {

    int diceRoll = rngsus.nextInt(ENEM_TYPES) + 1;
    switch (diceRoll) {
      case ENEM_STANDARD -> spawnStandardEnemy();
      case ENEM_FAST -> spawnFastEnemy();
      case ENEM_SLOW -> spawnSlowEnemy();
      default -> System.out.println("Invalid spawn type");
    }
  }

  public void spawnStandardEnemy() {
    if (curr_enem_count < ENEM_MAX) {
      int randomY = rngsus.nextInt(window.height);
      PVector randomPos = new PVector(window.width, randomY);
      Enemy newEnemy = new Enemy(
          window,
          collectionManager.getPlayer(),
          window.enemyStandardSprite,
          ENEM_STANDARD,
          EnemyConfig.ENEMY_STANDARD_HEALTH,
          EnemyConfig.ENEMY_STANDARD_DAMAGE,
          EnemyConfig.ENEMY_STANDARD_SIZE,
          EnemyConfig.ENEMY_STANDARD_SPEED,
          randomPos
      );
      curr_enem_count++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

  private void spawnFastEnemy() {
    if (curr_enem_count < ENEM_MAX) {
      int randomY = rngsus.nextInt(window.height);
      PVector randomPos = new PVector(window.width, randomY);
      Enemy newEnemy = new Enemy(
          window,
          collectionManager.getPlayer(),
          window.enemyFastSprite,
          ENEM_FAST,
          EnemyConfig.ENEMY_FAST_HEALTH,
          EnemyConfig.ENEMY_FAST_DAMAGE,
          EnemyConfig.ENEMY_FAST_SIZE,
          EnemyConfig.ENEMY_FAST_SPEED,
          randomPos
      );
      curr_enem_count++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

  public void spawnSlowEnemy() {
    if (curr_enem_count < ENEM_MAX) {
      int randomY = rngsus.nextInt(window.height);
      PVector randomPos = new PVector(window.width, randomY);
      Enemy newEnemy = new Enemy(
          window,
          collectionManager.getPlayer(),
          window.enemySlowSprite,
          ENEM_SLOW,
          EnemyConfig.ENEMY_SLOW_HEALTH,
          EnemyConfig.ENEMY_SLOW_DAMAGE,
          EnemyConfig.ENEMY_SLOW_SIZE,
          EnemyConfig.ENEMY_SLOW_SPEED,
          randomPos
      );
      curr_enem_count++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

  static public void decreaseEnemCount() {
    curr_enem_count--;
  }
}

package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.EnemyConfig.*;

import processing.core.PVector;

import java.util.Random;

public class EnemySpawner {
  private static final int BASE_WAVE_COUNT = 10;
  private static final int TIER_THRESHOLD = 50;
  private static final int NO_ENEMIES = 0;
  private int spawnModifier = 0;
  /**
   * Maximum number of enemies.
   */
  private int enem_max = BASE_WAVE_COUNT + spawnModifier;
  private int curr_enem_count;

  private Random randomNumber = new Random();
  private CollectionManager collectionManager;
  private Window window;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  public void spawnEnemy() {
    if (spawnAvailable()) {
      int diceRoll = randomNumber.nextInt(ENEM_TYPES) + 1;
      switch (diceRoll) {
        case ENEM_STANDARD_TYPE -> spawnStandardEnemy();
        case ENEM_FAST_TYPE -> spawnFastEnemy();
        case ENEM_SLOW_TYPE -> spawnSlowEnemy();
        default -> System.out.println("Invalid spawn type");
      }
    }
  }

  public void spawnStandardEnemy() {
    int randomY = randomNumber.nextInt(window.height);
    PVector randomPos = new PVector(window.width, randomY);
    Enemy newEnemy = new Enemy(
        window,
        collectionManager.getPlayer(),
        window.enemyStandardSprite,
        ENEM_STANDARD_TYPE,
        ENEMY_STANDARD_HEALTH,
        ENEMY_STANDARD_DAMAGE,
        ENEMY_STANDARD_SIZE,
        ENEMY_STANDARD_SPEED,
        randomPos
    );
    this.curr_enem_count++;
    collectionManager.getEnemies().add(newEnemy);
    collectionManager.getSprites().add(newEnemy);

  }


  private void spawnFastEnemy() {
    int randomY = randomNumber.nextInt(window.height);
    PVector randomPos = new PVector(window.width, randomY);
    Enemy newEnemy = new Enemy(
        window,
        collectionManager.getPlayer(),
        window.enemyFastSprite,
        ENEM_FAST_TYPE,
        ENEMY_FAST_HEALTH,
        ENEMY_FAST_DAMAGE,
        ENEMY_FAST_SIZE,
        ENEMY_FAST_SPEED,
        randomPos
    );
    this.curr_enem_count++;
    collectionManager.getEnemies().add(newEnemy);
    collectionManager.getSprites().add(newEnemy);

  }

  public void spawnSlowEnemy() {
    int randomY = randomNumber.nextInt(window.height);
    PVector randomPos = new PVector(window.width, randomY);
    Enemy newEnemy = new Enemy(
        window,
        collectionManager.getPlayer(),
        window.enemySlowSprite,
        ENEM_SLOW_TYPE,
        ENEMY_SLOW_HEALTH,
        ENEMY_SLOW_DAMAGE,
        ENEMY_SLOW_SIZE,
        ENEMY_SLOW_SPEED,
        randomPos
    );
    this.curr_enem_count++;
    collectionManager.getEnemies().add(newEnemy);
    collectionManager.getSprites().add(newEnemy);

  }

  public boolean spawnAvailable() {
    if (this.curr_enem_count > this.enem_max) {
      return false;
    }
    return true;
  }

  public void decreaseEnemCount() {
    this.curr_enem_count--;
  }

  public void updateSpawnModifier(Score score) {
    this.spawnModifier = score.getCurrentScore() / TIER_THRESHOLD;
    this.enem_max = BASE_WAVE_COUNT + spawnModifier;
  }

  public void countReset() {
    this.curr_enem_count = NO_ENEMIES;
  }

  public int getCount() {
    return this.curr_enem_count;
  }

  public int getSpawnModifier() {
    return spawnModifier;
  }
}

package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.EnemyConfig.*;
import processing.core.PVector;
import java.util.Random;

public class EnemySpawner {
  private static final int baseWaveCount = 10;
  private static final int tierThreshold = 50;
  private int spawnModifier = 0;
  /**
   * Maximum number of enemies.
   */
  private int enem_max = 10 + spawnModifier;
  private int curr_enem_count;

  private Random randomGenerator = new Random();
  private CollectionManager collectionManager;
  private Window window;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  public void spawnEnemy() {

    int diceRoll = randomGenerator.nextInt(ENEM_TYPES) + 1;
    switch (diceRoll) {
      case ENEM_STANDARD_TYPE:
        spawnStandardEnemy();
        break;
      case ENEM_FAST_TYPE:
        spawnFastEnemy();
        break;
      case ENEM_SLOW_TYPE:
        spawnSlowEnemy();
        break;
      default:
        System.out.println("Invalid spawn type");
        break;
    }
  }

  public void spawnStandardEnemy() {
    if (this.curr_enem_count < this.enem_max) {
      int randomY = randomGenerator.nextInt(window.height);
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
  }

  private void spawnFastEnemy() {
    if (this.curr_enem_count < this.enem_max) {
      int randomY = randomGenerator.nextInt(window.height);
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
  }

  public void spawnSlowEnemy() {
    if (this.curr_enem_count < this.enem_max) {
      int randomY = randomGenerator.nextInt(window.height);
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
  }

  public void decreaseEnemCount() {
    this.curr_enem_count--;
  }

  public void updateSpawnModifier(KillCounter kc) {
    this.spawnModifier = kc.getKills() / tierThreshold;
    this.enem_max = baseWaveCount + spawnModifier;
  }
}

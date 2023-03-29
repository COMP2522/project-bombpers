package org.bcit.comp2522.project;

import processing.core.PVector;
import java.util.Random;
import static org.bcit.comp2522.project.EnemyConfig.*;

public class EnemySpawner {
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
      case ENEM_STANDARD:
        spawnStandardEnemy();
        break;
      case ENEM_FAST:
        spawnFastEnemy();
        break;
      case ENEM_SLOW:
        spawnSlowEnemy();
        break;
      default:
        System.out.println("Invalid spawn type");
        break;
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
          ENEMY_STANDARD_HEALTH,
          ENEMY_STANDARD_DAMAGE,
          ENEMY_STANDARD_SIZE,
          ENEMY_STANDARD_SPEED,
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
          ENEMY_FAST_HEALTH,
          ENEMY_FAST_DAMAGE,
          ENEMY_FAST_SIZE,
          ENEMY_FAST_SPEED,
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
          ENEMY_SLOW_HEALTH,
          ENEMY_SLOW_DAMAGE,
          ENEMY_SLOW_SIZE,
          ENEMY_SLOW_SPEED,
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

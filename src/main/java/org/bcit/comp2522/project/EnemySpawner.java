package org.bcit.comp2522.project;

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
      Enemy newEnemy = new EnemyStandard(window, collectionManager.getPlayer());
      curr_enem_count++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

  private void spawnFastEnemy() {
    if (curr_enem_count < ENEM_MAX) {
      Enemy newEnemy = new EnemyFast(window, collectionManager.getPlayer());
      curr_enem_count++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

  public void spawnSlowEnemy() {
    if (curr_enem_count < ENEM_MAX) {
      Enemy newEnemy = new EnemySlow(window, collectionManager.getPlayer());
      curr_enem_count++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

  static public void decreaseEnemCount() {
    curr_enem_count--;
  }
}

package org.bcit.comp2522.project;

import java.util.Random;

public class EnemySpawner {

  /**
   * Number of enemy types.
   */
  private static final int ENEM_TYPES = 3;
  /**
   * Maximum number of enemies.
   */
  private static final int ENEM_MAX = 10;
  /**
   * Maximum number of standard type enemies.
   */
  private static final int ENEM_STANDARD_MAX = 5;
  /**
   * Maximum number of fast type enemies.
   */
  private static final int ENEM_FAST_MAX = 10;
  /**
   * Maximum number of slow type enemies.
   */
  private static final int ENEM_SLOW_MAX = 25;
  /**
   * Sets the different types of enemies to start off at 0.
   */

  private Random rngsus = new Random();
  private CollectionManager collectionManager;
  private static  int curr_enem_count;
  private static int curr_enem_standard = 0;
  private static int curr_enem_fast = 0;
  private static int curr_enem_slow = 0;

  private Window window;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  public void spawnEnemy() {
    int diceRoll = rngsus.nextInt(ENEM_TYPES) + 1;
    switch (diceRoll) {
      case 1:
        spawnStandardEnemy();
        break;
      case 2:
        spawnFastEnemy();
        break;
      case 3:
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

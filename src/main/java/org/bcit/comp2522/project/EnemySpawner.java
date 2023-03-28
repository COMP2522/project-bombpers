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
  private static final int ENEM_SLOW_MAX = 5;
  /**
   * Sets the different types of enemies to start off at 0.
   */

  private static final int ENEM_STANDARD = 0;
  private static final int ENEM_FAST = 1;
  private static final int ENEM_SLOW = 2;

  private Random rngsus = new Random();
  private CollectionManager collectionManager;
  private static int curr_enem_standard = 0;
  private static int curr_enem_fast = 0;
  private static int curr_enem_slow = 0;

  private Window window;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window; // set the window object
  }

  public void spawnEnemy(int spawnType) {
    switch (spawnType) {
      case ENEM_STANDARD:
        spawnStandardEnemy();
        break;
//      case ENEM_FAST:
//        spawnFastEnemy();
//        break;
//      case ENEM_SLOW:
//        spawnSlowEnemy();
//        break;
      default:
        System.out.println("Invalid spawn type");
        break;
    }
  }

  public void spawnStandardEnemy() {
    if (curr_enem_standard < ENEM_STANDARD_MAX) {
      Enemy newEnemy = new EnemyStandard(window, collectionManager.getPlayer()); // use the window object
      curr_enem_standard++;
      collectionManager.getEnemies().add(newEnemy);
      collectionManager.getSprites().add(newEnemy);
    }
  }

//  private void spawnFastEnemy() {
//    if (curr_enem_fast < ENEM_FAST_MAX) {
//      Enemy newEnemy = new EnemyFast(this, collectionManager.getPlayer());
//      curr_enem_fast++;
//      collectionManager.getEnemies().add(newEnemy);
//      collectionManager.getSprites().add(newEnemy);
//    }
//  }
//
//  public void spawnSlowEnemy() {
//    if (curr_enem_slow < ENEM_SLOW_MAX) {
//      Enemy newEnemy = new EnemySlow(this, collectionManager.getPlayer());
//      curr_enem_slow++;
//      collectionManager.getEnemies().add(newEnemy);
//      collectionManager.getSprites().add(newEnemy);
//    }
//  }

  public int getRandomSpawnType() {
    return rngsus.nextInt(ENEM_TYPES + 1);
  }
}


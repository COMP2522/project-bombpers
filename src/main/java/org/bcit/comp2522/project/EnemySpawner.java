package org.bcit.comp2522.project;

import java.util.Random;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Handles the spawning of enemies.
 *
 * @author Benny Li, Ozan Yurtisigi, Brett Reader
 * @version 1.0
 */
public class EnemySpawner {

  /**
   * The modifier for the number of enemies that spawn.
   */
  private int spawnModifier = ConstantManager.ZERO;

  /**
   * Maximum number of enemies.
   */
  private int enemyMax = ConstantManager.BASE_WAVE_COUNT + spawnModifier;

  /**
   * Current number of enemies.
   */
  private int currEnemyCount;

  /**
   * Random number generator.
   */
  private final Random randomNumber = new Random();

  /**
   * Collection manager.
   */
  private final CollectionManager collectionManager;

  /**
   * Window.
   */
  private final Window window;

  /**
   * Constructor for EnemySpawner.
   *
   * @param collectionManager collection manager
   * @param window            window
   */
  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  /**
   * Method activates the spawner once.
   */
  public void spawnerActivate() {
    if (spawnAvailable()) {
      final int diceRoll = randomNumber.nextInt(EnemyConfig.ENEMY_TYPES) + 1;
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
        default -> throw new ClassCastException("Invalid enemy type");
      }
    }
  }

  /**
   * Spawns an enemy.
   *
   * @param sprite    Visual sprite of the enemy
   * @param enemyType Enemy type according to EnemyConfig
   * @param health    Amount of damage that can be taken before perishing
   * @param damage    Damage dealt to player on contact
   * @param size      Size of the enemy
   * @param speed     Movement speed of the enemy
   */
  public void spawnEnemy(
      PImage sprite,
      int enemyType,
      int health,
      int damage,
      float size,
      float speed
  ) {
    final Enemy newEnemy = new Enemy(
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

  /**
   * Adds enemy to enemy collection.
   *
   * @param newEnemy Enemy to be added
   */
  public void addToCollection(Enemy newEnemy) {
    collectionManager.getEnemies().add(newEnemy);
    collectionManager.getSprites().add(newEnemy);
  }

  /**
   * Check to see enemies are currently allowed to spawn.
   *
   * @return Whether enemies allowed to spawn.
   */
  public boolean spawnAvailable() {
    return this.currEnemyCount <= this.enemyMax;
  }

  /**
   * Randomize position of enemy spawn point.
   *
   * @return Randomized spawn point
   */
  public PVector randomizePosition() {
    final int randomY = randomNumber.nextInt(Window.WINDOW_HEIGHT);
    return new PVector(Window.WINDOW_WIDTH, randomY);
  }

  /**
   * Increases the current enemy count.
   */
  public void increaseEnemyCount() {
    this.currEnemyCount++;
  }

  /**
   * Decreases the current enemy count.
   */
  public void decreaseEnemyCount() {
    this.currEnemyCount--;
  }

  /**
   * Updates the spawn modifier.
   *
   * @param score score
   */
  public void updateSpawnModifier(Score score) {
    this.spawnModifier = score.getCurrentScore() / ConstantManager.TIER_THRESHOLD;
    this.enemyMax = ConstantManager.BASE_WAVE_COUNT + spawnModifier;
  }

  /**
   * Resets the current enemy count.
   */
  public void countReset() {
    this.currEnemyCount = ConstantManager.NO_ENEMIES;
  }

  public int getSpawnModifier() {
    return spawnModifier;
  }
}

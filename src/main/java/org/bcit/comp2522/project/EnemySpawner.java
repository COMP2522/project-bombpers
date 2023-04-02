package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.EnemyConfig.*;

import processing.core.PImage;
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
  private int enemy_max = BASE_WAVE_COUNT + spawnModifier;
  private int curr_enemy_count;
  private Random randomNumber = new Random();
  private CollectionManager collectionManager;
  private Window window;
  private static int randomY;
  private static PVector randomPos;

  public EnemySpawner(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }

  public void spawnerActivate() {
    if (spawnAvailable()) {
      int diceRoll = randomNumber.nextInt(ENEMY_TYPES) + 1;
      switch (diceRoll) {
        case ENEMY_STANDARD_TYPE ->
            spawnEnemy(
                window,
                window.enemyStandardSprite,
                ENEMY_STANDARD_TYPE,
                ENEMY_STANDARD_HEALTH,
                ENEMY_STANDARD_DAMAGE,
                ENEMY_STANDARD_SIZE,
                ENEMY_STANDARD_SPEED
            );
        case ENEMY_FAST_TYPE ->
            spawnEnemy(
                window,
                window.enemyFastSprite,
                ENEMY_FAST_TYPE,
                ENEMY_FAST_HEALTH,
                ENEMY_FAST_DAMAGE,
                ENEMY_FAST_SIZE,
                ENEMY_FAST_SPEED
            );
        case ENEMY_SLOW_TYPE ->
            spawnEnemy(
                window,
                window.enemySlowSprite,
                ENEMY_SLOW_TYPE,
                ENEMY_SLOW_HEALTH,
                ENEMY_SLOW_DAMAGE,
                ENEMY_SLOW_SIZE,
                ENEMY_SLOW_SPEED
            );
        default -> System.out.println("Invalid spawn type");
      }
    }
  }

  public void spawnEnemy(
      Window window,
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
    if (this.curr_enemy_count > this.enemy_max) {
      return false;
    }
    return true;
  }

  public PVector randomizePosition() {
    randomY = randomNumber.nextInt(Window.WINDOW_HEIGHT);
    randomPos = new PVector(Window.WINDOW_WIDTH, randomY);
    return randomPos;
  }

  public void increaseEnemyCount() {
    this.curr_enemy_count++;
  }

  public void decreaseEnemyCount() {
    this.curr_enemy_count--;
  }

  public void updateSpawnModifier(Score score) {
    this.spawnModifier = score.getCurrentScore() / TIER_THRESHOLD;
    this.enemy_max = BASE_WAVE_COUNT + spawnModifier;
  }

  public void countReset() {
    this.curr_enemy_count = NO_ENEMIES;
  }

  public int getCount() {
    return this.curr_enemy_count;
  }

  public int getSpawnModifier() {
    return spawnModifier;
  }
}

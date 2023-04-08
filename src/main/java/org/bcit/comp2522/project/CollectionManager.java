package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * CollectionManager class - manages all collections of sprites.
 */
public class CollectionManager {

  private static CollectionManager c;

  private final List<Sprite> sprites;
  private final ConcurrentLinkedQueue<Projectile> projectiles;
  private final List<Enemy> enemies;
  public static Sprite player;
  private int highScore;
  private int currentScore;


  /**
   * Constructor for CollectionManager.
   */
  private CollectionManager() {
    sprites = new ArrayList<>();
    projectiles = new ConcurrentLinkedQueue<>();
    enemies = new ArrayList<>();
    //This highscore is only associated to CollectionManager, used for DB only.
    highScore = ConstantManager.ZERO;
  }

  public List<Sprite> getSprites() {
    return sprites;
  }

  public ConcurrentLinkedQueue<Projectile> getProjectiles() {
    return projectiles;
  }

  public List<Enemy> getEnemies() {
    return enemies;
  }

  public Sprite getPlayer() {
    return player;
  }

  /**
   * Makes the collection manager object singleton.
   *
   * @return the singleton object
   */
  public static CollectionManager getInstance() {
    if (c == null) {
      c = new CollectionManager();
    }
    return c;
  }

  //For DB purposes
  public void setCurrentScore(int currentScore) {
    this.currentScore = currentScore;
  }

  //For DB purposes
  public int getCurrentScore() {
    return currentScore;
  }

  //For DB purposes
  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }

  //For DB purposes
  public int getHighScore() {
    return highScore;
  }

  /**
   * Removes the enemies and projectiles that collided with each other.
   *
   * @param enemiesToRemove enemies that will be removed
   * @param projectilesToRemove projectiles that will be removed
   */
  public void removeCollidedEntities(ArrayList<Enemy> enemiesToRemove,
                                     ArrayList<Projectile> projectilesToRemove) {
    for (Enemy enemy : enemiesToRemove) {
      getEnemies().remove(enemy);
      getSprites().remove(enemy);
    }
    for (Projectile projectile : projectilesToRemove) {
      getProjectiles().remove(projectile);
      getSprites().remove(projectile);
    }
  }
}
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

  /**
   * Constructor for CollectionManager.
   */
  private CollectionManager() {
    sprites = new ArrayList<>();
    projectiles = new ConcurrentLinkedQueue<>();
    enemies = new ArrayList<>();
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

  public void removeCollidedEntities(List<Enemy> enemiesToRemove,List<Projectile> projectilesToRemove) {
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
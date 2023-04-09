package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * CollectionManager class - manages all collections of sprites.
 *
 * @author Brett Reader, Amarjot Sangha, Ozan Yurtisigi, Sukhraj Sidhu
 * @version 1.0
 */
public final class CollectionManager {

  /**
   * Singleton object.
   */
  private static CollectionManager manager;

  /**
   * List of sprites.
   */
  private final List<Sprite> sprites;

  /**
   * Queue of projectiles.
   */
  private final ConcurrentLinkedQueue<Projectile> projectiles;

  /**
   * List of enemies.
   */
  private final List<Enemy> enemies;

  /**
   * Player sprite.
   */
  public static Sprite player;

  /**
   * Constructor for CollectionManager.
   */
  private CollectionManager() {
    sprites = new ArrayList<>();
    projectiles = new ConcurrentLinkedQueue<>();
    enemies = new ArrayList<>();
  }

  /**
   * Adds a sprite to the list of sprites.
   *
   * @return the list of sprites
   */
  public List<Sprite> getSprites() {
    return sprites;
  }

  /**
   * Adds a projectile to the queue of projectiles.
   *
   * @return the queue of projectiles
   */
  public ConcurrentLinkedQueue<Projectile> getProjectiles() {
    return projectiles;
  }

  /**
   * Adds an enemy to the list of enemies.
   *
   * @return the list of enemies
   */
  public List<Enemy> getEnemies() {
    return enemies;
  }

  /**
   * Adds a player to the list of players.
   *
   * @return the list of players
   */
  public Sprite getPlayer() {
    return player;
  }

  /**
   * Makes the collection manager object singleton.
   *
   * @return the singleton object
   */
  public static CollectionManager getInstance() {
    if (manager == null) {
      manager = new CollectionManager();
    }
    return manager;
  }

  /**
   * Will check to see if the bullet and the enemy have collided. If they have,
   * the enemy and bullet will be removed.
   *
   * @param enemiesToRemove list of enemies to remove
   * @param bulletsToRemove list of bullets to remove
   */
  public void removeCollidedEntities(
      final List<Enemy> enemiesToRemove,
      final List<Projectile> bulletsToRemove
  ) {
    for (final Enemy enemy : enemiesToRemove) {
      getEnemies().remove(enemy);
      getSprites().remove(enemy);
    }
    for (final Projectile projectile : bulletsToRemove) {
      getProjectiles().remove(projectile);
      getSprites().remove(projectile);
    }
  }
}
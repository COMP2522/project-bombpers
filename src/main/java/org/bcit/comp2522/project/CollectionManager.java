package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;
import org.bcit.comp2522.project.enemies.Enemy;

/**
 * CollectionManager class - is a collection of all the sprites in the game.
 */
public class CollectionManager {
  private List<Sprite> sprites;
  private List<Projectile> projectiles;
  private List<Enemy> enemies;
  private Sprite player;

  /**
   * Constructor for CollectionManager.
   */
  public CollectionManager() {
    sprites = new ArrayList<>();
    projectiles = new ArrayList<>();
    enemies = new ArrayList<>();
  }

  public List<Sprite> getSprites() {
    return sprites;
  }

  public List<Projectile> getProjectiles() {
    return projectiles;
  }

  public List<Enemy> getEnemies() {
    return enemies;
  }

  public Sprite getPlayer() {
    return player;
  }
}

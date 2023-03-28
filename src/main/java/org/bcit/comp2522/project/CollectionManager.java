package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;


/**
 * CollectionManager class - manages all collections of sprites.
 */
public class CollectionManager {
  private List<Sprite> sprites;
  //  private List<Projectile> projectiles;
  private List<Enemy> enemies;
  Sprite player;


  /**
   * Constructor for CollectionManager.
   */
  public CollectionManager() {
    sprites = new ArrayList<>();
    //projectiles = new ArrayList<>();
    enemies = new ArrayList<>();
  }

  public List<Sprite> getSprites() {
    return sprites;
  }

  //  public List<Projectile> getProjectiles() {
  //    return projectiles;
  //  }

  public List<Enemy> getEnemies() {
    return enemies;
  }

  public Sprite getPlayer() {
    return player;
  }
}
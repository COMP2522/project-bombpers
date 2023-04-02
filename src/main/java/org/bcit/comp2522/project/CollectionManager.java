package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * CollectionManager class - manages all collections of sprites.
 */
public class CollectionManager {

  private static CollectionManager c;
  private static Sprite player;
  private final List<Sprite> sprites;
  private final ConcurrentLinkedQueue<Projectile> projectiles;
  private final List<Enemy> enemies;

  /**
   * Constructor for CollectionManager.
   */
  private CollectionManager() {
    this.sprites = new ArrayList<>();
    this.projectiles = new ConcurrentLinkedQueue<>();
    this.enemies = new ArrayList<>();
    this.player = getPlayerInstance(Window.getWindow());
    getSprites().add(player);
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

  public Sprite getPlayerInstance(Window window) {
    if (player == null) {
      this.player = Player.getPlayerInstance(window);
    }
    return player;
  }

  public Sprite getPlayer() {
    if (player == null) {
      throw new NullPointerException("Player is null!");
    }
    return player;
  }

  public static CollectionManager getInstance() {
    if (c == null) {
      c = new CollectionManager();
    }
    return c;
  }

}
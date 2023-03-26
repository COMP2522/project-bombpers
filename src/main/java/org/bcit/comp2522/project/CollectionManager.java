package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;
import org.bcit.comp2522.project.enemies.Enemy;

public class CollectionManager {
  private List<Sprite> sprites;
  private List<Projectile> projectiles;
  private List<Enemy> enemies;
  private Sprite player;

  public CollectionManager(List<Sprite> sprites, List<Projectile> projectiles, List<Enemy> enemies, Sprite player) {
    this.sprites = sprites;
    this.projectiles = projectiles;
    this.enemies = enemies;
    this.player = player;
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

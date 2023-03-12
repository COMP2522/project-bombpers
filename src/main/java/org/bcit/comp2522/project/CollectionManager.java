package org.bcit.comp2522.project;

import org.bcit.comp2522.project.enemies.Enemy_Base;

import java.util.ArrayList;
import java.util.List;

public class CollectionManager {
  private List<Sprite> sprites;
  private List<Projectile> projectiles;
  private List<Enemy_Base> enemies;
  private Sprite player;

  public CollectionManager() {
    sprites = new ArrayList<>();
    projectiles = new ArrayList<>();
    enemies = new ArrayList<>();
    //player?
  }
}

package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;

public class CollectionManager {
  private List<Sprite> sprites;
  private List<Projectile> projectiles;
  private List<Enemy> enemies;
  private Sprite player;

  public CollectionManager() {
    sprites = new ArrayList<>();
    projectiles = new ArrayList<>();
    enemies = new ArrayList<>();
    //player?
  }
}

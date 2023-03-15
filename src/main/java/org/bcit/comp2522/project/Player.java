package org.bcit.comp2522.project;

import org.bcit.comp2522.project.enemies.Enemy_Base;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Sprite implements Comparable<Enemy_Base>{
  private int level;
  private String name;
  private PImage characterSprite;
  private final float resize = 0.6f;
  // Add an ArrayList of Projectiles
  private ArrayList<Projectile> projectiles;
  private final float projectileSize = 5.0f;
  private final float projectileSpeed = 3.0f;
  private final Color projectileColor = new Color(0, 255, 0);

  public Player(PVector position, PVector direction,
                float size, float speed, Color color,
                Window window, int health, int damage,
                int level, String name, PImage characterSprite) {
    super(position, direction, size, speed, color, window, health, damage);
    this.level = level;
    this.name = name;
    this.characterSprite = characterSprite;
    this.projectiles = new ArrayList<>();
  }

  public ArrayList<Projectile> getProjectiles() {
    return projectiles;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param one
   * @param two
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void compareTo() {
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void draw() {
    window.image(characterSprite, position.x, position.y, size * resize, size * resize);
//    window.fill(color.getRGB());
//    window.ellipse(position.x, position.y, size, size);
    for (Projectile p : projectiles) {
      p.draw();
    }
  }

  /**
   *
   */
  @Override
  public void move() {
    if (window.keyPressed) {
      if (window.key == 'w' || window.key == 'W') {
        position.y -= speed;
      }
      if (window.key == 'a' || window.key == 'A') {
        position.x -= speed;
      }
      if (window.key == 's' || window.key == 'S') {
        position.y += speed;
      }
      if (window.key == 'd' || window.key == 'D') {
        position.x += speed;
      }
      if (window.mousePressed && window.mouseButton == window.LEFT) {
        aim();
        System.out.println("detected");
      }
    }
    for (Projectile p : projectiles) {
      p.move();
    }
  }

  private void aim(){
    // Determine the direction the player is aiming by getting the mouse position relative to the player position
    PVector aimDirection = new PVector(window.mouseX - position.x, window.mouseY - position.y);
    aimDirection.normalize();
    System.out.println("aimed");
    shoot(aimDirection);
    System.out.println("shot");
  }

  private void shoot(PVector aimDirection) {
    // Create a new Projectile and add it to the projectiles ArrayList
    Projectile newProjectile = new Projectile(
            position.copy(),
            aimDirection,
            projectileSize,
            projectileSpeed,
            projectileColor,
            window,
            1,
            1
    );
    System.out.println("added");
    projectiles.add(newProjectile);
    System.out.println("added to arraylist");
  }

  @Override
  public int compareTo(Sprite enemy) {
    if (Float.compare(this.size, enemy.getSize()) < 0) {
      return -1;
    } else if (Float.compare(this.size, enemy.getSize()) >= 0) {
      return 1;
    }
    return 0;
  }
}

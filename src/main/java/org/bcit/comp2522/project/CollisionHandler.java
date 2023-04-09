package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Oversees the collision detection and handling of entities.
 *
 * @author Sukhraj Sidhu, Brett Reader, Ozan Yurtisigi
 * @version 1.0
 */
public class CollisionHandler {

  /**
   * The CollectionManager object.
   */
  private final CollectionManager collectionManager;

  /**
   * The Window object.
   */
  private final Window window;

  /**
   * The UIHandler object.
   */
  private final UiHandler uiHandler;

  /**
   * Constructs a CollisionHandler object.
   *
   * @param collectionManager the CollectionManager object
   * @param window            the Window object
   * @param uiHandler         the UIHandler object
   */
  public CollisionHandler(
      final CollectionManager collectionManager,
      final Window window,
      final UiHandler uiHandler
  ) {
    this.collectionManager = collectionManager;
    this.window = window;
    this.uiHandler = uiHandler;
  }

  /**
   * Checks for collisions between entities and handles them.
   */
  public void handleCollisions() {
    final ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
    final ArrayList<Projectile> bulletsToRemove = new ArrayList<>();

    checkPlayerEnemyCollisions(enemiesToRemove);
    checkProjectileEnemyCollisions(enemiesToRemove, bulletsToRemove);
    CollectionManager.getInstance().removeCollidedEntities(enemiesToRemove, bulletsToRemove);
  }

  /**
   * Checks for collisions between projectiles and enemies.
   *
   * @param enemiesToRemove the list of enemies to remove
   * @param projectiles     the list of projectiles to remove
   */
  public void checkProjectileEnemyCollisions(
      final List<Enemy> enemiesToRemove,
      final List<Projectile> projectiles
  ) {

    for (final Projectile projectile : collectionManager.getProjectiles()) {
      for (final Enemy enemy : collectionManager.getEnemies()) {
        projectile.collide(projectile, enemy);
        if (projectile.isDead()) {
          handleProjectileEnemyCollision(projectile, enemy, projectiles, enemiesToRemove);
        }
      }
    }
  }

  /**
   * Handles collisions between projectiles and enemies.
   *
   * @param projectile      the projectile
   * @param enemy           the enemy
   * @param bulletsToRemove the list of projectiles to remove
   * @param enemiesToRemove the list of enemies to remove
   */
  public void handleProjectileEnemyCollision(
      final Projectile projectile,
      final Enemy enemy,
      final List<Projectile> bulletsToRemove,
      final List<Enemy> enemiesToRemove
  ) {
    bulletsToRemove.add(projectile);
    if (enemy.isDead()) {
      enemiesToRemove.add(enemy);
      window.updateGameParametersAfterEnemyDeath(enemy);
    }
  }

  /**
   * Checks for collisions between the player and enemies.
   *
   * @param enemiesToRemove the list of enemies to remove
   */
  public void checkPlayerEnemyCollisions(final List<Enemy> enemiesToRemove) {
    for (final Enemy enemy : collectionManager.getEnemies()) {
      if (enemy.checkCollisionWithPlayer(collectionManager.getPlayer())) {
        handlePlayerEnemyCollision(enemy, enemiesToRemove);
      }
    }
  }

  /**
   * Handles collisions between the player and enemies.
   *
   * @param enemy           the enemy
   * @param enemiesToRemove the list of enemies to remove
   */
  public void handlePlayerEnemyCollision(final Enemy enemy, final List<Enemy> enemiesToRemove) {
    enemiesToRemove.add(enemy);
    collectionManager
        .getPlayer()
        .setHealth(collectionManager.getPlayer().getHealth() - enemy.getDamage());
    uiHandler.getHpDisplay().takeDamage(enemy.getDamage());
    if (collectionManager.getPlayer().getHealth() <= 0) {
      window.handleGameOver(enemiesToRemove);
    }
  }
}

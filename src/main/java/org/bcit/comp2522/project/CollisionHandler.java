package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {

  private final CollectionManager collectionManager;
  private final Window window;
  private final UIHandler uiHandler;

  public CollisionHandler(final CollectionManager collectionManager, final Window window, final
      UIHandler uiHandler) {
    this.collectionManager = collectionManager;
    this.window = window;
    this.uiHandler = uiHandler;
  }

  public void handleCollisions() {
    final ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
    final ArrayList<Projectile> bulletsToRemove = new ArrayList<>();

    checkPlayerEnemyCollisions(enemiesToRemove);
    checkProjectileEnemyCollisions(enemiesToRemove, bulletsToRemove);
    CollectionManager.getInstance().removeCollidedEntities(enemiesToRemove, bulletsToRemove);
  }

  public void checkProjectileEnemyCollisions(final List<Enemy> enemiesToRemove, final
      List<Projectile> projectilesToRemove) {

    for (final Projectile projectile : collectionManager.getProjectiles()) {
      for (final Enemy enemy : collectionManager.getEnemies()) {
        projectile.collide(projectile, enemy);
        if (projectile.isDead()) {
          handleProjectileEnemyCollision(projectile, enemy, projectilesToRemove, enemiesToRemove);
        }
      }
    }
  }

  public void handleProjectileEnemyCollision(final Projectile projectile, final Enemy enemy,
                                             final List<Projectile> bulletsToRemove, final
                                             List<Enemy> enemiesToRemove) {
    bulletsToRemove.add(projectile);
    if (enemy.isDead()) {
      enemiesToRemove.add(enemy);
      window.updateGameParametersAfterEnemyDeath(enemy);
    }
  }

  public void checkPlayerEnemyCollisions(final List<Enemy> enemiesToRemove) {
    for (final Enemy enemy : collectionManager.getEnemies()) {
      if (enemy.checkCollisionWithPlayer(collectionManager.getPlayer())) {
        handlePlayerEnemyCollision(enemy, enemiesToRemove);
      }
    }
  }

  public void handlePlayerEnemyCollision(final Enemy enemy, final List<Enemy> enemiesToRemove) {
    enemiesToRemove.add(enemy);
    collectionManager.getPlayer().setHealth(collectionManager.getPlayer()
        .getHealth() - enemy.getDamage());
    uiHandler.getHPDisplay().takeDamage(enemy.getDamage());
    if (collectionManager.getPlayer().getHealth() <= 0) {
      window.handleGameOver(enemiesToRemove);
    }
  }

}

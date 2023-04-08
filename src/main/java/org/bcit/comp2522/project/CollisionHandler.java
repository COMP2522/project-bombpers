package org.bcit.comp2522.project;

import java.util.ArrayList;

public class CollisionHandler {

  CollectionManager collectionManager;
  Window window;
  UIHandler uiHandler;

  public CollisionHandler(CollectionManager collectionManager, Window window, UIHandler uiHandler) {
    this.collectionManager = collectionManager;
    this.window = window;
    this.uiHandler = uiHandler;
  }

  public void handleCollisions() {
    ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
    ArrayList<Projectile> projectilesToRemove = new ArrayList<>();

    checkPlayerEnemyCollisions(enemiesToRemove);
    checkProjectileEnemyCollisions(enemiesToRemove, projectilesToRemove);
    CollectionManager.getInstance().removeCollidedEntities(enemiesToRemove, projectilesToRemove);
  }

  public void checkProjectileEnemyCollisions(ArrayList<Enemy> enemiesToRemove, ArrayList<Projectile> projectilesToRemove) {

    for (Projectile projectile : collectionManager.getProjectiles()) {
      for (Enemy enemy : collectionManager.getEnemies()) {
        projectile.collide(projectile, enemy);
        if (projectile.isDead()) {
          handleProjectileEnemyCollision(projectile, enemy, projectilesToRemove, enemiesToRemove);
        }
      }
    }
  }

  public void handleProjectileEnemyCollision(Projectile projectile, Enemy enemy, ArrayList<Projectile> projectilesToRemove, ArrayList<Enemy> enemiesToRemove) {
    projectilesToRemove.add(projectile);
    if (enemy.isDead()) {
      enemiesToRemove.add(enemy);
      window.updateGameParametersAfterEnemyDeath(enemy);
    }
  }

  public void checkPlayerEnemyCollisions(ArrayList<Enemy> enemiesToRemove) {
    for (Enemy enemy : collectionManager.getEnemies()) {
      if (enemy.checkCollisionWithPlayer(collectionManager.getPlayer())) {
        handlePlayerEnemyCollision(enemy, enemiesToRemove);
      }
    }
  }

  public void handlePlayerEnemyCollision(Enemy enemy, ArrayList<Enemy> enemiesToRemove) {
    enemiesToRemove.add(enemy);
    collectionManager.getPlayer().setHealth(collectionManager.getPlayer().getHealth() - enemy.getDamage());
    uiHandler.getHPDisplay().takeDamage(enemy.getDamage());
    if (collectionManager.getPlayer().getHealth() <= 0) {
      window.handleGameOver(enemiesToRemove);
    }
  }

}

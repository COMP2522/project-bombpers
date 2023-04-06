package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.ConstantManager.CUT_HITBOX_IN_HALF;
import static org.bcit.comp2522.project.ConstantManager.ENEMY_STANDARD_HITBOX_HEIGHT;
import static org.bcit.comp2522.project.ConstantManager.ENEMY_STANDARD_HITBOX_WIDTH;
import static org.bcit.comp2522.project.ConstantManager.TWO;
import static org.bcit.comp2522.project.ConstantManager.ZERO;

import processing.core.PImage;
import processing.core.PVector;

public class Enemy extends Sprite {
  //protected Sprite player;
  protected PImage enemySprite;
  protected int enemyType;
  private boolean dead;
  private final float hitboxWidth;
  private final float hitboxHeight;

  public Enemy(
      Window window,
      //Sprite player,
      PImage sprite,
      int enemyType,
      int health,
      int damage,
      float size,
      float speed,
      PVector pos
  ) {
    super(window);
    this.direction = new PVector(ZERO, ZERO);
    //this.player = player;
    this.enemySprite = sprite;
    this.enemyType = enemyType;
    this.health = health;
    this.damage = damage;
    this.size = size;
    this.speed = speed;
    this.position = pos;
    this.hitboxWidth = ENEMY_STANDARD_HITBOX_WIDTH;
    this.hitboxHeight = ENEMY_STANDARD_HITBOX_HEIGHT;
  }

  /**
   * Method for two Enemy Sprites colliding.
   *
   * @param one the first sprite
   * @param two the second sprite
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  @Override
  public PVector getCenterPosition() {
    return new PVector(position.x + hitboxWidth / CUT_HITBOX_IN_HALF,
            position.y + hitboxHeight / CUT_HITBOX_IN_HALF);
  }

  /**
   * draws the enemy.
   */
  @Override
  public void draw() {
    window.image(enemySprite, position.x, position.y, size, size);
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  /**
   * moves the enemy.
   */
  @Override
  public void move() {
    PVector directionToPlayer = PVector.sub(CollectionManager.getInstance()
            .getPlayer().getPosition(), position);
    directionToPlayer.normalize();
    directionToPlayer.mult(speed);
    direction = directionToPlayer;
    position.add(direction);
  }

  /**
   * Checks if the enemy is colliding with the player.
   *
   * @param player the player
   * @return true if the enemy is colliding with the player
   */
  public boolean checkCollisionWithPlayer(Sprite player) {
    float dx = player.getCenterPosition().x - this.getCenterPosition().x;
    float dy = player.getCenterPosition().y - this.getCenterPosition().y;
    float distance = (float) Math.sqrt(dx * dx + dy * dy);

    float minDistance = (player.getSize() + this.size) / TWO;

    return distance <= minDistance;
  }

}
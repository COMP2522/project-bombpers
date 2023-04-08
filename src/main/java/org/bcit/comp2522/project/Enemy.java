package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

/**
 * Class for enemy objects.
 */
public class Enemy extends Sprite {
  //protected Sprite player;
  protected PImage enemySprite;
  protected int enemyType;
  private boolean dead;
  public final float hitboxWidth;
  private final float hitboxHeight;

  /**
   * Constructor for enemy objects.
   *
   * @param window Window that houses this enemy
   * @param sprite Visual sprite of enemy
   * @param enemyType Enemy type according to EnemyConfig
   * @param health Amount of damage that can be taken before perishing
   * @param damage Damage dealt to player on contact
   * @param size Size of the enemy
   * @param speed Movement speed of the enemy
   * @param pos Initial position of enemy
   */
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
    this.direction = new PVector(ConstantManager.ZERO, ConstantManager.ZERO);
    //this.player = player;
    this.enemySprite = sprite;
    this.enemyType = enemyType;
    this.health = health;
    this.damage = damage;
    this.size = size;
    this.speed = speed;
    this.position = pos;
    this.hitboxWidth = ConstantManager.ENEMY_STANDARD_HITBOX_WIDTH;
    this.hitboxHeight = ConstantManager.ENEMY_STANDARD_HITBOX_HEIGHT;
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
    return new PVector(
        position.x + hitboxWidth / ConstantManager.CUT_HITBOX_IN_HALF,
        position.y + hitboxHeight / ConstantManager.CUT_HITBOX_IN_HALF
    );
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
    PVector directionToPlayer = PVector.sub(
        CollectionManager
            .getInstance()
            .getPlayer()
            .getPosition(),
        position);
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

    float minDistance = (player.getSize() + this.size) / ConstantManager.TWO;

    return distance <= minDistance;
  }

}
package org.bcit.comp2522.project.enemies;

import java.awt.Color;

import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

//TODO: Per our UML, this should be extending Enemy, not EnemyStandard. Please Fix.

/**
 * Slow enemy class - is a child of the EnemyStandard class.
 */
public class EnemySlow extends EnemyStandard {
  protected static final String NAME_ENEM_SLOW = "Big Hanji";
  protected static final int HP_ENEM_SLOW = 6;
  protected static final int DMG_ENEM_SLOW = 4;
  protected static final float SIZE_ENEM_SLOW = 20f;
  protected static final float SPEED_ENEM_SLOW = 0.9f;
  protected static final Color COLOR_ENEM_SLOW = new Color(230, 100, 0);

  /**
   * Constructor for EnemySlow.
   *
   * @param position  position of the enemy
   * @param direction direction of the enemy
   * @param window    window the enemy is in
   */
  public EnemySlow(PVector position, PVector direction, Window window, Player player) {
    super(position, direction, window, player);
    this.size = SIZE_ENEM_SLOW;
    this.speed = SPEED_ENEM_SLOW;
    this.name = NAME_ENEM_SLOW;
    this.color = COLOR_ENEM_SLOW;
  }

}
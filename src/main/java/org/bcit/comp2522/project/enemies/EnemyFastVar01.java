package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

/**
 * EnemyFastVar01. Class WIP.
 */
public class EnemyFastVar01 extends EnemyFast {
  protected static final String NAME_ENEM_FAST_VAR01 = "Spooky Ghost Hanji";
  protected static final int HP_ENEM_FAST_VAR01 = 2;
  protected static final int DMG_ENEM_FAST_VAR01 = 1;
  protected static final float SIZE_ENEM_FAST_VAR01 = 0.8f;
  protected static final float SPEED_ENEM_FAST_VAR01 = 1.5f;

  /**
   * Constructor for EnemyFastVar01.
   */
  public EnemyFastVar01(PVector position, PVector direction, Window window, Player player) {
    super(position, direction, window, player);
    this.name = NAME_ENEM_FAST_VAR01;
    this.size = SIZE_ENEM_FAST_VAR01;
    this.speed = SPEED_ENEM_FAST_VAR01;
  }

  public void collide() {
    // TODO: Ghost enemy, so ignore walls and always have straight line pathing
  }
}
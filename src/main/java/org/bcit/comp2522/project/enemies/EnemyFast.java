package org.bcit.comp2522.project.enemies;

import java.awt.Color;

import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

//TODO: Per our UML, this should be extending Enemy, not EnemyStandard. Please Fix.

/**
 * Fast enemy class - is a child of the EnemyStandard class.
 */
public class EnemyFast extends EnemyStandard {
  protected static final String NAME_ENEM_FAST = "Sanic the Hanji";
  protected static final int HP_ENEM_FAST = 2;
  protected static final int DMG_ENEM_FAST = 1;
  protected static final float SIZE_ENEM_FAST = 7.5f;
  protected static final float SPEED_ENEM_FAST = 1.6f;
  protected static final Color COLOR_ENEM_FAST = new Color(0, 200, 255);
  protected Player player;

  public EnemyFast(PVector position, PVector direction, Window window, Player player) {
    super(position, direction, window, player);
    this.size = SIZE_ENEM_FAST;
    this.speed = SPEED_ENEM_FAST;
    this.color = COLOR_ENEM_FAST;
  }
}
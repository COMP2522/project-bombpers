package org.bcit.comp2522.project.enemies;

import java.awt.Color;

import org.bcit.comp2522.project.Player;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

/**
 * Standard enemy class - is a child of the Enemy class.
 */
public class EnemyStandard extends Enemy {

  /**
   * Constructor for EnemyStandard.
   *
   * @param position  the position of the enemy
   * @param direction the direction of the enemy
   * @param window    the window the enemy is in
   */
  public EnemyStandard(PVector position, PVector direction, Window window, Player player) {
    super("Modus Ponens", 4, 2, position, direction, 10f, 1.4f, new Color(255, 0, 0), window, player);
  }

}
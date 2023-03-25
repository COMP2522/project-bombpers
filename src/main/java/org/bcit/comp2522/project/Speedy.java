package org.bcit.comp2522.project;

import java.awt.Color;
import processing.core.PImage;
import processing.core.PVector;


/**
 * Speedy class - is a child of the Player class.
 */
public class Speedy extends Player {
  /**
   * Creates a speedy player.
   *
   * @param position - the position of the player
   * @param direction - the direction of the player
   * @param size - the size of the player
   * @param speed - the speed of the player
   * @param color - the color of the player
   * @param window - the window of the player
   * @param health - the health of the player
   * @param damage - the damage the player does
   * @param level - the level of the player
   * @param name - the name of the player
   * @param characterSprite - the character sprite of the player
   */
  public Speedy(PVector position,
                PVector direction,
                float size,
                float speed,
                Color color,
                Window window,
                int health,
                int damage,
                int level,
                String name,
                PImage characterSprite) {
    super(position,
          direction,
          size,
          speed,
          color,
          window,
          health,
          damage,
          level,
          name,
          characterSprite);
  }
}

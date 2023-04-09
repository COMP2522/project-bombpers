package org.bcit.comp2522.project;

/**
 * Collidable interface - is implemented by all classes
 * that can collide with each other.
 *
 * @author Brett Reader, Sukhraj Sidhu
 * @version 1.0
 */
public interface Collidable {

  /**
   * Checks if two sprites collide.
   *
   * @param one first sprite
   * @param two second sprite
   */
  void collide(Sprite one, Sprite two);
}
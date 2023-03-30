package org.bcit.comp2522.project;

/**
 * Collidable interface - is implemented by all classes that can collide with each other.
 */
public interface Collidable {
  void collide(Sprite one, Sprite two);
}

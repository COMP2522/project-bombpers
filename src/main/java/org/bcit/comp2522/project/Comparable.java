package org.bcit.comp2522.project;

/**
 * interface for comparable objects.
 */
public interface Comparable<E extends Sprite> {
  @Override
   boolean equals(Object o);

  @Override
   int hashCode();
}

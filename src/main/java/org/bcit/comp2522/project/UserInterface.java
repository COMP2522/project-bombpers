package org.bcit.comp2522.project;

/**
 * Abstract class for UserInterface.
 */

public abstract class UserInterface {
  /**
   * The x-position of the UserInterface.
   */
  private int positionX;
  /**
   * The y-position of the UserInterface.
   */
  private int positionY;
  /**
   * Constructor for UserInterface.
   *
   * @param positionX the x-position of the UserInterface
   * @param positionY the y-position of the UserInterface
   */

  public UserInterface(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  /**
   * Draws the UserInterface.
   */
  protected abstract void drawUserInterface();

  /**
   * Gets the x-position of the UserInterface.
   *
   * @return the x-position of the UserInterface
   */
  public int getPositionX() {
    return positionX;
  }

  /**
   * Sets the x-position of the UserInterface.
   *
   * @param positionX the x-position of the UserInterface
   */
  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  /**
   * Gets the y-position of the UserInterface.
   *
   * @return the y-position of the UserInterface
   */
  public int getPositionY() {
    return positionY;
  }

  /**
   * Sets the y-position of the UserInterface.
   *
   * @param positionY the y-position of the UserInterface
   */
  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}
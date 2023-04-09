package org.bcit.comp2522.project;

/**
 * Abstract class for the User Interface.
 *
 * @author Brett Reader, Amarjot Singha, Benny Li
 * @version 1.0
 */
public abstract class UserInterface {

  /**
   * The x-position of the UserInterface.
   */
  protected int positionX;
  /**
   * The y-position of the UserInterface.
   */
  protected int positionY;

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
   * Gets the y-position of the UserInterface.
   *
   * @return the y-position of the UserInterface
   */
  public int getPositionY() {
    return positionY;
  }

}
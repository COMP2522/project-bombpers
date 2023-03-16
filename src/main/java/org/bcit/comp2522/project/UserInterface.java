package org.bcit.comp2522.project;

/**
 * UI class - is a parent class for all UI elements.
 */
public class UserInterface {
  private int positionX;
  private int positionY;

  public UserInterface(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  private void drawUserInterface() {
    //TODO: Implement this method
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}

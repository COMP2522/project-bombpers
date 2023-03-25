package org.bcit.comp2522.project;

public abstract class UserInterface {
  private int positionX;
  private int positionY;

  public UserInterface(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  protected abstract void drawUserInterface();

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
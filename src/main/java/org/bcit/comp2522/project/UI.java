package org.bcit.comp2522.project;

public class UI {
  private int XPos;
  private int YPos;

  public UI(int xPos, int yPos) {
    this.XPos = xPos;
    this.YPos = yPos;
  }

  private void drawUI() {
    //TODO: Implement this method
  }

  public int getXPos() {
    return XPos;
  }

  public void setXPos(int XPos) {
    this.XPos = XPos;
  }

  public int getYPos() {
    return YPos;
  }

  public void setYPos(int YPos) {
    this.YPos = YPos;
  }
}

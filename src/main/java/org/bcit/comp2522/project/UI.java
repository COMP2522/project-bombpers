package org.bcit.comp2522.project;

public class UI extends Window{
  private int XPos;
  private int YPos;

  public UI(int xPos, int yPos) {
    this.XPos = xPos;
    this.YPos = yPos;
  }

  private void drawUI() {
    //TODO: Implement this method
    background(0);
    //welcome message
    textSize(100);
    fill(0, 408, 612);

    //button
    fill(153);
    rect(120, 200, 200, 50);
    textSize(50);
    fill(200, 0, 0);
    text("Welcome!", 140, 245);
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

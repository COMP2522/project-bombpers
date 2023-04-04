package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class HPDisplay extends UserInterface {
  private static final int X_POS = (int) (Window.WINDOW_WIDTH * 0.15f);
  private static final int Y_POS = (int) (Window.WINDOW_HEIGHT * 0.9f);
  private PApplet pApp;
  private int health;

  public HPDisplay(PApplet p) {
    super(X_POS, Y_POS);
    this.pApp = p;
    update();
  }

  @Override
  public void drawUserInterface() {
    pApp.textAlign(PConstants.RIGHT, PConstants.CENTER);
    pApp.fill(220, 220, 220);
    pApp.textSize(24);
    pApp.text("HP: " + health, positionX, positionY);
  }

  public void update() {
    this.health = CollectionManager.getInstance().getPlayer().getHealth();
  }

  public void damage(int dmg) {
    this.health = CollectionManager.getInstance().getPlayer().getHealth() - dmg;
    update();
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

package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import static org.bcit.comp2522.project.ConstantManager.*;

public class HPDisplay extends UserInterface {
  private static final int DEFAULT_X_POS = (int) (Window.WINDOW_WIDTH * POINTFIFTEEN);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * POINTNINE);
  private CollectionManager collectionManager;
  private PApplet pApp;
  private int health;

  public HPDisplay(PApplet p) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.pApp = p;
    collectionManager = CollectionManager.getInstance();
    update();
  }

  @Override
  public void drawUserInterface() {
    pApp.textAlign(PConstants.RIGHT, PConstants.CENTER);
    pApp.fill(TWOTWENTY, TWOTWENTY, TWOTWENTY);
    pApp.textSize(TWENTYFOUR);
    pApp.text("HP: " + health, positionX, positionY);
  }

  public void update() {
    this.health = collectionManager.getPlayer().getHealth();
  }

  public void takeDamage(int dmg) {
    this.health = collectionManager.getPlayer().getHealth() - dmg;
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

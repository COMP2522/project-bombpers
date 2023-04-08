package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

public class HPDisplay extends UserInterface {
  private static final int DEFAULT_X_POS =
          (int) (Window.WINDOW_WIDTH * ConstantManager.POINTFIFTEEN);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * ConstantManager.POINTNINE);
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
    pApp.fill(ConstantManager.TWOTWENTY, ConstantManager.TWOTWENTY, ConstantManager.TWOTWENTY);
    pApp.textSize(ConstantManager.TWENTYFOUR);
    pApp.text("HP: " + health, positionX, positionY);
  }

  public void update() {
    this.health = collectionManager.getPlayer().getHealth();
  }

  public void takeDamage(int dmg) {
    this.health = collectionManager.getPlayer().getHealth() - dmg;
    update();
  }
}

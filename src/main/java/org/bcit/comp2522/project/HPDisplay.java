package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

public class HPDisplay extends UserInterface {
  private static final int DEFAULT_X_POS =
          (int) (Window.WINDOW_WIDTH * ConstantManager.HP_WIDTH);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * ConstantManager.HP_HEIGHT);
  private final CollectionManager collectionManager;
  private final PApplet papp;
  private int health;

  public HPDisplay(PApplet p) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.papp = p;
    collectionManager = CollectionManager.getInstance();
    update();
  }

  @Override
  public void drawUserInterface() {
    papp.textAlign(PConstants.RIGHT, PConstants.CENTER);
    papp.fill(ConstantManager.HP_COLOR, ConstantManager.HP_COLOR, ConstantManager.HP_COLOR);
    papp.textSize(ConstantManager.TEXT_SIZE);
    papp.text("HP: " + health, positionX, positionY);
  }

  public void update() {
    this.health = collectionManager.getPlayer().getHealth();
  }

  public void takeDamage(int dmg) {
    this.health = collectionManager.getPlayer().getHealth() - dmg;
    update();
  }
}

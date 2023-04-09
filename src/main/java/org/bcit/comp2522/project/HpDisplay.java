package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * This class handles the UI element to show the HP on screen.
 *
 * @author Benny Li, Ozan Yurtisigi
 * @version 1.0
 */
public class HpDisplay extends UserInterface {
  private static final int DEFAULT_X_POS =
      (int) (Window.WINDOW_WIDTH * ConstantManager.HP_WIDTH);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * ConstantManager.HP_HEIGHT);
  private final CollectionManager collectionManager;
  private final PApplet papp;
  private int health;

  /**
   * Constructor for HPDisplay text.
   *
   * @param p PApplet object to draw the text
   */
  public HpDisplay(PApplet p) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    collectionManager = CollectionManager.getInstance();
    this.papp = p;
    //update();
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

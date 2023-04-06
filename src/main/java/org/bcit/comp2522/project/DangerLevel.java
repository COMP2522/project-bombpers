package org.bcit.comp2522.project;

import static org.bcit.comp2522.project.ConstantManager.ONE;
import static org.bcit.comp2522.project.ConstantManager.POINTNINETYFIVE;
import static org.bcit.comp2522.project.ConstantManager.TWENTYFOUR;
import static org.bcit.comp2522.project.ConstantManager.TWOF;
import static org.bcit.comp2522.project.ConstantManager.TWOHUNDRED;
import static org.bcit.comp2522.project.ConstantManager.ZERO;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * DangerLevel class that increases the enemy number on screen depending on score.
 */
public class DangerLevel extends UserInterface {
  private static final int DEFAULT_X_POS = (int) (Window.WINDOW_WIDTH / TWOF);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * POINTNINETYFIVE);
  private int dangerLevel;
  private PApplet pApp;
  private EnemySpawner eSpawner;

  /**
   * Constructor for DangerLevel.
   *
   * @param p PApplet object
   * @param spawner enemy spawner
   */
  public DangerLevel(PApplet p, EnemySpawner spawner) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.pApp = p;
    this.eSpawner = spawner;
    this.dangerLevel = eSpawner.getSpawnModifier() + ONE;
  }

  @Override
  protected void drawUserInterface() {
    pApp.fill(TWOHUNDRED, ZERO, ZERO);
    pApp.textAlign(PConstants.CENTER);
    pApp.textSize(TWENTYFOUR);
    pApp.text("DANGER LEVEL: " + dangerLevel, positionX, positionY);
  }

  public void increaseDangerLevel() {
    this.dangerLevel++;
  }

  public void resetDangerLevel() {
    this.dangerLevel = ONE;
  }

  public int getCurrDangerLevel() {
    return this.dangerLevel;
  }

  public void update() {
    this.dangerLevel = eSpawner.getSpawnModifier() + ONE;
  }

  public int getPositionX() {
    return this.positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return this.positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }
}

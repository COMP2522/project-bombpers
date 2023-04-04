package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

import static org.bcit.comp2522.project.ConstantManager.*;

public class DangerLevel extends UserInterface {
  private static final int DEFAULT_X_POS = (int) (Window.WINDOW_WIDTH / TWOF);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * POINTNINETYFIVE);
  private int dangerLevel;
  private final PApplet pApp;
  private final EnemySpawner eSpawner;

  public DangerLevel(PApplet p, EnemySpawner spawner) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.pApp = p;
    this.eSpawner = spawner;
    this.dangerLevel = eSpawner.getSpawnModifier() + ONE;
  }

  @Override
  public void drawUserInterface(GameState state) {
    pApp.fill(TWOHUNDRED, ZERO, ZERO);
    pApp.textAlign(PConstants.CENTER);
    pApp.textSize(TWENTYFOUR);
    pApp.text("DANGER LEVEL: " + dangerLevel, positionX, positionY);
  }

  /**
   * Band-aid
   */
  @Override
  protected void drawUserInterface() {

  }

  public void increaseDangerLevel() {
    this.dangerLevel++;
  }

  public void resetDangerLevel() {
    this.dangerLevel = 1;
  }

  public int getDangerLevel() {
    return this.dangerLevel;
  }

  public void update() {
    this.dangerLevel = eSpawner.getSpawnModifier() + 1;
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

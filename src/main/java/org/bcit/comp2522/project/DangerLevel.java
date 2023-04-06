package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

public class DangerLevel extends UserInterface {
  private static final int DEFAULT_X_POS = (int) (Window.WINDOW_WIDTH / 2f);
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * 0.95f);
  private int dangerLevel;
  private PApplet pApp;
  private EnemySpawner eSpawner;

  public DangerLevel(PApplet p, EnemySpawner spawner) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.pApp = p;
    this.eSpawner = spawner;
    this.dangerLevel = eSpawner.getSpawnModifier() + 1;
  }

  @Override
  protected void drawUserInterface() {
    pApp.fill(200, 0, 0);
    pApp.textAlign(PConstants.CENTER);
    pApp.textSize(24);
    pApp.text("DANGER LEVEL: " + dangerLevel, positionX, positionY);
  }

  public void increaseDangerLevel() {
    this.dangerLevel++;
  }

  public void resetDangerLevel() {
    this.dangerLevel = 1;
  }

  public int getCurrDangerLevel() {
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

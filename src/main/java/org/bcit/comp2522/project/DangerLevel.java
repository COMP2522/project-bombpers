package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

public class DangerLevel {
  private static final int X_POS = (int) (Window.WINDOW_WIDTH / 2);
  private static final int Y_POS = (int) (Window.WINDOW_HEIGHT * 0.95);
  private int dangerLevel;
  private PApplet pApp;
  private EnemySpawner eSpawner;

  public DangerLevel(PApplet p, EnemySpawner spawner) {
    this.pApp = p;
    this.eSpawner = spawner;
    this.dangerLevel = eSpawner.getSpawnModifier() + 1;
  }

  public void draw() {
    pApp.fill(200, 0, 0);
    pApp.textAlign(PConstants.CENTER);
    pApp.textSize(24);
    pApp.text("DANGER LEVEL: " + dangerLevel, X_POS, Y_POS);
  }

  public void increaseDangerLevel() {
    dangerLevel++;
  }

  public void resetDangerLevel() {
    dangerLevel = 1;
  }

  public int getDangerLevel() {
    return dangerLevel;
  }

  public void update() {
    this.dangerLevel = eSpawner.getSpawnModifier() + 1;
  }
}

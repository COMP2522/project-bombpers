package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * DangerLevel class that increases the enemy number on screen depending on score.
 */
public class DangerLevel extends UserInterface {
  private static final int DEFAULT_X_POS = (int) (Window.WINDOW_WIDTH / ConstantManager.TWOF);
  private static final int DEFAULT_Y_POS =
      (int) (Window.WINDOW_HEIGHT * ConstantManager.POINTNINETYFIVE);
  private int level;
  private final PApplet pApp;
  private final EnemySpawner eSpawner;

  /**
   * Constructor for DangerLevel.
   *
   * @param p       PApplet object
   * @param spawner enemy spawner
   */
  public DangerLevel(final PApplet p, final EnemySpawner spawner) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.pApp = p;
    this.eSpawner = spawner;
    this.level = eSpawner.getSpawnModifier() + ConstantManager.ONE;
  }

  @Override
  protected void drawUserInterface() {
    pApp.fill(ConstantManager.TWOHUNDRED, ConstantManager.ZERO, ConstantManager.ZERO);
    pApp.textAlign(PConstants.CENTER);
    pApp.textSize(ConstantManager.TWENTYFOUR);
    pApp.text("DANGER LEVEL: " + level, positionX, positionY);
  }

  public void increaseDangerLevel() {
    this.level++;
  }

  public void resetDangerLevel() {
    this.level = ConstantManager.ONE;
  }

  public int getCurrDangerLevel() {
    return this.level;
  }

  public void update() {
    this.level = eSpawner.getSpawnModifier() + ConstantManager.ONE;
  }

}

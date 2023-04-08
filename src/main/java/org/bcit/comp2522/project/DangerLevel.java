package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * DangerLevel class that increases the enemy number on screen depending on score.
 */
public class DangerLevel extends UserInterface {
  private static final int DEFAULT_X_POS =
          (int) (Window.WINDOW_WIDTH / ConstantManager.WINDOW_WIDTH_RESIZE);
  private static final int DEFAULT_Y_POS =
          (int) (Window.WINDOW_HEIGHT * ConstantManager.WINDOW_HEIGHT_RESIZE);
  private int dangerLevel;
  private final PApplet papp;
  private final EnemySpawner espawner;

  /**
   * Constructor for DangerLevel.
   *
   * @param p PApplet object
   * @param spawner enemy spawner
   */
  public DangerLevel(PApplet p, EnemySpawner spawner) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.papp = p;
    this.espawner = spawner;
    this.dangerLevel = espawner.getSpawnModifier() + ConstantManager.ONE;
  }

  @Override
  protected void drawUserInterface() {
    papp.fill(ConstantManager.ENEMY_SPAWNER_UI, ConstantManager.ZERO, ConstantManager.ZERO);
    papp.textAlign(PConstants.CENTER);
    papp.textSize(ConstantManager.TEXT_SIZE);
    papp.text("DANGER LEVEL: " + dangerLevel, positionX, positionY);
  }

  public void increaseDangerLevel() {
    this.dangerLevel++;
  }

  public void resetDangerLevel() {
    this.dangerLevel = ConstantManager.ONE;
  }

  public int getCurrDangerLevel() {
    return this.dangerLevel;
  }

  public void update() {
    this.dangerLevel = espawner.getSpawnModifier() + ConstantManager.ONE;
  }

}

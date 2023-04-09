package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * DangerLevel class that increases the enemy number on screen depending on score.
 *
 * @author Ozan Yurtisigi, Benny Li, Brett Reader, Sukhraj Sidhu
 */
public class DangerLevel extends UserInterface {

  /**
   * Default x position.
   */
  private static final int DEFAULT_X_POS =
          (int) (Window.WINDOW_WIDTH / ConstantManager.WINDOW_WIDTH_RESIZE);

  /**
   * Default y position.
   */
  private static final int DEFAULT_Y_POS =
          (int) (Window.WINDOW_HEIGHT * ConstantManager.WINDOW_HEIGHT_RESIZE);

  /**
   * Current level.
   */
  private int level;

  /**
   * PApplet object.
   */
  private final PApplet papp;

  /**
   * Enemy spawner.
   */
  private final EnemySpawner espawner;

  /**
   * Constructor for DangerLevel.
   *
   * @param applet  PApplet object
   * @param spawner enemy spawner
   */
  public DangerLevel(final PApplet applet, final EnemySpawner spawner) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.papp = applet;
    this.espawner = spawner;
    this.level = espawner.getSpawnModifier() + ConstantManager.ONE;
  }

  @Override
  protected void drawUserInterface() {
    papp.fill(ConstantManager.ENEMY_SPAWNER_UI, ConstantManager.ZERO, ConstantManager.ZERO);
    papp.textAlign(PConstants.CENTER);
    papp.textSize(ConstantManager.TEXT_SIZE);
    papp.text("DANGER LEVEL: " + level, positionX, positionY);
  }

  /**
   * Resets the danger level.
   */
  public void resetDangerLevel() {
    this.level = ConstantManager.ONE;
  }

  /**
   * Updates the danger level.
   */
  public void update() {
    this.level = espawner.getSpawnModifier() + ConstantManager.ONE;
  }

}

package org.bcit.comp2522.project;

import processing.core.PApplet;

/**
 * Class for handling the User Interface elements.
 *
 * @author Brett Reader, Benny Li, Ozan Yurtisigi
 * @version 1.0
 */
public class UiHandler {

  /**
   * The HpDisplay object.
   */
  private final HpDisplay hpDisplay;

  /**
   * The DangerLevel object.
   */
  private final DangerLevel dangerLevel;

  /**
   * The Score object.
   */
  private final Score score;

  /**
   * Constructor for UiHandler.
   *
   * @param papp    PApplet object to draw the text
   * @param window  Window object to draw the text
   * @param state   GameState object to draw the text
   * @param spawner EnemySpawner object to draw the text
   */
  public UiHandler(PApplet papp, Window window, GameState state, EnemySpawner spawner) {
    this.hpDisplay = new HpDisplay(papp);
    this.dangerLevel = new DangerLevel(papp, spawner);
    this.score = new Score(window, state);
  }

  /**
   * Draws the User Interface.
   *
   * @param state GameState object to draw the text.
   */
  public void draw(GameState state) {
    hpDisplay.drawUserInterface();
    dangerLevel.drawUserInterface();
    score.updateGameState(state);
    score.drawUserInterface();
  }

  public HpDisplay getHpDisplay() {
    return this.hpDisplay;
  }

  public DangerLevel getDangerLevel() {
    return this.dangerLevel;
  }

  public Score getScore() {
    return this.score;
  }

}
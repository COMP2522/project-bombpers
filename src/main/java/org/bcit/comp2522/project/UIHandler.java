package org.bcit.comp2522.project;

import processing.core.PApplet;

public class UIHandler {

  private PApplet pApplet;
  private HPDisplay hpDisplay;
  private DangerLevel dangerLevel;
  private Score score;

  public UIHandler(PApplet pApp, Window window, GameState state, EnemySpawner spawner) {
    this.pApplet = pApp;
    this.hpDisplay = new HPDisplay(pApp);
    this.dangerLevel = new DangerLevel(pApp, spawner);
    this.score = new Score(window, state);
  }

  public void draw() {
    hpDisplay.drawUserInterface();
    dangerLevel.drawUserInterface();
    score.drawUserInterface();
  }

  public void drawScoreOnly() {
    score.drawUserInterface();
  }

  public HPDisplay getHPDisplay() {
    return this.hpDisplay;
  }

  public DangerLevel getDangerLevel() {
    return this.dangerLevel;
  }

  public Score getScore() {
    return this.score;
  }
}

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

  public void draw(GameState state) {
    hpDisplay.drawUserInterface();
    dangerLevel.drawUserInterface();
    score.updateGameState(state);
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

  public PApplet getpApplet() {
    return pApplet;
  }

  public void setpApplet(PApplet pApplet) {
    this.pApplet = pApplet;
  }

  public HPDisplay getHpDisplay() {
    return hpDisplay;
  }

  public void setHpDisplay(HPDisplay hpDisplay) {
    this.hpDisplay = hpDisplay;
  }

  public void setDangerLevel(DangerLevel dangerLevel) {
    this.dangerLevel = dangerLevel;
  }

  public void setScore(Score score) {
    this.score = score;
  }
}

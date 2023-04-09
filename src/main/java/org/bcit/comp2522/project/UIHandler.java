package org.bcit.comp2522.project;

import processing.core.PApplet;

public class UIHandler {

  private PApplet papplet;
  private HPDisplay hpDisplay;
  private DangerLevel dangerLevel;
  private Score score;

  public UIHandler(PApplet papp, Window window, GameState state, EnemySpawner spawner) {
    this.papplet = papp;
    this.hpDisplay = new HPDisplay(papp);
    this.dangerLevel = new DangerLevel(papp, spawner);
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
    return papplet;
  }

  public void setpApplet(PApplet papplet) {
    this.papplet = papplet;
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

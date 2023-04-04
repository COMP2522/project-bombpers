package org.bcit.comp2522.project;

import processing.core.PApplet;

public class UIHandler {

  private PApplet pApplet;
  private HPDisplay hpDisplay;
  private DangerLevel dangerLevel;

  public UIHandler(PApplet pApp, EnemySpawner spawner) {
    this.pApplet = pApp;
    this.hpDisplay = new HPDisplay(pApp);
    this.dangerLevel = new DangerLevel(pApp, spawner);
  }

  public void draw() {
    hpDisplay.drawUserInterface();
    dangerLevel.drawUserInterface();
  }

  public HPDisplay getHPDisplay() {
    return this.hpDisplay;
  }

  public DangerLevel getDangerLevel() {
    return this.dangerLevel;
  }
}

package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;

public class KillCounter {

  private static final int X_POS = 480;
  private static final int Y_POS = 480;
  private static final int TEXT_SIZE = 16;

  private static int numKills;
  private PFont font;

  public KillCounter(PApplet p) {
    numKills = 0;
    font = p.createFont("Arial", TEXT_SIZE, true);
  }

  public void draw(PApplet p) {
    p.textFont(font);
    p.fill(255, 0, 0);
    p.textAlign(PConstants.RIGHT, PConstants.BOTTOM);
    p.text("Kills:" + numKills, X_POS, Y_POS);
  }

  public static int getKills() {
    return numKills;
  }

  public void killPlus() {
    numKills++;
  }
}

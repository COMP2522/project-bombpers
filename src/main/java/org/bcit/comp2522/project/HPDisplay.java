package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class HPDisplay {
  private PApplet pApp;
  private Player player;
  private PVector position;
  private int health;
  private int xpos = 70;
  private int ypos = Window.WINDOW_HEIGHT - 50;

  public HPDisplay(PApplet p, CollectionManager collectionManager) {
    this.pApp = p;
    this.player = (Player) collectionManager.getPlayer();
    this.position = new PVector(xpos, ypos);
    this.health = player.getHealth();
  }

  public void draw() {
    pApp.textAlign(PConstants.RIGHT, PConstants.CENTER);
    pApp.fill(200, 200, 200);
    pApp.textSize(24);
    pApp.text("HP: " + health, position.x, position.y);
  }

  public void update() {
    this.health = player.getHealth();
  }

  public void damage(int dmg) {
    this.health = player.getHealth() - dmg;
    update();
  }
}

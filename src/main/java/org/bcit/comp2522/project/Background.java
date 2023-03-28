package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Background class.
 */
public class Background implements Drawable {
  private final PApplet parent;
  private final PImage backgroundImage;

  public Background(PApplet parent) {
    this.parent = parent;
    this.backgroundImage = loadBackgroundImage();
  }

  private PImage loadBackgroundImage() {
    return parent.loadImage("../img/bg3.png");
  }

  @Override
  public void draw() {
    parent.image(backgroundImage, 0, 0, parent.width, parent.height);
  }
}
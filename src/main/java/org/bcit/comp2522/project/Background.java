package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * Background class - is a child of the Drawable interface. Draws the background on the screen.
 */
public class Background implements Drawable {
  private final PApplet pattern;
  private final PImage backgroundImage;

  /**
   * Constructor for Background.
   *
   * @param pattern the pattern to draw on screen
   */
  public Background(PApplet pattern) {
    this.pattern = pattern;

    // Load the dirt texture image
    backgroundImage = pattern.loadImage("../img/bg3.png");

  }

  @Override
  public void draw() {
    // Draw the background image
    pattern.image(backgroundImage, 0, 0, pattern.width, pattern.height);

  }
}
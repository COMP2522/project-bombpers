package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class Background implements Drawable {
  private PApplet p;
  private PImage backgroundImage;

  public Background(PApplet p) {
    this.p = p;

    // Load the dirt texture image
    backgroundImage = p.loadImage("../img/bg3.png");

  }

  @Override
  public void draw() {

    // Draw the background image
    p.image(backgroundImage, 0, 0, p.width, p.height);

  }
}
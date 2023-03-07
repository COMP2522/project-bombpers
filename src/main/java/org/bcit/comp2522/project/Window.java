package org.bcit.comp2522.project;

import processing.core.PApplet;

public class Window extends PApplet {

  public void settings() {
    size(500, 500);
  }

  public void draw() {
    ellipse(mouseX, mouseY, 50, 50);
  }

  public void mousePressed() {
    background(64);
  }

  public static void main(String[] args) {
    String[] processingArgs = {"window"};
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }

}

package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

import static org.bcit.comp2522.project.ConstantManager.ZERO;

/**
 * Background class.
 */
public class Background implements Drawable {

  /**
   * The parent PApplet.
   */
  private final PApplet parent;

  /**
   * The background image.
   */
  private final PImage backgroundImage;

  /**
   * The offset of the background image.
   */
  private float offset;

  /**
   * The speed of the background image.
   */
  private final float speed;

  /**
   * The speed of the background image.
   */
  private static final float SPEED_OF_SCROLL = 0.5f;

  /**
   * The starting point of the background image.
   */
  private static final float Y_POS_STARTING_POINT = 0;

  /**
   * Constructor for the Background class.
   * @param parent the parent PApplet
   */
  public Background(PApplet parent) {
    this.parent = parent;
    this.backgroundImage = loadBackgroundImage();
    this.offset = ZERO;
    this.speed = SPEED_OF_SCROLL;
  }

  /**
   * Loads the background image.
   * @return the background image
   */
  private PImage loadBackgroundImage() {
    return parent.loadImage("../img/grass_bgv4.png");
  }

  /**
   * Draws the background image.
   */
  @Override
  public void draw() {
    float imageWidth = (float) (backgroundImage.width * parent.height) / backgroundImage.height;
    parent.image(backgroundImage, offset, Y_POS_STARTING_POINT, imageWidth, parent.height);
    parent.image(backgroundImage, offset + imageWidth, Y_POS_STARTING_POINT, imageWidth, parent.height);

    offset -= speed;
    if (offset <= -imageWidth) {
      offset = Y_POS_STARTING_POINT;
    }
  }
}
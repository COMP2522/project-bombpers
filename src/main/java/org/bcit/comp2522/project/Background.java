package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;

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
   * Constructor for the Background class.
   *
   * @param parent the parent PApplet
   */
  public Background(final PApplet parent) {
    this.parent = parent;
    this.backgroundImage = loadBackgroundImage();
    this.offset = ConstantManager.ZERO;
    this.speed = ConstantManager.SPEED_OF_SCROLL;
  }

  /**
   * Loads the background image.
   *
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
    final float imageWidth =
        (float) (backgroundImage.width * parent.height) / backgroundImage.height;
    parent.image(backgroundImage, offset, ConstantManager.ZERO, imageWidth, parent.height);
    parent.image(backgroundImage, offset + imageWidth, ConstantManager.ZERO,
            imageWidth, parent.height);

    offset -= speed;
    if (offset <= -imageWidth) {
      offset = ConstantManager.ZERO;
    }
  }
}
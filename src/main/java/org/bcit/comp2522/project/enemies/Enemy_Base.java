package org.bcit.comp2522.project.enemies;

import org.bcit.comp2522.project.Sprite;
import org.bcit.comp2522.project.Window;
import processing.core.PVector;

import java.awt.*;

public class Enemy_Base extends Sprite {
  protected static final String BASE_NAME_ENEM = "Modus Ponens";
  protected static final int BASE_HP_ENEM = 4;
  protected static final int BASE_DMG_ENEM = 2;
  protected static final float BASE_SIZE_ENEM = 10;
  protected static final float BASE_SPEED_ENEM = 1.2f;
  protected static final Color BASE_COLOR_ENEM = new Color(255, 0, 0);
  protected String name;

  // Makes easier to build constructors for subclasses
  public Enemy_Base(){
    this.name = BASE_NAME_ENEM;
    this.health = BASE_HP_ENEM;
    this.damage = BASE_DMG_ENEM;
    this.size = BASE_SIZE_ENEM;
    this.speed = BASE_SPEED_ENEM;
    this.color = BASE_COLOR_ENEM;
    // TODO: spawn somewhere near player
    this.position = new PVector(0, 0);
    this.direction = new PVector(0, 0);
  }

//  public Enemy_Base(PVector position,
//                    PVector direction,
//                    float size,
//                    float speed,
//                    Color color,
//                    Window window,
//                    int health,
//                    int damage,
//                    String name) {
//    super(position,
//          direction,
//          size,
//          speed,
//          color,
//          window,
//          health,
//          damage);
//    this.name = name;
//  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param one
   * @param two
   */
  @Override
  public void collide(Sprite one, Sprite two) {
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void compareTo() {
    //TODO: Implement this method
  }

  /**
   *
   */
  @Override
  public void draw() {
    window.noStroke();  // disable stroke
    window.fill(color.getRGB());  // set fill color
    window.ellipse(position.x, position.y, size, size);  // draw a circle at the enemy position
  }


  /**
   *
   */
  @Override
  public void move() {
    //TODO: Implement this method
  }
}

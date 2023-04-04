package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.core.PConstants;

/**
 * InputHandler class - handles the input from the user.
 */
public class InputHandler {

  /**
   * The instance of the InputHandler.
   */
  private static InputHandler instance;
  /**
   * These booleans are used to check if the key is pressed or not.
   */
  private boolean isLeftPressed = false;
  private boolean isRightPressed = false;
  private boolean isUpPressed = false;
  private boolean isDownPressed = false;
  private final Window window;
  private static final int CHAR_RESIZE_WIDTH = 2;
  private static final int CHAR_X_POS_MOVE = 31;
  private static final int CHAR_Y_POS_MOVE = 20;

  /**
   * The collection manager that will be used to access the collection of sprites.
   */
  private final CollectionManager collectionManager;

  /**
   * getInstance method - returns the instance of the InputHandler.
   *
   * @param collectionManager the collection manager that will be used to access the collection of sprites.
   * @return the instance of the InputHandler.
   */
  public static InputHandler getInstance(CollectionManager collectionManager, Window window) {
    if (instance == null) {
      instance = new InputHandler(collectionManager, window);
    }
    return instance;
  }

  /**
   * InputHandler constructor - creates a new InputHandler object.
   *
   * @param collectionManager the collection manager that will be used to access the collection of sprites.
   */
  private InputHandler(CollectionManager collectionManager, Window window) {
    this.collectionManager = collectionManager;
    this.window = window;
  }


  /**
   * keyPressed method - is called when a key is pressed.
   *
   * @param event the key event.
   */
  public void keyPressed(KeyEvent event) {
    // Get the key code of the key that was pressed
    int keyCode = event.getKeyCode();

    // Check if the key code is the same as any of the switch cases and do the corresponding action
    switch (keyCode) {
      case PConstants.LEFT -> isLeftPressed = true;
      case PConstants.RIGHT -> isRightPressed = true;
      case PConstants.UP -> isUpPressed = true;
      case PConstants.DOWN -> isDownPressed = true;
      default -> System.out.println(); // switch needed a default case, it does nothing.
    }
  }

  /**
   * keyReleased method - is called when a key is released.
   *
   * @param event the key event.
   */
  public void keyReleased(KeyEvent event) {
    // Get the key code of the key that was pressed
    int keyCode = event.getKeyCode();

    // Check if the key code is the same as any of the switch cases and do the corresponding action
    switch (keyCode) {
      case PConstants.LEFT -> isLeftPressed = false;
      case PConstants.RIGHT -> isRightPressed = false;
      case PConstants.UP -> isUpPressed = false;
      case PConstants.DOWN -> isDownPressed = false;
      default -> System.out.println(); // switch needed a default case, it does nothing.
    }
  }

  /**
   * updatePlayerDirection method - updates the player's direction based on the key pressed.
   *
   * @return the new direction of the player.
   */
  public PVector updatePlayerDirection() {
    int directionX = 0;
    int directionY = 0;

    // Check if the key is pressed and update the direction accordingly
    if (isLeftPressed) {
      directionX--;
    }
    if (isRightPressed) {
      directionX++;
    }
    if (isUpPressed) {
      directionY--;
    }
    if (isDownPressed) {
      directionY++;
    }

    // If the direction is not 0,0, set the player's direction to the new direction
    if (directionX != 0 || directionY != 0) {
      collectionManager.getPlayer().setDirection(new PVector(directionX, directionY));
    } else {
      collectionManager.getPlayer().setDirection(new PVector(0, 0));
    }

    return new PVector(directionX, directionY);
  }

  public void mousePressed(PImage image) {
    if (window.stateOfGame == GameState.STARTGAME && window.mouseButton == PConstants.LEFT) {
      PVector mousePosition = new PVector(window.mouseX, window.mouseY);
      PVector playerPosition = collectionManager.getPlayer().getPosition();
      PVector direction = PVector.sub(mousePosition, playerPosition).normalize();

      PVector projectileStartPosition = new PVector(
          playerPosition.x + CHAR_X_POS_MOVE - Projectile.PROJECTILE_SIZE / CHAR_RESIZE_WIDTH,
          playerPosition.y + CHAR_Y_POS_MOVE - Projectile.PROJECTILE_SIZE / CHAR_RESIZE_WIDTH
      );

      Projectile projectile = new Projectile(window, projectileStartPosition, direction, image); //Pimage

      collectionManager.getProjectiles().add(projectile);
      collectionManager.getSprites().add(projectile);
    }
  }
}

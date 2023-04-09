package org.bcit.comp2522.project;

import java.util.logging.Logger;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * InputHandler class - handles the input from the user.
 *
 * @author Ozan Yurtisigi, Sukhraj Sidhu, Brett Reader
 * @version 1.0
 */
public final class InputHandler {

  /**
   * The instance of the InputHandler.
   */
  private static InputHandler instance;
  /**
   * These booleans are used to check if the key is pressed or not.
   */
  private boolean isLeftPressed;
  private boolean isRightPressed;
  private boolean isUpPressed;
  private boolean isDownPressed;
  private final Window window;

  /**
   * The collection manager that will be used to access the collection of sprites.
   */
  private final CollectionManager collectionManager;

  /**
   * getInstance method - returns the instance of the InputHandler.
   *
   * @param collectionManager the collection manager that will be used to access
   *                          the collection of sprites.
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
   * @param collectionManager the collection manager that will be used to access
   *                          the collection of sprites.
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
    final int keyCode = event.getKeyCode();

    // Check if the key code is the same as any of the switch cases and do the corresponding action
    switch (keyCode) {
      case PConstants.LEFT -> isLeftPressed = true;
      case PConstants.RIGHT -> isRightPressed = true;
      case PConstants.UP -> isUpPressed = true;
      case PConstants.DOWN -> isDownPressed = true;
      default -> Logger.getLogger("InputHandler").info("Key pressed: " + keyCode);
    }
  }

  /**
   * keyReleased method - is called when a key is released.
   *
   * @param event the key event.
   */
  public void keyReleased(KeyEvent event) {
    // Get the key code of the key that was pressed
    final int keyCode = event.getKeyCode();

    // Check if the key code is the same as any of the switch cases and do the corresponding action
    switch (keyCode) {
      case PConstants.LEFT -> isLeftPressed = false;
      case PConstants.RIGHT -> isRightPressed = false;
      case PConstants.UP -> isUpPressed = false;
      case PConstants.DOWN -> isDownPressed = false;
      default -> Logger.getLogger("InputHandler").info("Key released: " + keyCode);
    }
  }

  /**
   * updatePlayerDirection method - updates the player's direction based on the key pressed.
   *
   * @return the new direction of the player.
   */
  public PVector updatePlayerDirection() {
    int directionX = ConstantManager.ZERO;
    int directionY = ConstantManager.ZERO;

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
    collectionManager.getPlayer().setDirection(new PVector(directionX, directionY));

    return new PVector(directionX, directionY);
  }

  /**
   * A method that shoots a bullet at the direction where the mouse is.
   *
   * @param image sprite image of the projectile
   */
  public void mousePressed(PImage image) {
    if (window.stateOfGame == GameState.STARTGAME && window.mouseButton == PConstants.LEFT) {
      final PVector mousePosition = new PVector(window.mouseX, window.mouseY);
      final PVector playerPosition = collectionManager.getPlayer().getPosition();
      final PVector direction = PVector.sub(mousePosition, playerPosition).normalize();

      final PVector bulletStartPosition = new PVector(
          playerPosition.x + ConstantManager.CHAR_X_POS_MOVE
              - Projectile.PROJECTILE_SIZE / ConstantManager.CHAR_RESIZE_WIDTH,
          playerPosition.y + ConstantManager.CHAR_Y_POS_MOVE
              - Projectile.PROJECTILE_SIZE / ConstantManager.CHAR_RESIZE_WIDTH
      );

      final Projectile projectile = new Projectile(
          window, bulletStartPosition, direction, image); //Pimage

      collectionManager.getProjectiles().add(projectile);
      collectionManager.getSprites().add(projectile);
    }
  }

  /**
   * A method that reads the key input, if it is 'P', it pauses the game.
   *
   * @param event key input that is being read
   */
  public void pauseGameOnPauseKeyPressed(KeyEvent event) {
    final char key = event.getKey();
    if (key == 'p' || key == 'P') {
      window.stateOfGame = GameState.PAUSE;
    }
  }
}

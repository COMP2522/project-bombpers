package org.bcit.comp2522.project;

import java.util.logging.Logger;
import processing.core.PImage;

/**
 * Class for Menu of the game.
 *
 * @author Ozan Yurtisigi, Brett Reader, Amarjot Sangha, Benny Li
 * @version 1.0
 */
public class Menu extends UserInterface {

  /**
   * The message to be displayed on the UI.
   */
  private String message;

  /**
   * The window of the game that the menu is displayed on.
   */
  private final Window window;

  /**
   * The main background of the game.
   */
  private final PImage mainBackground;

  /**
   * The current state of the game.
   */
  private GameState currState;

  /**
   * Constructor for Menu.
   *
   * @param posX    the x-position of the menu
   * @param posY    the y-position of the menu
   * @param message the message to be displayed
   * @param window  the window of the game that the menu is displayed on
   */
  public Menu(int posX, int posY, String message, Window window) {
    super(posX, posY);
    this.message = message;
    this.window = window;
    this.mainBackground = window.loadImage("../img/mainBackground.png");
  }

  /**
   * Sets the message to be displayed.
   *
   * @param state the state of the game
   */
  public void updateGameState(GameState state) {
    this.currState = state;
  }

  /**
   * Draws the user interface.
   */
  @Override
  protected void drawUserInterface() {
    displayMenu(currState);
  }

  /**
   * Displays the menu.
   *
   * @param state the state of the game
   */
  public void displayMenu(GameState state) {
    final int startButtonTextXposition =
        this.window.width / ConstantManager.START_TEXT_X_POS_RESIZE_FACTOR;

    final int startButtonTextYposition =
        this.window.width / ConstantManager.TEXT_RESIZE_FACTOR - ConstantManager.START_Y_OFFSET;

    final int endButtonTextXposition =
        (int) (Window.WINDOW_WIDTH * ConstantManager.MENU_WIDTH_RESIZE);

    final int endButtonTextYposition =
        (int) (Window.WINDOW_HEIGHT * ConstantManager.MENU_HEIGHT_RESIZE);

    final int pauseButtonTextXposition =
        (int) (Window.WINDOW_WIDTH * ConstantManager.MENU_WIDTH_RESIZE);

    final int pauseButtonTextYposition =
        (int) (Window.WINDOW_HEIGHT * ConstantManager.MENU_HEIGHT_RESIZE);

    window.image(
        mainBackground,
        ConstantManager.IMAGE_X_POSITION,
        ConstantManager.IMAGE_Y_POSITION,
        window.width, window.height
    );

    createTitle();
    button();

    switch (state) {
      case STARTMENU -> window.text("Start", startButtonTextXposition, startButtonTextYposition);
      case ENDGAME -> window.text("Restart", endButtonTextXposition, endButtonTextYposition);
      case PAUSE -> window.text("Continue", pauseButtonTextXposition, pauseButtonTextYposition);
      default -> Logger.getLogger("Menu").warning("Invalid state");
    }
  }

  /**
   * Creates the title text.
   */
  private void createTitle() {
    setMessage(message);
    final String message = getMessage();
    window.textSize(ConstantManager.TITLE_SIZE);
    window.fill(
        ConstantManager.TITLE_RED_VALUE,
        ConstantManager.TITLE_BLUE_VALUE,
        ConstantManager.TITLE_GREEN_VALUE
    );
    window.text(message, getPositionX(), getPositionY());
  }

  /**
   * Creates the button that will be displayed on menus.
   */
  private void button() {
    window.fill(ConstantManager.BUTTON_BACKGROUND_COLOR_VALUE);

    window.rect(
        ConstantManager.BUTTON_X_POSITION,
        ConstantManager.BUTTON_Y_POSITION,
        ConstantManager.BUTTON_WIDTH,
        ConstantManager.BUTTON_HEIGHT
    );

    window.textSize(ConstantManager.BUTTON_TEXT_SIZE);

    window.fill(
        ConstantManager.BUTTON_TEXT_RED_VALUE,
        ConstantManager.BUTTON_TEXT_BLUE_VALUE,
        ConstantManager.BUTTON_TEXT_GREEN_VALUE
    );
  }

  /**
   * returns a string that is the message that will be displayed.
   *
   * @return message a string containing the text to be displayed
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message variable to the desired text.
   *
   * @param message a string that contains the text that we want to display
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
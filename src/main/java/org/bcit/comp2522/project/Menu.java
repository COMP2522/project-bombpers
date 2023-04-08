package org.bcit.comp2522.project;

import processing.core.PImage;

/**
 * Class for Menu of the game.
 */
public class Menu extends UserInterface {

  //  private message to be displayed
  private String message;

  //  the window of the game that the menu is displayed on
  private final Window window;

  //Size of the title text
  private static final int TITLE_SIZE = 80;

  //Size of the button text
  private static final int BUTTON_TEXT_SIZE = 50;

  //Red value to be used for the color of the title
  private static final int TITLE_RED_VALUE = 255;

  //Blue value to be used for the color of the title
  private static final int TITLE_BLUE_VALUE = 140;

  //Green value to be used for the color of the title
  private static final int TITLE_GREEN_VALUE = 0;

  //Background color of the button
  private static final int BUTTON_BACKGROUND_COLOR_VALUE = 153;

  //X position of the button
  private static final int BUTTON_X_POSITION = 120;

  //Y position of the button
  private static final int BUTTON_Y_POSITION = 190;

  //width of the button
  private static final int BUTTON_WIDTH = 200;

  //height of the button
  private static final int BUTTON_HEIGHT = 45;

  // red value of the button text color
  private static final int BUTTON_TEXT_RED_VALUE  = 200;

  // blue value of the button text color
  private static final int BUTTON_TEXT_BLUE_VALUE  = 0;

  // green value of the button text color
  private static final int BUTTON_TEXT_GREEN_VALUE  = 0;

  //Offset the y value of the text so that it is centered inside the button
  private static final int START_Y_OFFSET = 20;

  //A resize variable to
  private static final int TEXT_RESIZE_FACTOR = 2;
  private static final int START_TEXT_X_POS_RESIZE_FACTOR = 3;
  private final PImage MainBG;
  private GameState currState;

  private static final int IMAGE_X_POSITION = 0;
  private static final int IMAGE_Y_POSITION = 0;

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
    this.MainBG = window.loadImage("../img/MainBackGround2.png");
  }

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
    int START_BUTTON_TEXT_X_POSITION = this.window.width / START_TEXT_X_POS_RESIZE_FACTOR;
    int START_BUTTON_TEXT_Y_POSITION = this.window.width / TEXT_RESIZE_FACTOR - START_Y_OFFSET;
    int END_BUTTON_TEXT_X_POSITION = (int) (Window.WINDOW_WIDTH * 0.44f);
    int END_BUTTON_TEXT_Y_POSITION = (int) (Window.WINDOW_HEIGHT * 0.46f);
    int PAUSE_BUTTON_TEXT_X_POSITION = (int) (Window.WINDOW_WIDTH * 0.44f);
    int PAUSE_BUTTON_TEXT_Y_POSITION = (int) (Window.WINDOW_HEIGHT * 0.46f);
    window.image(MainBG, IMAGE_X_POSITION, IMAGE_Y_POSITION, window.width, window.height);

    createTitle();
    button();

    switch (state) {
      case STARTMENU ->
              window.text("Start", START_BUTTON_TEXT_X_POSITION, START_BUTTON_TEXT_Y_POSITION);
      case ENDGAME ->
              window.text("Restart", END_BUTTON_TEXT_X_POSITION, END_BUTTON_TEXT_Y_POSITION);
      case PAUSE ->
              window.text("Continue", PAUSE_BUTTON_TEXT_X_POSITION, PAUSE_BUTTON_TEXT_Y_POSITION);
    }
  }

  /**
   * Creates the title text.
   */
  private void createTitle() {
    setMessage(message);
    String message = getMessage();
    window.textSize(TITLE_SIZE);
    window.fill(TITLE_RED_VALUE, TITLE_BLUE_VALUE, TITLE_GREEN_VALUE);
    window.text(message, getPositionX(), getPositionY());
  }

  /**
   * Creates the button that will be displayed on menus.
   */
  private void button() {
    window.fill(BUTTON_BACKGROUND_COLOR_VALUE);
    window.rect(BUTTON_X_POSITION, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
    window.textSize(BUTTON_TEXT_SIZE);
    window.fill(BUTTON_TEXT_RED_VALUE, BUTTON_TEXT_BLUE_VALUE, BUTTON_TEXT_GREEN_VALUE);
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
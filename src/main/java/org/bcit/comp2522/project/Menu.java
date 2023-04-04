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
  private final int TITLE_SIZE = 90;
  private final int TEXT_SIZE = 50;
  private final int BLACK = 0;
  private final int TITLE_RED_VALUE = 0;
  private final int TITLE_BLUE_VALUE = 408;
  private final int TITLE_GREEN_VALUE = 612;
  private final int BUTTON_BACKGROUND_COLOR_VALUE = 153;
  private final int BUTTON_X_POSITION = 120;
  private final int BUTTON_Y_POSITION = 200;
  private final int BUTTON_WIDTH = 200;
  private final int BUTTON_HEIGHT = 45;
  private final int BUTTON_TEXT_RED_VALUE  = 200;
  private final int BUTTON_TEXT_BLUE_VALUE  = 0;
  private final int BUTTON_TEXT_GREEN_VALUE  = 0;
  private final int START_X_OFFSET = 12;

  private final int PAUSE_X_OFFSET = 70;
  private final int END_X_OFFSET = 55;
  private final int TEXT_RESIZE_FACTOR = 2;
  private final int START_TEXT_X_POS_RESIZE_FACTOR = 3;
  private final PImage MainBG;

  /**
   * Constructor for Menu.
   *
   * @param posX the x-position of the menu
   * @param posY the y-position of the menu
   * @param message the message to be displayed
   * @param window the window of the game that the menu is displayed on
   */
  public Menu(int posX, int posY, String message, Window window) {
    super(posX, posY);
    this.message = message;
    this.window = window;
    this.MainBG = window.loadImage("../img/MainBackGround2.png");
  }


  @Override
  protected void drawUserInterface(GameState stateOfGame) {
    window.background(BLACK);
    displayMenu(stateOfGame);

  }

  /**
   * Displays the menu.
   *
   * @param state the state of the game
   */
  public void displayMenu(GameState state) {
    int START_BUTTON_TEXT_X_POSITION = this.window.width / START_TEXT_X_POS_RESIZE_FACTOR;
    int START_BUTTON_TEXT_Y_POSITION = this.window.width / TEXT_RESIZE_FACTOR - START_X_OFFSET;
    int PAUSE_BUTTON_TEXT_X_POSITION = this.window.width / TEXT_RESIZE_FACTOR + PAUSE_X_OFFSET;
    int PAUSE_BUTTON_TEXT_Y_POSITION = this.window.width / TEXT_RESIZE_FACTOR;
    int END_BUTTON_TEXT_X_POSITION = this.window.width / TEXT_RESIZE_FACTOR + END_X_OFFSET;
    int END_BUTTON_TEXT_Y_POSITION = this.window.width / TEXT_RESIZE_FACTOR;
    window.image(MainBG, 0, 0,window.width,window.height);

    createTitle();
    button();

    switch (state) {
      case STARTMENU -> window.text("Start", START_BUTTON_TEXT_X_POSITION, START_BUTTON_TEXT_Y_POSITION);
      case ENDGAME -> window.text("Restart", END_BUTTON_TEXT_X_POSITION, END_BUTTON_TEXT_Y_POSITION);
      case PAUSE -> window.text("Continue", PAUSE_BUTTON_TEXT_X_POSITION, PAUSE_BUTTON_TEXT_Y_POSITION);
    }
  }

  private void createTitle() {
    setMessage(message);
    String message = getMessage();
    window.textSize(TITLE_SIZE);
    window.fill(TITLE_RED_VALUE, TITLE_BLUE_VALUE, TITLE_GREEN_VALUE);
    window.text(message, getPositionX(), getPositionY());
  }

  private void button() {
    window.fill(BUTTON_BACKGROUND_COLOR_VALUE);
    window.rect(BUTTON_X_POSITION, BUTTON_Y_POSITION, BUTTON_WIDTH, BUTTON_HEIGHT);
    window.textSize(TEXT_SIZE);
    window.fill(BUTTON_TEXT_RED_VALUE,BUTTON_TEXT_BLUE_VALUE,BUTTON_TEXT_GREEN_VALUE);
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
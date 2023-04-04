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
  private final PImage MainBG;

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


  @Override
  protected void drawUserInterface() {
    // Implement drawing of the menu elements here
  }

  /**
   * Displays the menu.
   *
   * @param state     the state of the game
   * @param titleSize the size of the title
   */
  public void displayMenu(GameState state, int titleSize) {
    window.image(MainBG, 0, 0,window.width,window.height);
    modifyMessage(titleSize);
    button();

    switch (state) {
      case STARTMENU -> window.text("Start", 160, 245);
      case ENDGAME -> window.text("Restart", 190, 245);
      case PAUSE -> window.text("Continue", 320, 252);
    }
  }

  private void modifyMessage(int textSize) {
    setMessage(message);
    String message = getMessage();
    window.textSize(textSize);
    window.fill(0, 408, 612);
    window.text(message, getPositionX(), getPositionY());
  }

  private void button() {
    window.fill(153);
    window.rect(120, 200, 200, 55);
    window.textSize(50);
    window.fill(200, 0, 0);
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
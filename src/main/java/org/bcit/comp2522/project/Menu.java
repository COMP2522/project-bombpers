package org.bcit.comp2522.project;

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
    createTitle();
    button();
    switch (state) {
      case STARTMENU -> window.text("Start", 166, 245);
      case ENDGAME -> window.text("Restart", 300, 255);
      case PAUSE -> window.text("Continue", 320, 252);
    }
  }

  private void createTitle() {
    setMessage(message);
    String message = getMessage();
    window.textSize(TITLE_SIZE);
    window.fill(0, 408, 612);
    window.text(message, getPositionX(), getPositionY());
  }

  private void button() {
    window.fill(153);
    window.rect(120, 200, 200, 55);
    window.textSize(TEXT_SIZE);
    window.fill(200, 0, 0);
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
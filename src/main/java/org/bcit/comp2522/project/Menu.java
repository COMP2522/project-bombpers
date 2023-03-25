package org.bcit.comp2522.project;

/**
 * Menu class - is a UI element that displays the title of the game and the start button.
 */
public class Menu extends UserInterface {
  private String message;
  private final Window window;

  /**
   * Constructor for Menu.
   *
   * @param posX    x position of the menu
   * @param posY    y position of the menu
   * @param message message to be displayed
   * @param wind    window to be displayed on
   */
  public Menu(int posX, int posY, String message, Window wind) {
    super(posX, posY);
    this.message = message;
    this.window = wind;
  }

  /**
   * Displays the menu.
   *
   * @param state     state of the game
   * @param titleSize size of the title
   */
  public void displayMenu(int state, int titleSize) {
    window.background(0);
    if (state == 0) {
      //welcome message
      modifyMessage(titleSize);
      //button
      button();
      window.text("Start", 160, 245);
    } else if (state == 2) {
      //resets background so screen "updates"
      //title
      modifyMessage(titleSize);
      //button
      button();
      window.text("Restart", 140, 245);
    } else if (state == 3) {
      //resets background so screen "updates"
      //title
      modifyMessage(titleSize);
      //button
      button();
      window.text("Continue", 120, 245);
    } else if (state == 4) {
      //resets background so screen "updates"
      //title
      modifyMessage(titleSize);
      //button
      button();
      window.text("Speedy", 140, 240);
      button2();
      window.text("Tank", 140, 340);
    }

  }

  /**
   * Modifies the message.
   *
   * @param textSize size of the text
   */
  public void modifyMessage(int textSize) {
    setMessage(message);
    String message = getMessage();
    window.textSize(textSize);
    window.fill(0, 408, 612);
    window.text(message, getPositionX(), getPositionY());

  }

  /**
   * Creates the first type of button.
   */
  public void button() {
    window.fill(153);
    window.rect(120, 200, 200, 55);
    window.textSize(50);
    window.fill(200, 0, 0);
  }

  /**
   * Creates the second type of button.
   */
  public void button2() {
    window.fill(100);
    //xCord,yCord,width,height
    window.rect(120, 300, 200, 55);
    window.textSize(50);
    window.fill(200, 0, 0);
  }

  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message message to be set
   */
  public void setMessage(String message) {

    this.message = message;

  }
}

package org.bcit.comp2522.project;

public class Menu extends UserInterface {
  private String message;
  private final Window window;

  public Menu(int posX, int posY, String message, Window window) {
    super(posX, posY);
    this.message = message;
    this.window = window;
  }

  @Override
  protected void drawUserInterface() {
    // Implement drawing of the menu elements here
  }

  public void displayMenu(int state, int titleSize) {
    window.background(0);
    modifyMessage(titleSize);
    button();

    switch (state) {
      case 0:
        window.text("Start", 160, 245);
        break;
      case 2:
        window.text("Restart", 140, 245);
        break;
      case 3:
        window.text("Continue", 120, 245);
        break;
      case 4:
        window.text("Begin!", 140, 240);
        break;
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
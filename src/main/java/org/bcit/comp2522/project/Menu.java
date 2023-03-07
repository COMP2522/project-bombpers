package org.bcit.comp2522.project;

public class Menu extends UI {
  private String message;

  public Menu(int xPos, int yPos, String message) {
    super(xPos, yPos);
    this.message = message;
  }

  public void displayMenu() {
    System.out.println("Welcome to the game!");
    System.out.println("1. Start Game");
    System.out.println("2. Exit");
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

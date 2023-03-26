package org.bcit.comp2522.project;

public class Score extends UserInterface {
  private int highScore;
  private int currentScore;
  private final Window window;

  public Score(int positionX, int positionY, int score, Window window) {
    super(positionX, positionY);
    currentScore = score;
    this.window = window;
  }

  public int getHighScore() {
    return highScore;
  }

  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }

  public int getCurrentScore() {
    return currentScore;
  }

  public void setCurrentScore(int currentScore) {
    this.currentScore = currentScore;
  }

  @Override
  protected void drawUserInterface() {
    // Implement drawing of the score elements here
  }

  public void displayScore(int state) {
    switch (state) {
      case 1:
        displayInGameScore();
        break;
      default:
        displayEndGameScore();
        break;
    }
  }

  private void displayInGameScore() {
    window.textSize(30);
    window.fill(255, 255, 0);
    window.text("Score: " + currentScore, getPositionX(), getPositionY());
  }

  private void displayEndGameScore() {
    window.textSize(60);
    window.fill(255, 255, 0);
    window.text("Current Score: " + getCurrentScore(), getPositionX() - 130, getPositionY() + 300);
    window.text("High Score: " + getHighScore(), getPositionX() - 100, getPositionY() + 400);
  }
}

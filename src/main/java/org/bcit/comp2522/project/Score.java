package org.bcit.comp2522.project;

/**
 * Score class - is a child of the UI class.
 */
public class Score extends UserInterface {
  private int highScore;
  private int currentScore;
  private final Window window;

  /**
   * Score constructor.
   *
   * @param positionX x position of the score
   * @param positionY y position of the score
   * @param score     current score
   * @param window    window the score is in
   */
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

  /**
   * Displays the score.
   *
   * @param state - the state of the game
   */
  public void displayScore(int state) {
    if (state == 1) {
      window.textSize(30);
      window.fill(255, 255, 0);
      window.text("Score: " + currentScore, getPositionX(), getPositionY());
    } else {
      window.textSize(60);
      window.fill(255, 255, 0);
      window.text("Current Score: " + getCurrentScore(), getPositionX() - 130, getPositionY() + 300);
      window.text("High Score: " + getHighScore(), getPositionX() - 100, getPositionY() + 400);
    }
  }

  public void setCurrentScore(int currentScore) {
    this.currentScore = currentScore;
  }
}

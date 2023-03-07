package org.bcit.comp2522.project;

public class Score extends UI {
  private int highScore;
  private int currentScore;

  public Score(int xPos, int yPos) {
    super(xPos, yPos);
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
}

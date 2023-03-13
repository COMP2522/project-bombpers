package org.bcit.comp2522.project;

public class Score extends UI {
  private int highScore;
  private int currentScore;
private Window w;
  public Score(int xPos, int yPos, int score, Window window) {
    super(xPos, yPos);
    currentScore = score;
    w = window;
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
  public void displayScore(int state){
    if (state == 1) {
      w.textSize(30);
      w.fill(255, 255, 0);
      w.text("Score: " + currentScore, getXPos(), getYPos());
    }
    else{
      w.textSize(60);
      w.fill(255, 255, 0);
      w.text("Current Score: " + getCurrentScore(), getXPos()-130, getYPos() + 300);
      w.text("High Score: " + getHighScore(), getXPos()-100, getYPos() + 400);
    }
  }

  public void setCurrentScore(int currentScore) {
    this.currentScore = currentScore;
  }
}

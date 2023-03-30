package org.bcit.comp2522.project;

/**
 * Class for Score of the game set, gets, and displays score.
 */
public class Score extends UserInterface {
  /**
   * The high score of the game.
   */
  private int highScore;
  /**
   * The current score of the game.
   */
  private int currentScore;
  /**
   * The window of the game that the score is displayed on.
   */
  private final Window window;

  /**
   * Constructor for Score.
   *
   * @param positionX the x-position of the score
   * @param positionY the y-position of the score
   * @param window the window of the game that the score is displayed on
   */
  public Score(int positionX, int positionY, Window window) {
    super(positionX, positionY);
    this.window = window;
  }

  /**
   * Gets the high score of the game.
   *
   * @return the high score of the game
   */
  public int getHighScore() {
    return highScore;
  }

  /**
   * Sets the high score of the game.
   *
   * @param highScore the high score of the game
   */
  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }

  /**
   * Gets the current score of the game.
   *
   * @return the current score of the game
   */
  public int getCurrentScore() {
    return currentScore;
  }

  /**
   * Sets the current score of the game.
   *
   * @param currentScore the current score of the game
   */
  public void setCurrentScore(int currentScore) {
    this.currentScore = currentScore;
  }

  @Override
  protected void drawUserInterface() {
    // Implement drawing of the score elements here
  }

  /**
   * Displays the score of the game.
   *
   * @param state the state of the game
   */
  public void displayScore(GameState state) {
    //Depending on the state of the game, call the appropriate method to display the score

    if (state == GameState.STARTGAME) {

        displayInGameScore();
    } else {
        displayMenuGameScore();
    }
  }

  /**
   * Displays the score of the game while the game is in progress.
   */
  private void displayInGameScore() {
    window.textSize(30);
    window.fill(255, 255, 0);
    window.text("Score: " + currentScore, getPositionX(), getPositionY());
  }
  /**
   * Displays the score of the game while the game is in a menu.
   */
  private void displayMenuGameScore() {
    window.textSize(60);
    window.fill(255, 255, 0);
    //Set x and y in here make current x value larger
    window.text("Current Score: " + getCurrentScore(), getPositionX()+100, getPositionY());
    window.text("High Score: " + getHighScore(), getPositionX()+100, getPositionY() +50);
  }
}
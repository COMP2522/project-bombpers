package org.bcit.comp2522.project;

import java.util.logging.Logger;

/**
 * Class for Score of the game set, gets, and displays score.
 *
 * @author Brett Reader, Benny Li, Ozan Yurtisigi, Armarjot Singha
 * @version 1.0
 */
public class Score extends UserInterface {

  /**
   * The default x-position of the score.
   */
  private static final int DEFAULT_X_POS =
      (int) (Window.WINDOW_WIDTH / ConstantManager.WINDOW_WIDTH_RESIZE);

  /**
   * The default y-position of the score.
   */
  private static final int DEFAULT_Y_POS = (int) (Window.WINDOW_HEIGHT * ConstantManager.POINTONE);

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
   * The text size of the score.
   */
  private static final int MENU_SCORE_TEXT_SIZE = 60;

  /**
   * The current state of the game.
   */
  private GameState currState;

  /**
   * Constructor for Score.
   *
   * @param window the window of the game that the score is displayed on
   */
  public Score(Window window, GameState state) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.window = window;
    this.currState = state;
  }

  /**
   * Updates the game state.
   *
   * @param state the state of the game
   */
  public void updateGameState(GameState state) {
    this.currState = state;
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
    displayScore(this.currState);
  }

  /**
   * Displays the score of the game.
   *
   * @param state the state of the game
   */
  public void displayScore(GameState state) {
    int redColorValue = 255;
    int greenColorValue = 255;
    int blueColorValue = 0;
    window.fill(redColorValue, greenColorValue, blueColorValue);
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
    final int gameScoreTextSize = MENU_SCORE_TEXT_SIZE / 2;
    window.textSize(gameScoreTextSize);
    window.text("Score: " + currentScore, getPositionX(), getPositionY());
  }

  /**
   * Displays the score of the game while the game is in a menu.
   */
  private void displayMenuGameScore() {
    window.textSize(MENU_SCORE_TEXT_SIZE);
    //Set x and y in here make current x value larger
    window.text("Current Score:" + getCurrentScore(), getPositionX(), getPositionY());
    window.text("\nHigh Score: " + getHighScore(), getPositionX(), getPositionY());
  }

  /**
   * Increments the score of the game based on the enemy killed.
   *
   * @param enemyType the type of enemy killed
   */
  public void incrementScore(int enemyType) {
    switch (enemyType) {
      case EnemyConfig.ENEMY_STANDARD_TYPE -> currentScore++;
      case EnemyConfig.ENEMY_FAST_TYPE -> currentScore = currentScore + EnemyConfig.ENEMY_FAST_TYPE;
      case EnemyConfig.ENEMY_SLOW_TYPE -> currentScore = currentScore + EnemyConfig.ENEMY_SLOW_TYPE;
      default -> Logger.getLogger(Score.class.getName()).warning("Invalid enemy type");
    }
    setCurrentScore(currentScore);
  }

  /**
   * Resets the score of the game.
   */
  public void resetScore() {
    int startingScore = 0;
    setCurrentScore(startingScore);
  }
}
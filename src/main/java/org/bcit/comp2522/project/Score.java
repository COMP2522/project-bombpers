package org.bcit.comp2522.project;

/**
 * Class for Score of the game set, gets, and displays score.
 */
public class Score extends UserInterface {
  private static final int DEFAULT_X_POS = (int) (Window.WINDOW_WIDTH / ConstantManager.TWOF);
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

  private final int STARTING_SCORE = 0;
  private final int MENU_SCORE_TEXT_SIZE = 60;
  private final int GAME_SCORE_TEXT_SIZE = MENU_SCORE_TEXT_SIZE / 2;
  private final int RED_COLOR_VALUE = 255;
  private final int GREEN_COLOR_VALUE = 255;
  private final int BLUE_COLOR_VALUE = 0;
  private final int ENEMY_FAST_VAUE = 2;
  private final int ENEMY_SLOW_VAUE = 3;

  private GameState currState;

  /**
   * Constructor for Score.
   *
   * @param window    the window of the game that the score is displayed on
   */
  public Score(Window window, GameState state) {
    super(DEFAULT_X_POS, DEFAULT_Y_POS);
    this.window = window;
    this.currState = state;
  }

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
    //Depending on the state of the game, call the appropriate method to display the score
    window.fill(RED_COLOR_VALUE, GREEN_COLOR_VALUE, BLUE_COLOR_VALUE);
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
    window.textSize(GAME_SCORE_TEXT_SIZE);
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

  public void incrementScore(int score, Enemy enemy) {
    int typeOfEnemy = enemy.enemyType;
    switch (typeOfEnemy) {
      case EnemyConfig.ENEMY_STANDARD_TYPE -> score++;
      case EnemyConfig.ENEMY_FAST_TYPE -> score = score + ENEMY_FAST_VAUE;
      case EnemyConfig.ENEMY_SLOW_TYPE -> score = score + ENEMY_SLOW_VAUE;
    }
    setCurrentScore(score);
  }

  public void resetScore() {
    setCurrentScore(STARTING_SCORE);
  }
}
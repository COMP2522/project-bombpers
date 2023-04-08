package org.bcit.comp2522.project;

/**
 * Class for menuHandler that handles the menus of the game.
 */
public class MenuHandler {
  /**
   * Left Bound for the button.
   */
  private final int buttonLeftBound = 120;
  /**
   * Right Bound for the button.
   */
  private final int buttonRightBound = 312;
  /**
   * Top Bound for the button.
   */
  private final int buttonTopBound = 199;
  /**
   * Bottom Bound for the button.
   */
  private final int buttonBottomBound = 244;
  /**
   * The possible types of menus.
   */
  public Menu startMenu;
  public Menu pauseMenu;
  public Menu endMenu;
  /**
   * The window the menu is displayed on.
   */
  Window window;
  /**
   * The current state of the game.
   */
  public GameState currentState;

  public Score menuScore;
  public float scoreXPosition;

  private final int SCORE_OFFSET_WHEN_TEN = 10;
  private final int SCORE_OFFSET_WHEN_HUNDRED = 100;
  private final int SCORE_OFFSET_WHEN_THOUSEAND = 1000;
  private final float OFFSET_TEXT_RESIZE_FOR_LESS_THAN_TEN = 1.1f;
  private final float OFFSET_TEXT_RESIZE_FOR_GREATER_TEN = 1.07f;
  private final float OFFSET_TEXT_RESIZE_FOR_GREATER_THAN_HUNDRED = 1.01f;

  private final int OFFSET_HEIGHT = 200;
  private final int OFFSET_WIDTH = 50;
  private final int START_MENU_TITLE_X_POSITION = 30;
  private final int START_MENU_TITLE_Y_POSITION = 145;
  private final int PAUSE_MENU_TITLE_X_POSITION = (int) (Window.WINDOW_WIDTH / 2f);
  private final int PAUSE_MENU_TITLE_Y_POSITION = (int) (Window.WINDOW_HEIGHT * 0.7f);
  private final int END_MENU_TITLE_X_POSITION = (int) (Window.WINDOW_WIDTH / 2f);
  private final int END_MENU_TITLE_Y_POSITION = (int) (Window.WINDOW_HEIGHT * 0.7f);

  /**
   * Constructor for menuHandler.
   *
   * @param state  the current state of the game
   * @param window the window the menu is displayed of
   */
  public MenuHandler(GameState state, Window window) {
    this.currentState = state;
    this.window = window;
  }

  /**
   * Creates the menu.
   *
   * @param state the current state of the game
   * @return the current state of the game
   */
  public GameState createMenu(GameState state, int currScore, int highScore) {
    int scoreXPosition = this.window.width - OFFSET_WIDTH;
    int scoreYPosition = this.window.height - OFFSET_HEIGHT;
    //store the current state
    this.currentState = state;
    //Creates new score object to use in menus since score id displayed differently in menus
    this.menuScore = new Score(this.window, this.currentState);
    if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
        && (this.window.mouseX >= buttonLeftBound && this.window.mouseX < buttonRightBound)
        && (this.window.mouseY >= buttonTopBound
        && this.window.mouseY <= buttonBottomBound)) {
      this.window.mousePressed = false;
      if (this.currentState == GameState.ENDGAME) {
        this.window.init();
      }
      this.currentState = GameState.STARTGAME;
      return this.currentState;
    }
    //if the current state is startMenu, create the startMenu
    if (this.currentState == GameState.STARTMENU) {
      startMenu = new Menu(START_MENU_TITLE_X_POSITION, START_MENU_TITLE_Y_POSITION, "Wave " +
          "Assault", this.window);
      //display the startMenu
      startMenu.updateGameState(this.currentState);
      startMenu.drawUserInterface();
      //return the start menu so that it can be continued to be displayed
      return GameState.STARTMENU;
    } else if (this.currentState == GameState.PAUSE) {
      // If the current state is pause, create the pauseMenu
      pauseMenu = new Menu(PAUSE_MENU_TITLE_X_POSITION, PAUSE_MENU_TITLE_Y_POSITION, "Paused!", this.window);
      // display the pauseMenu
      pauseMenu.updateGameState(this.currentState);
      pauseMenu.drawUserInterface();
      //display the score
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.updateGameState(this.currentState);
      menuScore.drawUserInterface();
      //return the pause menu so that it can be continued to be displayed
      return GameState.PAUSE;

    } else {
      // If the current state is endGame, create the endMenu
      endMenu = new Menu(END_MENU_TITLE_X_POSITION, END_MENU_TITLE_Y_POSITION, "Game Over", this.window);
      endMenu.updateGameState(this.currentState);
      endMenu.drawUserInterface();
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.updateGameState(this.currentState);
      menuScore.drawUserInterface();
      return GameState.ENDGAME;
    }
  }

  /**
   * Displays the menu depending on the state.
   * @return
   */
  private boolean displayMenu() {
    menuScore.displayScore(currentState);
    if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
            && (this.window.mouseX >= 120 && this.window.mouseX < 312)
            && (this.window.mouseY >= 199 && this.window.mouseY <= 244)) {
      this.currentState = GameState.STARTGAME;
      return true;
    }
    return false;
  }
}
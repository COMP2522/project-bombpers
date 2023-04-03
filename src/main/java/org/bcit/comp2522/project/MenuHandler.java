package org.bcit.comp2522.project;

/**
 * Class for menuHandler that handles the menus of the game.
 */
public class MenuHandler {
  /**
   * Left Bound for the button.
   */
  private final int buttonLeftBound = 120;
  /** Right Bound for the button. */
  private final int buttonRightBound = 312;
  /** Top Bound for the button. */
  private final int buttonTopBound = 199;
  /** Bottom Bound for the button. */
  private final int buttonBottomBound = 244;
  /** The possible types of menus. */
  public Menu startMenu;
  public Menu pauseMenu;
  public Menu endMenu;
  /** The winodw the menu is displayed on. */
  Window window;
  /** The current state of the game. */
  public GameState currentState;

  public Score menuScore;
  private  float scoreXPosition;

  private final int SCORE_OFFSET_WHEN_TEN = 10;
  private final int SCORE_OFFSET_WHEN_HUNDRED = 100;
  private final int SCORE_OFFSET_WHEN_THOUSEAND = 1000;
  private final float OFFSET_TEXT_RESIZE_FOR_LESS_THAN_TEN = 1.1f;
  private final float OFFSET_TEXT_RESIZE_FOR_GREATER_TEN = 1.07f;
  private final float OFFSET_TEXT_RESIZE_FOR_GREATER_THAN_HUNDRED = 1.01f;

  private final int OFFSET_HEIGHT = 90;
  private final int START_MENU_TITLE_X_POSITION = 50;
  private final int START_MENU_TITLE_Y_POSITION = 145;
  private final int PAUSE_MENU_TITLE_X_POSITION = 420;
  private final int PAUSE_MENU_TITLE_Y_POSITION = 125;
  private final int END_MENU_TITLE_X_POSITION = 470;
  private final int END_MENU_TITLE_Y_POSITION = 120;
  /**
   * Constructor for menuHandler.
   *
   * @param state the current state of the game
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
    if (currScore<SCORE_OFFSET_WHEN_TEN) {
      scoreXPosition = this.window.width/OFFSET_TEXT_RESIZE_FOR_LESS_THAN_TEN;
    } else if (currScore >= SCORE_OFFSET_WHEN_TEN && currScore < SCORE_OFFSET_WHEN_HUNDRED) {
       scoreXPosition = this.window.width/OFFSET_TEXT_RESIZE_FOR_GREATER_TEN;
    } else if (currScore>= SCORE_OFFSET_WHEN_HUNDRED && currScore < SCORE_OFFSET_WHEN_THOUSEAND) {
      scoreXPosition = this.window.width/OFFSET_TEXT_RESIZE_FOR_GREATER_THAN_HUNDRED;
    }
    int scoreYPosition = this.window.height - OFFSET_HEIGHT;
    //store the current state
    this.currentState = state;
    //Creates new score object to use in menus since score id displayed differently in menus
    this.menuScore = new Score(scoreXPosition,scoreYPosition,this.window);
    if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
            && (this.window.mouseX >= buttonLeftBound && this.window.mouseX < buttonRightBound)
            && (this.window.mouseY >= buttonTopBound
            && this.window.mouseY <= buttonBottomBound)) {
      this.window.mousePressed = false;
        if (this.currentState == GameState.ENDGAME){
          this.window.init();
        }
      this.currentState = GameState.STARTGAME;
      return this.currentState;
    }
    //if the current state is startMenu, create the startMenu
    if (this.currentState == GameState.STARTMENU) {
      startMenu = new Menu(START_MENU_TITLE_X_POSITION, START_MENU_TITLE_Y_POSITION, "Welcome!", this.window);
      //display the startMenu
      startMenu.drawUserInterface(this.currentState);
      //return the start menu so that it can be continued to be displayed
      return GameState.STARTMENU;
    } else if (this.currentState == GameState.PAUSE) {
      // If the current state is pause, create the pauseMenu
      pauseMenu = new Menu(PAUSE_MENU_TITLE_X_POSITION, PAUSE_MENU_TITLE_Y_POSITION, "Paused!", this.window);
      // display the pauseMenu
      pauseMenu.drawUserInterface(this.currentState);
      //display the score
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.drawUserInterface(currentState);
      //return the pause menu so that it can be continued to be displayed
      return GameState.PAUSE;

    } else {
      // If the current state is endGame, create the endMenu
      endMenu = new Menu(END_MENU_TITLE_X_POSITION, END_MENU_TITLE_Y_POSITION, "Game Over", this.window);
      endMenu.drawUserInterface(this.currentState);
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.drawUserInterface(this.currentState);
      return GameState.ENDGAME;
    }
  }
}
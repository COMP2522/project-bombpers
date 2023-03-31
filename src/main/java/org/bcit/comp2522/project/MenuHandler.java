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

  /**
   * Constructor for menuHandler.
   *
   * @param state the current state of the game
   * @param window the window the menu is displayed on
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
    //store the current state
    this.currentState = state;
    this.menuScore = new Score(400,360,this.window);
    //if the current state is startMenu, create the startMenu
    if (this.currentState == GameState.STARTMENU) {
      startMenu = new Menu(50, 145, "Welcome!", this.window);
      //display the startMenu
      startMenu.displayMenu(this.currentState, 100);
      //If the mouse is pressed and the mouse is within the bounds of the button,
      // change the state to startGame to start the game
      if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
              && (this.window.mouseX >= buttonLeftBound && this.window.mouseX < buttonRightBound)
              && (this.window.mouseY >= buttonTopBound
              && this.window.mouseY <= buttonBottomBound)) {
        this.window.mousePressed = false;
        this.currentState = GameState.STARTGAME;
        return currentState;
      }
      //return the start menu so that it can be continued to be displayed
      return GameState.STARTMENU;
    } else if (this.currentState == GameState.PAUSE) {
      // If the current state is pause, create the pauseMenu
      pauseMenu = new Menu(420, 120, "Paused!", this.window);
      // display the pauseMenu
      pauseMenu.displayMenu(this.currentState, 100);
      //display the score
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.displayScore(currentState);
      //If the mouse is pressed and the mouse is within the bounds of the button,
      // change the state to startGame to start the game
      if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
              && (this.window.mouseX >= 120 && this.window.mouseX < 312)
              && (this.window.mouseY >= 199 && this.window.mouseY <= 244)) {

        this.currentState = GameState.STARTGAME;
        return currentState;
      }
      //return the pause menu so that it can be continued to be displayed
      return GameState.PAUSE;

    } else {
      // If the current state is endGame, create the endMenu
      endMenu = new Menu(470, 120, "Game Over !", this.window);
      endMenu.displayMenu(state, 80);
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.displayScore(currentState);

      if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
              && (this.window.mouseX >= 120 && this.window.mouseX < 312)
              && (this.window.mouseY >= 199 && this.window.mouseY <= 244)) {
        this.currentState = GameState.STARTGAME;
        this.window.init();

        menuScore.setHighScore(menuScore.getHighScore());
        return currentState;
      }
      return GameState.ENDGAME;
    }
  }
}
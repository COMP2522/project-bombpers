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
    if (currScore<10) {
      scoreXPosition = this.window.width/1.1f;
    } else if (currScore >= 10 && currScore < 100) {
       scoreXPosition = this.window.width/1.07f;
    } else if (currScore>= 100 && currScore < 1000) {
      scoreXPosition = this.window.width/1.01f;
    }
    int scoreYPosition = this.window.height - 90;
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
      startMenu = new Menu(50, 145, "Welcome!", this.window);
      //display the startMenu
      startMenu.drawUserInterface(this.currentState);
      //return the start menu so that it can be continued to be displayed
      return GameState.STARTMENU;
    } else if (this.currentState == GameState.PAUSE) {
      // If the current state is pause, create the pauseMenu
      pauseMenu = new Menu(420, 120, "Paused!", this.window);
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
      endMenu = new Menu(470, 120, "Game Over", this.window);
      endMenu.drawUserInterface(this.currentState);
      menuScore.setCurrentScore(currScore);
      menuScore.setHighScore(highScore);
      menuScore.drawUserInterface(this.currentState);
      return GameState.ENDGAME;
    }
  }
}
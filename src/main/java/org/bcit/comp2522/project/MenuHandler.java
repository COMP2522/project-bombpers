package org.bcit.comp2522.project;

/**
 * Class for menuHandler that handles the menus of the game.
 */
public class MenuHandler {
  /**
   * The window the menu is displayed on.
   */
  private final Window window;
  /**
   * The current state of the game.
   */
  public GameState currentState;

  public Score menuScore;
  private static final int START_MENU_TITLE_X_POSITION = 30;
  private static final int START_MENU_TITLE_Y_POSITION = 145;
  private static final int PAUSE_MENU_TITLE_X_POSITION = (int) (Window.WINDOW_WIDTH / 2f);
  private static final int PAUSE_MENU_TITLE_Y_POSITION = (int) (Window.WINDOW_HEIGHT * 0.7f);
  private static final int END_MENU_TITLE_X_POSITION = (int) (Window.WINDOW_WIDTH / 2f);
  private static final int END_MENU_TITLE_Y_POSITION = (int) (Window.WINDOW_HEIGHT * 0.7f);

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
    this.currentState = state;
    int buttonLeftBound = 120;
    int buttonRightBound = 312;
    int buttonTopBound = 199;
    int buttonBottomBound = 244;
    if (this.window.mousePressed && this.window.mouseButton == this.window.LEFT && this.window.mouseX >= buttonLeftBound && this.window.mouseX < buttonRightBound && this.window.mouseY >= buttonTopBound && this.window.mouseY <= buttonBottomBound) {
      this.window.mousePressed = false;
      if (this.currentState == GameState.ENDGAME) {
        this.window.init();
      }
      this.currentState = GameState.STARTGAME;
      return this.currentState;
    }

    Menu menu;
    String title;
    int titleX;
    int titleY;
    switch (this.currentState) {
      case STARTMENU -> {
        title = "Wave Assault";
        titleX = START_MENU_TITLE_X_POSITION;
        titleY = START_MENU_TITLE_Y_POSITION;
        menu = new Menu(titleX, titleY, title, this.window);
        setupMenu(menu);
      }
      case PAUSE -> {
        title = "Paused!";
        titleX = PAUSE_MENU_TITLE_X_POSITION;
        titleY = PAUSE_MENU_TITLE_Y_POSITION;
        menu = new Menu(titleX, titleY, title, this.window);
        setupMenu(menu);
        setupScore(currScore, highScore);
      }
      case ENDGAME -> {
        title = "Game Over";
        titleX = END_MENU_TITLE_X_POSITION;
        titleY = END_MENU_TITLE_Y_POSITION;
        menu = new Menu(titleX, titleY, title, this.window);
        setupMenu(menu);
        setupScore(currScore, highScore);
      }
      default -> throw new IllegalArgumentException("Invalid game state: " + this.currentState);
    }

    return this.currentState;
  }


  private void setupMenu(Menu menu) {
    menu.updateGameState(this.currentState);
    menu.drawUserInterface();
  }

  private void setupScore(int currentScore, int highScore) {
    this.menuScore = new Score(this.window, this.currentState);
    this.menuScore.setCurrentScore(currentScore);
    this.menuScore.setHighScore(highScore);
    this.menuScore.updateGameState(this.currentState);
    this.menuScore.drawUserInterface();
  }
}
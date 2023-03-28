package org.bcit.comp2522.project;

public class menuHandler {
    private int button_left_Bound = 120;
    private int button_right_Bound = 312;
    private int button_top_Bound = 199;
    private int button_bottom_Bound = 244;
    private Menu menu, menu2, menu3, menu4;
    Window window;
    private gameState currentState;
    public menuHandler(gameState state, Window window){
        this.currentState  = state;
        this.window = window;
    }

    public gameState createMenu(){
        System.out.println("creating menu");
        if (this.currentState == gameState.startMenu){
            menu = new Menu(50, 145, "Welcome!", this.window);
            //menu.displayMenu(this.currentState, 100);
            if (this.window.mousePressed && (this.window.mouseButton == this.window.LEFT)
                    && (this.window.mouseX >= button_left_Bound && this.window.mouseX < button_right_Bound)
                    && (this.window.mouseY >= button_top_Bound && this.window.mouseY <= button_bottom_Bound)) {
                this.window.background(0);
                //for the if statement that has the game animations
                this.window.mousePressed = false;
                return gameState.startGame;
                // score.setHighScore(0);
            }
        }
        return currentState;
    }
}

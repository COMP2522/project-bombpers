package org.bcit.comp2522.project;

import processing.core.PApplet;

public class Menu extends UI {
  private String message;
    private Window w;
  public Menu(int xPos, int yPos, String message, Window wind) {
    super(xPos, yPos);
    this.message = message;
    this.w = wind;
  }

  public void displayMenu(int state, int titleSize) {
      w.background(0);
    if (state == 0) {
        //welcome message
        modifyMessage(titleSize);
        //button
        button();
        w.text("Start", 160, 245);
    } else if (state == 2) {
        //resets background so screen "updates"

        //title
        modifyMessage(titleSize);
        //button
        button();
        w.text("Restart", 140, 245);
    } else if(state == 3){
        //resets background so screen "updates"
        //title
        modifyMessage(titleSize);
        //button
        button();
        w.text("Continue", 120, 245);
    } else if(state == 4){
        //resets background so screen "updates"
        //title
        modifyMessage(titleSize);
        //button
        button();
        w.text("Speedy", 140, 240);
        button2();
        w.text("Tank", 140, 340);
    }

  }

    public void modifyMessage(int textSize){
        setMessage(message);
        String message = getMessage();
        w.textSize(textSize);
        w.fill(0, 408, 612);
        w.text(getMessage(), getXPos(), getYPos());

    }
    public void button(){
        w.fill(153);
        w.rect(120, 200, 200, 55);
        w.textSize(50);
        w.fill(200, 0, 0);
    }
    public void button2(){
        w.fill(100);
        //xCord,yCord,width,height
        w.rect(120, 300, 200, 55);
        w.textSize(50);
        w.fill(200, 0, 0);
    }
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {

    this.message = message;

  }
}

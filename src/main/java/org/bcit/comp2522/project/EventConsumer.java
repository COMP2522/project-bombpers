package org.bcit.comp2522.project;

/**
 * EventConsumer class.
 * Use is for Callback Functions in our project.
 *
 * @author Brett R
 * @version 3/14/2023
 */
public class EventConsumer {
  private final EventListener eventListener;

  public EventConsumer(EventListener eventListener) {
    this.eventListener = eventListener;
  }

  public void doThingAsync() {
    System.out.println("I'm doing the thing async");
    new Thread(eventListener::onTrigger).start();
  }
}

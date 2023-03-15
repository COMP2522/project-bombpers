package org.bcit.comp2522.project;

/**
 * EventListenerImpl class.
 * Use is for Callback Functions in our project.
 *
 * @author Brett R
 * @version 3/14/2023
 */
public class EventListenerImpl implements EventListener {

  @Override
  public void onTrigger() {
    triggerResponse();
  }

  @Override
  public void triggerResponse() {
    System.out.println("trigger response");
  }
}

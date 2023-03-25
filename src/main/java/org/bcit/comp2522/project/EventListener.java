package org.bcit.comp2522.project;

/**
 * EventListener interface.
 * Use is for Callback Functions in our project.
 *
 * @author Brett R
 * @version 3/14/2023
 */
public interface EventListener {
  void onTrigger();

  void triggerResponse(); // async response

}

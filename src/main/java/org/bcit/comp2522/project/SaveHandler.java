package org.bcit.comp2522.project;

import java.util.logging.Logger;

/**
 * Class to handle the saving of the game.
 *
 * @author Brett Reader, Ozan Yurtisigi, Sukhraj Sidhu
 * @version 1.0
 */
public class SaveHandler {

  /**
   * DatabaseHandler object to handle the saving of the game.
   */
  private final DatabaseHandler db;

  /**
   * Constructor for the SaveHandler class.
   *
   * @param u UiHandler object to handle the user interface
   */
  public SaveHandler(UiHandler u) {
    CollectionManager c = CollectionManager.getInstance();
    this.db = DatabaseHandler.getInstance(u, c);
  }

  private void save() {
    db.save();
  }

  /**
   * Auto saves the game every 30 seconds.
   */
  public void autoSave() {
    for (; ; ) {
      try {
        Thread.sleep(30000);
        this.save();
      } catch (InterruptedException e) {
        Logger.getLogger("org.mongodb.driver").severe(e.getMessage());
      }
    }
  }

}

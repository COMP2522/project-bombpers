package org.bcit.comp2522.project;

import java.util.logging.Logger;

/**
 * Class to handle the saving of the game.
 *
 * @author Brett Reader, Ozan Yurtisigi, Sukhraj Sidhu
 * @version 1.0
 */
public class SaveHandler {
  private final DatabaseHandler db;

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
    //System.out.println("Game Started, it will be saved every half minute");
    for (; ; ) {
      try {
        Thread.sleep(30000);
        //Thread.sleep(5000);
        this.save();
        //System.out.println("Game has been saved");
      } catch (InterruptedException e) {
        Logger.getLogger("org.mongodb.driver").severe(e.getMessage());
      }
    }
  }

}

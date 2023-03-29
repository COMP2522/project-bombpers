package org.bcit.comp2522.project;
import com.google.gson.Gson;

public class SaveHandler {
  DatabaseHandler db;
  CollectionManager c;

  public SaveHandler() {
    this.db = DatabaseHandler.getInstance();
    this.c = CollectionManager.getInstance();
  }

  private void save() {
    String info = "Info to save";
    db.save("Game", info);
  }

  public void autoSave(){
    for (; ; ) {
      try {
        Thread.sleep(10000);
        this.save();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

}

package org.bcit.comp2522.project;

public class SaveHandler {
  DatabaseHandler db;
  CollectionManager c;

  public SaveHandler() {
    this.db = DatabaseHandler.getInstance();
    this.c = CollectionManager.getInstance();
  }

  private void save() {
    db.save();
  }

  public void autoSave(){
    for (; ; ) {
      try {
        Thread.sleep(30000);
        this.save();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

}

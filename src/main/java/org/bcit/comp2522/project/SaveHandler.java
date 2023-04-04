package org.bcit.comp2522.project;

public class SaveHandler {
  DatabaseHandler db;
  CollectionManager c;

  public SaveHandler() {
    this.c = CollectionManager.getInstance();
    this.db = DatabaseHandler.getInstance(c);
  }

  private void save(Score s) {
    db.save(s);
  }

  public void autoSave(CollectionManager c, Score s) {
    System.out.println("Game Started, it will be saved every half minute");
    for (; ; ) {
      try {
        Thread.sleep(30000);
        this.save(s);
        System.out.println("Game has been saved");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

}

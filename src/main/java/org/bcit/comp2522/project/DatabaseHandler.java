package org.bcit.comp2522.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

public class DatabaseHandler {
  MongoDatabase database;

  private static DatabaseHandler instance;

  String content;

  CollectionManager cm;

  private DatabaseHandler(CollectionManager cm) {
    try {
      content = new String(Files.readAllBytes(Paths.get("token.txt")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ConnectionString connectionString = new ConnectionString(content);
    MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();
    MongoClient mongoClient = MongoClients.create(settings);
    this.database = mongoClient.getDatabase("Bombpers");
    this.cm = cm;
  }

  public static DatabaseHandler getInstance(CollectionManager cm) {
    if (instance == null) {
      instance = new DatabaseHandler(cm);
    }
    return instance;
  }

  public void save(Score s) {
    System.out.println("Saving game");
    this.getInfo(cm, s);
  }

  public int load() {
    MongoCollection<Document> collection = database.getCollection("Game");

    int highestScore = 0;
    try (MongoCursor<Document> cursor = collection.find().iterator()) {
      while (cursor.hasNext()) {
        Document doc = cursor.next();
        int highscore = doc.getEmbedded(Arrays.asList("Score", "Highscore"), Integer.class);
        if (highscore > highestScore) {
          highestScore = highscore;
        }
      }
    }

    return highestScore;
  }


  public void getInfo(CollectionManager c, Score s) {
    Document gameDoc = new Document();
    Document playerDoc = new Document();
    Document enemyDoc = new Document();
    Document scoreDoc = new Document();

    //Get the score info
    scoreDoc.append("Highscore", c.getHighScore());
    scoreDoc.append("CurrentScore", c.getCurrentScore());
    gameDoc.append("Score", scoreDoc);

    //Get the info from the collection manager for the Player
    playerDoc.append("health", c.getPlayer().getHealth());
    playerDoc.append("damage", c.getPlayer().getDamage());
    playerDoc.append("x-Position", c.getPlayer().getPositionX());
    playerDoc.append("y-Position", c.getPlayer().getPositionY());

    //add it to the gameDoc
    gameDoc.append("Player", playerDoc);

    //Get the enemy info from the collection manager
    int n = 0;
    for (Enemy e : c.getEnemies()) {
      enemyDoc.append("health", e.getHealth());
      enemyDoc.append("damage", e.getDamage());
      enemyDoc.append("x-Position", e.getPositionX());
      enemyDoc.append("y-Position", e.getPositionY());
      gameDoc.append("Enemy_" + n, enemyDoc);
      n++;
    }
    System.out.println("I'm done getting the info");

    //add the game doc to the database
    database.getCollection("Game").insertOne(gameDoc);
  }

}
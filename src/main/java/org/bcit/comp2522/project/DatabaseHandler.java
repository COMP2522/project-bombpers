package org.bcit.comp2522.project;

import com.mongodb.*; // star format was used here to avoid like 20 imports.
import com.mongodb.client.*; // star format was used here to avoid like 20 imports.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.bson.Document;

/**
 * A class that handles the connection and communication with a MongoDB database. The class has
 * methods for saving and loading data related to our game, such as player and enemy information
 * and game scores.
 *
 * @author Brett Reader
 * @version 2023-04-08
 */
public class DatabaseHandler {
  MongoDatabase database;

  private static DatabaseHandler instance;

  String content;

  UIHandler uiHandler;

  CollectionManager cm;

  /**
   * Singleton constructor for the DatabaseHandler.
   *
   * @param uiHandler the uiHandler being passed to the constructor
   * @param cm        the collectionManager being passed to the constructor
   */
  private DatabaseHandler(UIHandler uiHandler, CollectionManager cm) {
    try {
      content = new String(Files.readAllBytes(Paths.get("token.txt")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ConnectionString connectionString = new ConnectionString(content);
    MongoClientSettings settings = MongoClientSettings
        .builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    this.database = mongoClient.getDatabase("Bombpers");
    this.cm = cm;
    this.uiHandler = uiHandler;
  }

  /**
   * Gets the instance of the DB handler from the database. It checks if the DB handler has been
   * initialized and if it has not it will create a new instance of the DB handler.
   *
   * @param u  the uihandler being passed to the method
   * @param cm the collectionManager being passed to the method
   * @return the instance of the database handler.
   */
  public static DatabaseHandler getInstance(UIHandler u, CollectionManager cm) {
    if (instance == null) {
      instance = new DatabaseHandler(u, cm);
    }
    return instance;
  }

  public void save(Score s) {
    System.out.println("Saving game");
    this.getInfo(cm, uiHandler);
  }

  /**
   * Loads the highest score from the database.
   *
   * @return the highest score from the database
   */
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


  /**
   * Helper function for getInfo(). Creates a document for the score.
   *
   * @param u the uiHandler containing the score object.
   * @return the score document.
   */
  private Document createScoreDocument(UIHandler u) {
    Document scoreDoc = new Document();
    scoreDoc.append("Highscore", u.getScore().getHighScore());
    scoreDoc.append("CurrentScore", u.getScore().getCurrentScore());
    return scoreDoc;
  }

  /**
   * Helper function for getInfo(). Creates a document for the player.
   *
   * @param c the collectionManager containing the player object.
   * @return the player document.
   */
  private Document createPlayerDocument(CollectionManager c) {
    Document playerDoc = new Document();
    playerDoc.append("health", c.getPlayer().getHealth());
    playerDoc.append("damage", c.getPlayer().getDamage());
    return playerDoc;
  }

  /**
   * Helper function for getInfo(). Creates a document for the enemy.
   *
   * @param e the enemy object
   * @param n the number of the enemy. This is for printing purposes.
   * @return the enemy document.
   */
  private Document createEnemyDocument(Enemy e, int n) {
    Document enemyDoc = new Document();
    enemyDoc.append("health", e.getHealth());
    enemyDoc.append("damage", e.getDamage());
    return new Document("Enemy_" + n, enemyDoc);
  }

  /**
   * Gets the information from the game and saves it to the database.
   *
   * @param c the collectionManager containing the game information.
   * @param u the uiHandler containing the game information.
   */
  public void getInfo(CollectionManager c, UIHandler u) {
    Document gameDoc = new Document();
    gameDoc.append("Score", createScoreDocument(u));
    gameDoc.append("Player", createPlayerDocument(c));
    int n = 0;
    for (Enemy e : c.getEnemies()) {
      gameDoc.append("Enemy_" + n, createEnemyDocument(e, n));
      n++;
    }
    try {
      database.getCollection("Game").insertOne(gameDoc);
      System.out.println("Data inserted successfully");
    } catch (MongoException ex) {
      System.out.println("Error inserting data into database: " + ex.getMessage());
    }
  }


}
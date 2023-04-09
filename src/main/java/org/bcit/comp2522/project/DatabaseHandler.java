package org.bcit.comp2522.project;

import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Logger;
import org.bson.Document;

/**
 * A class that handles the connection and communication with a MongoDB database. The class has
 * methods for saving and loading data related to our game, such as player and enemy information
 * and game scores.
 *
 * @author Brett Reader, Benny Li
 * @version 1.0
 */
public final class DatabaseHandler {
  private final MongoDatabase database;

  private static DatabaseHandler instance;

  private String content;

  private final UiHandler uiHandler;

  private final CollectionManager cm;

  /**
   * Singleton constructor for the DatabaseHandler.
   *
   * @param uiHandler the uiHandler being passed to the constructor
   * @param cm        the collectionManager being passed to the constructor
   */
  private DatabaseHandler(final UiHandler uiHandler, final CollectionManager cm) {
    try {
      content = new String(Files.readAllBytes(Paths.get("token.txt")));
    } catch (IOException e) {
      Logger.getLogger("org.mongodb.driver").severe(e.getMessage());
    }
    final com.mongodb.ConnectionString connectionString = new com.mongodb.ConnectionString(content);
    final com.mongodb.MongoClientSettings settings = com.mongodb.MongoClientSettings
        .builder()
        .applyConnectionString(connectionString)
        .serverApi(com.mongodb.ServerApi.builder().version(com.mongodb.ServerApiVersion.V1).build())
        .build();
    final com.mongodb.client.MongoClient mongoClient =
        com.mongodb.client.MongoClients.create(settings);
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
  public static DatabaseHandler getInstance(final UiHandler u, final CollectionManager cm) {
    if (instance == null) {
      instance = new DatabaseHandler(u, cm);
    }
    return instance;
  }

  public void save() {
    //System.out.println("Saving game");
    this.saveInfo(cm, uiHandler);
  }

  /**
   * Loads the highest score from the database.
   *
   * @return the highest score from the database
   */
  public int load() {
    final com.mongodb.client.MongoCollection<Document> collection = database.getCollection("Game");

    int highestScore = 0;
    try (com.mongodb.client.MongoCursor<Document> cursor = collection.find().iterator()) {
      while (cursor.hasNext()) {
        final Document doc = cursor.next();
        final int highscore = doc.getEmbedded(Arrays.asList("Score", "Highscore"), Integer.class);
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
  private Document createScoreDocument(final UiHandler u) {
    final Document scoreDoc = new Document();
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
  private Document createPlayerDocument(final CollectionManager c) {
    final Document playerDoc = new Document();
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
  private Document createEnemyDocument(final Enemy e, final int n) {
    final Document enemyDoc = new Document();
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
  public void saveInfo(final CollectionManager c, final UiHandler u) {
    final Document gameDoc = new Document();
    gameDoc.append("Score", createScoreDocument(u));
    gameDoc.append("Player", createPlayerDocument(c));
    int n = 0;
    for (final Enemy e : c.getEnemies()) {
      gameDoc.append("Enemy_" + n, createEnemyDocument(e, n));
      n++;
    }
    try {
      database.getCollection("Game").insertOne(gameDoc);
      //System.out.println("Data inserted successfully");
    } catch (com.mongodb.MongoException ex) {
      Logger.getLogger("org.mongodb.driver").severe(ex.getMessage());
    }
  }


}
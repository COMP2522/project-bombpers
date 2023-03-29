package org.bcit.comp2522.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


//This is for getting values from the DB, please don't delete it.
import static com.mongodb.client.model.Filters.eq;

public class DatabaseHandler {
  MongoDatabase database;

  private static DatabaseHandler instance;

  String content;

  static CollectionManager collectionManager = CollectionManager.getInstance();



  private DatabaseHandler() {
    try {
      content = new String(Files.readAllBytes(Paths.get("token.txt")));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ConnectionString connectionString = new ConnectionString(content);
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    this.database = mongoClient.getDatabase("Bombpers");
  }

  public static DatabaseHandler getInstance() {
    if(instance == null) {
      instance = new DatabaseHandler();
    }
    return instance;
  }

  public void save(String key, String value) {
    System.out.println("Saving " + key + " to the database.");
  }

  public void load() {
    //TODO implement this.
  }

  public static void main(String[] args) {

    DatabaseHandler db = new DatabaseHandler();
    //db.database.createCollection("Game"); //creates a collection called Game !DON'T UNCOMMENT!

    //Add everything to this doc at the end
    Document gameDoc = new Document();

    //Doc for the things I want to save the state of
    Document playerDoc = new Document();
    Document enemyDoc = new Document();


    //player info
    playerDoc.append("Testing","token");

    //Add the Player info to the game doc
    gameDoc.append("Player", playerDoc);

    //enemy info
    enemyDoc.append("Testing","token");

    //Add the Enemy info to the game doc
    gameDoc.append("Enemy", enemyDoc);

    //TODO: Get collection manager working with projectiles and the player and enemies
    // and use the collection manager to get the info for the game doc more cleanly.
    // For now I'm just going to use the player info as an example.

    // Above is an example of how to create one line of BSON and below is an example of
    // adding it to the database.

    //Add the game doc to the database
    db.database.getCollection("Game").insertOne(gameDoc);

  }

}

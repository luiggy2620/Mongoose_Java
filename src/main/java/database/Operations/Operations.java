package database.Operations;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.Connection;
import org.bson.Document;

public class Operations {

    protected MongoDatabase database;
    protected MongoCollection<Document> usersCollection;
    protected MongoClient connection;

    protected MongoClient getConnection() {
        if(connection == null)
            connection = Connection.getConnection().getMongodb();
        return connection;
    }

    protected MongoDatabase getDatabase() {
        if(database == null)
            database = getConnection().getDatabase("socialMedia");
        return database;
    }

    protected MongoCollection<Document> getUsersCollection() {
        if(usersCollection == null)
            usersCollection = getDatabase().getCollection("users");
        return usersCollection;
    }
}
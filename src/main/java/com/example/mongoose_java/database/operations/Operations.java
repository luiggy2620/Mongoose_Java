package com.example.mongoose_java.database.operations;

import com.example.mongoose_java.database.Connection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public abstract class Operations {

    protected MongoDatabase database;
    protected MongoCollection<Document> usersCollection;
    protected MongoCollection<Document> postsCollection;
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

    protected MongoCollection<Document> getPostsCollection() {
        if(postsCollection == null)
            postsCollection = getDatabase().getCollection("posts");
        return postsCollection;
    }

    protected Document getProjection(boolean isVisible, String... keys) {
        int visibility = 0;
        if (isVisible) visibility = 1;
        Document projection = new Document();
        for (String key : keys)
            projection.append(key, visibility);
        return projection;
    }

    protected abstract Document getSchema(Object object);
}
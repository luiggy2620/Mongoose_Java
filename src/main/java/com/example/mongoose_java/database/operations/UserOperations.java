package com.example.mongoose_java.database.operations;

import com.example.mongoose_java.database.CRUD.*;
import com.example.mongoose_java.database.schemaKeys.UserKeys;
import com.example.mongoose_java.model.User;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class UserOperations extends Operations implements Get, Post, Put, Delete {

    private Document user;
    private MongoCursor<Document> users;
    public static UserOperations userOperations;

    public UserOperations() {
        this.users = null;
        this.user = null;
    }

    public static UserOperations getUserOperations() {
        if (userOperations == null)
            userOperations = new UserOperations();
        return userOperations;
    }


    @Override
    protected Document getSchema(Object object) {
        User user = (User) object;
        return new Document("_id", new ObjectId())
                .append("name", user.getName())
                .append("userName", user.getUserName())
                .append("email", user.getEmail())
                .append("password", user.getPassword())
                .append("isPublic", user.isPublic())
                .append("posts", user.getPosts());
    }

    @Override
    public MongoCursor<Document> findAll() {
        try {
            users = getUsersCollection().find().iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    private MongoCursor<Document> findByProjection(boolean isVisible, String ...keys) {
        try {
            Document projection = getProjection(isVisible, keys);
            users = getUsersCollection().find().projection(projection).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    private String[] getKeysString(Object ...keys) {
        String[] keysString = new String[keys.length];
        int i = 0;

        for (Object key : keys) {
            keysString[i] = ((UserKeys)key).getText();
            i++;
        }

        return keysString;
    }

    private String getKeyText(Object key) {
        return ((UserKeys) key).getText();
    }

    @Override
    public MongoCursor<Document> findAllExcept(Object... keys) {
        return findByProjection(false, getKeysString(keys));
    }

    @Override
    public MongoCursor<Document> findAllJust(Object... keys) {
        return findByProjection(true, getKeysString(keys));
    }

    @Override
    public MongoCursor<Document> findAllAndSorter(Object key, boolean isAscending) {
        try {
            Bson sorter = Sorts.descending(getKeyText(key));
            if(isAscending) sorter = Sorts.ascending(getKeyText(key));
            users = getUsersCollection().find().sort(sorter).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    @Override
    public MongoCursor<Document> findBy(Object key, Object value) {
        try {
            Bson filter = Filters.eq(getKeyText(key), value);
            users = getUsersCollection().find(filter).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    private MongoCursor<Document> findByFilterAndProjection(
            String key,
            Object value,
            boolean isVisible,
            String ...keys
    ) {
        try {
            Document projection = getProjection(isVisible, keys);
            Bson filter = Filters.eq(key, value);
            users = getUsersCollection().find(filter).projection(projection).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    @Override
    public MongoCursor<Document> findByJust(Object key, Object value, Object... keys) {
        return findByFilterAndProjection(getKeyText(key), value,
                true, getKeysString(keys));
    }

    @Override
    public MongoCursor<Document> findByExcept(Object key, Object value, Object ...keys) {
        return findByFilterAndProjection(getKeyText(key), value,
                false, getKeysString(keys));
    }

    @Override
    public Document findOneBy(Object key, Object value) {
        try {
            Bson filter = new Document(getKeyText(key), value);
            user = getUsersCollection().find(filter).first();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return user;
    }

    @Override
    public Document findOneById(String id) {
        return findOneBy(UserKeys.ID, new ObjectId(id));
    }

    @Override
    public void insertOne(Object object) {
        try {
            Document userToSave = getSchema(object);
            getUsersCollection().insertOne(userToSave);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void insertMany(Object... objects) {
        try {
            ArrayList<Document> usersSchema = new ArrayList<>();
            for (Object object : objects)
                usersSchema.add(getSchema(object));
            getUsersCollection().insertMany(usersSchema);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByAndUpdateMany(String key, Object value, Bson schema) {
        try {
            Bson filter = Filters.eq(key, value);
            Bson updates = Updates.combine(new Document("$set", schema));
            getUsersCollection().updateOne(filter, updates);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByIdAndUpdateMany(String id, Bson schema) {
        findByAndUpdateMany("_id", new ObjectId(id), schema);
    }

    @Override
    public void findByAndUpdateOne(String key, Object value, String keyToUpdate, Object valueToUpdate) {
        try {
            Bson filter = Filters.eq(key, value);
            Bson updates = Updates.combine(Updates.set(keyToUpdate, valueToUpdate));
            getUsersCollection().updateOne(filter, updates);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByIdAndUpdateOne(String id, String keyToUpdate, Object valueToUpdate) {
        findByAndUpdateOne("_id", new ObjectId(id), keyToUpdate, valueToUpdate);
    }

    @Override
    public void findByAndDelete(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
            getUsersCollection().deleteOne(filter);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByIdAndDelete(String id) {
        findByAndDelete("_id", new ObjectId(id));
    }

}
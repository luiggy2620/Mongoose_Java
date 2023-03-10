package com.example.mongoose_java.database.operations;

import com.example.mongoose_java.database.CRUD.*;
import com.example.mongoose_java.database.schemaKeys.PostKeys;
import com.example.mongoose_java.database.schemaKeys.Schemaable;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.example.mongoose_java.model.Post;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostOperations extends Operations implements Get, Put, Delete,
        com.example.mongoose_java.database.CRUD.Post {

    private Document post;
    private MongoCursor<Document> posts;
    public static PostOperations postOperations;

    public static PostOperations getPostOperations() {
        if(postOperations == null)
            postOperations = new PostOperations();
        return postOperations;
    }

    public PostOperations() {
        this.posts = null;
        this.post = null;
    }

    @Override
    protected Document getSchema(Object object) {
        Post post = (Post) object;
        return new Document("_id", new ObjectId())
                .append("description", post.getDescription())
                .append("dateCreated", post.getDateCreated())
                .append("likes", post.getLikes())
                .append("idUser", post.getIdUser());
    }

    private String[] getKeysString(Object ...keys) {
        String[] keysString = new String[keys.length];
        int i = 0;

        for (Object key : keys) {
            keysString[i] = ((PostKeys) key).toText();
            i++;
        }

        return keysString;
    }

    private String getKeyText(Object key) {
        return ((Schemaable) key).toText();
    }

    private List<Bson> getPipeline() {
        return Arrays.asList(
                Aggregates.lookup("users", "idUser", "_id", "user"),
                Aggregates.unwind("$user"),
                Aggregates.project(Projections.fields(
                        Projections.include(
                                "_id",
                                "description",
                                "dateCreated",
                                "likes",
                                "idUser"),
                        Projections.computed(
                                "username", "$user.username"
                        )
                ))
        );
    }

    private List<Bson> getPipelineWithFilter(Bson filter) {
        return Arrays.asList(
                Aggregates.lookup("users", "idUser", "_id", "user"),
                Aggregates.unwind("$user"),
                Aggregates.project(Projections.fields(
                        Projections.include(
                                "_id",
                                "description",
                                "dateCreated",
                                "likes",
                                "idUser"),
                        Projections.computed(
                                "username", "$user.username"
                        )
                )),
                Aggregates.match(filter)
        );
    }

    @Override
    public MongoCursor<Document> findAll() {
        try {
            List<Bson> pipeline = getPipeline();
            posts = getPostsCollection().aggregate(pipeline).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return posts;
    }

    @Override
    public MongoCursor<Document> findBy(Object key, Object value) {
        try {
            Bson filter = Filters.eq(getKeyText(key), value);
            List<Bson> pipeline = getPipelineWithFilter(filter);
            posts = getPostsCollection().aggregate(pipeline).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return posts;
    }

    private MongoCursor<Document> findByFilterAndProjection(
            String key,
            Object value,
            boolean isVisible,
            String ...keys
    ) {
        try {
            Bson filter = Filters.eq(key, value);
            Document projection = getProjection(isVisible, keys);
            posts = getPostsCollection().find(filter).projection(projection).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return posts;
    }

    @Override
    public MongoCursor<Document> findByExcept(Object key, Object value, Object... keys) {
        return findByFilterAndProjection(getKeyText(key), value,
                false, getKeysString(keys));
    }

    @Override
    public MongoCursor<Document> findByJust(Object key, Object value, Object... keys) {
        return findByFilterAndProjection(getKeyText(key), value,
                true, getKeysString(keys));
    }

    private MongoCursor<Document> findByProjection(boolean isVisible, String ...keys) {
        try {
            Document projection = getProjection(isVisible, keys);
            posts = getPostsCollection().find().projection(projection).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return posts;
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
            String keyText = getKeyText(key);
            Bson sorter = Sorts.descending(keyText);
            if (isAscending) sorter = Sorts.ascending(keyText);
            posts = getPostsCollection().find().sort(sorter).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return null;
    }

    @Override
    public Document findOneBy(Object key, Object value) {
        try {
            Bson filter = Filters.eq(getKeyText(key), value);
            post = getPostsCollection().find(filter).first();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return post;
    }

    @Override
    public Document findOneById(String id) {
        return findOneBy("_id", new ObjectId(id));
    }

    @Override
    public void insertOne(Object object) {
        try {
            Document postToSave = getSchema(object);
            getPostsCollection().insertOne(postToSave);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void insertMany(Object... objects) {
        try {
            ArrayList<Document> postsToSave = new ArrayList<>();
            for (Object post : objects)
                postsToSave.add(getSchema(post));
            getPostsCollection().insertMany(postsToSave);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByAndUpdateMany(String key, Object value, Bson schema) {
        try {
            Bson filter = Filters.eq(key, value);
            Bson updates = Updates.combine(new Document("$set", schema));
            getPostsCollection().updateOne(filter, updates);
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
            Bson toUpdate = Updates.combine(Updates.set(keyToUpdate, valueToUpdate));
            getPostsCollection().updateOne(filter, toUpdate);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByIdAndUpdateOne(String id, String keyToUpdate, Object valueToUpdate) {
        findByAndUpdateOne("_id", id, keyToUpdate, valueToUpdate);
    }

    @Override
    public void findByAndDelete(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
            getPostsCollection().deleteOne(filter);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByIdAndDelete(String id) {
        findByAndDelete("_id", new ObjectId(id));
    }
}
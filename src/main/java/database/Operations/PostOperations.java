package database.Operations;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import database.CRUD.*;
import model.Post;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class PostOperations extends Operations implements Get, database.CRUD.Post, Put, Delete {

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
                .append("title", post.getTitle())
                .append("description", post.getDescription())
                .append("dateCreated", post.getDateCreated())
                .append("likes", post.getLikes())
                .append("idUser", post.getIdUser());
    }

    @Override
    public MongoCursor<Document> find() {
        try {
            posts = getPostsCollection().find().iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return posts;
    }

    @Override
    public MongoCursor<Document> findBy(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
            posts = getPostsCollection().find(filter).iterator();
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
    public MongoCursor<Document> findByAllExcept(String key, Object value, String... keys) {
        return findByFilterAndProjection(key, value, false, keys);
    }

    @Override
    public MongoCursor<Document> findByJust(String key, Object value, String... keys) {
        return findByFilterAndProjection(key, value, true, keys);
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
    public MongoCursor<Document> findAllExcept(String... keys) {
        return findByProjection(false, keys);
    }

    @Override
    public MongoCursor<Document> findJust(String... keys) {
        return findByProjection(true, keys);
    }

    @Override
    public MongoCursor<Document> findAndSorter(String key, boolean isAscending) {
        try {
            Bson sorter = Sorts.descending(key);
            if (isAscending) sorter = Sorts.ascending(key);
            posts = getPostsCollection().find().sort(sorter).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return null;
    }

    @Override
    public Document findOneBy(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
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
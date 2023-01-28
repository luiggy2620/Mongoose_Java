package database.Operations;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import database.CRUD.*;
import model.Post;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class PostOperations extends Operations implements Get, database.CRUD.Post {

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
            posts = getPostCollection().find().iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return posts;
    }

    @Override
    public MongoCursor<Document> findBy(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
            posts = getPostCollection().find(filter).iterator();
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
            posts = getPostCollection().find(filter).projection(projection).iterator();
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
            posts = getPostCollection().find().projection(projection).iterator();
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
            posts = getPostCollection().find().sort(sorter).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return null;
    }

    @Override
    public Document findOneBy(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
            post = getPostCollection().find(filter).first();
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
            getPostCollection().insertOne(getSchema(postToSave));
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
            getPostCollection().insertMany(postsToSave);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }
}
package database.Operations;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import database.CRUD.Get;
import database.CRUD.Post;
import database.CRUD.Put;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class UserOperations extends Operations implements Get, Post, Put {

    private MongoCursor<Document> users;
    private Document user;
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

    private Document getUserSchema(User user) {
        return new Document("_id", new ObjectId())
                .append("name", user.getName())
                .append("userName", user.getUserName())
                .append("email", user.getEmail())
                .append("password", user.getPassword())
                .append("isPublic", user.isPublic())
                .append("posts", user.getPosts());
    }

    private Document getProjection(boolean isVisible, String... keys) {
        int visible = 0;
        if (isVisible) visible = 1;
        Document projection = new Document();
        for (String key : keys)
            projection.append(key, visible);
        return projection;
    }

    @Override
    public MongoCursor<Document> find() {
        try {
            users = getUsersCollection().find().iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    @Override
    public MongoCursor<Document> findAllExcept(String... keys) {
        try {
            Document projection = getProjection(false, keys);
            users = getUsersCollection().find().projection(projection).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    @Override
    public MongoCursor<Document> findJust(String... keys) {
        try {
            Document projection = getProjection(true, keys);
            users = getUsersCollection().find().projection(projection).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    @Override
    public MongoCursor<Document> findAndSorter(String key, boolean isAscending) {
        try {
            Bson sortType = Sorts.ascending(key);
            if(!isAscending) sortType = Sorts.descending(key);
            users = getUsersCollection().find().sort(sortType).iterator();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return users;
    }

    @Override
    public MongoCursor<Document> findBy(String key, Object value) {
        try {
            Bson filter = Filters.eq(key, value);
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
    public MongoCursor<Document> findByJust(String key, Object value, String... keys) {
        return findByFilterAndProjection(key, value, true, keys);
    }

    @Override
    public MongoCursor<Document> findByAllExcept(String key, Object value, String ...keys) {
        return findByFilterAndProjection(key, value, false, keys);
    }

    @Override
    public Document findOneBy(String key, Object value) {
        try {
            Bson filter = new Document(key, value);
            user = getUsersCollection().find(filter).first();
        } catch (MongoException exception) {
            System.out.println(exception);
        }
        return user;
    }

    @Override
    public Document findOneById(String id) {
        return findOneBy("_id", new ObjectId(id));
    }

    @Override
    public void insertOne(Object object) {
        try {
            User userToSave = (User) object;
            getUsersCollection().insertOne(getUserSchema(userToSave));
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void insertMany(Object... objects) {
        try {
            ArrayList<Document> usersSchema = new ArrayList<>();
            for (Object object : objects)
                usersSchema.add(getUserSchema((User) object));
            getUsersCollection().insertMany(usersSchema);
        } catch (MongoException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void findByAndUpdateMany(String key, Object value, Bson schema) {
        try {
            Bson filter = new Document(key, value);
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
            Bson filter = new Document(key, value);
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
}
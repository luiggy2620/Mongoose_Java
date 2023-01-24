package database.Operations;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCursor;
import database.CRUD.Get;
import database.CRUD.Post;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class UserOperations extends Operations implements Get, Post {

    public static UserOperations userOperations;

    public static UserOperations getUserOperations() {
        if(userOperations == null)
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

    @Override
    public MongoCursor<Document> find() {
        return getUsersCollection().find().iterator();
    }

    @Override
    public MongoCursor<Document> findAllExcept(String... keys) {
        Document projection = new Document();
        for (String key : keys)
            projection.append(key, 0);
        return getUsersCollection().find().projection(projection).iterator();
    }

    @Override
    public MongoCursor<Document> findJust(String... keys) {
        return null;
    }

    @Override
    public MongoCursor<Document> findAndSorter(String key, Object value) {
        return null;
    }

    @Override
    public Document findBy(String key, Object value) {
        return null;
    }

    @Override
    public Document findById(String id) {
        return null;
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
}
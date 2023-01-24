package database.Operations;

import com.mongodb.client.MongoCursor;
import database.CRUD.Get;
import org.bson.Document;

public class UserOperations extends Operations implements Get {

    public static UserOperations userOperations;

    public static UserOperations getUserOperations() {
        if(userOperations == null)
            userOperations = new UserOperations();
        return userOperations;
    }

    @Override
    public MongoCursor<Document> find() {
        return null;
    }

    @Override
    public MongoCursor<Document> findAllExcept(String... keys) {
        return null;
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
}
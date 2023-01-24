package database.CRUD;

import com.mongodb.client.MongoCursor;
import org.bson.Document;

public interface Get {

    public MongoCursor<Document> find();

    public MongoCursor<Document> findAllExcept(String ...keys);

    public MongoCursor<Document> findJust(String ...keys);

    public MongoCursor<Document> findAndSorter(String key, Object value);

    public Document findBy(String key, Object value);

    public Document findById(String id);
}
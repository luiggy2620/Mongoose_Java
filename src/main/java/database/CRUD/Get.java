package database.CRUD;

import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.concurrent.CompletableFuture;

public interface Get {

    public MongoCursor<Document> find();

    public MongoCursor<Document> findBy(String key, Object value);

    public MongoCursor<Document> findByAllExcept(String key, Object value, String ...keys);

    public MongoCursor<Document> findByJust(String key, Object value, String ...keys);

    public MongoCursor<Document> findAllExcept(String ...keys);

    public MongoCursor<Document> findJust(String ...keys);

    public MongoCursor<Document> findAndSorter(String key, boolean isAscending);

    public Document findOneBy(String key, Object value);

    public Document findOneById(String id);
}
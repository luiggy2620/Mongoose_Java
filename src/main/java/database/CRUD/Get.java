package database.CRUD;

import com.mongodb.client.MongoCursor;
import org.bson.Document;

public interface Get {

    public MongoCursor<Document> findAll();

    public MongoCursor<Document> findBy(Object key, Object value);

    public MongoCursor<Document> findByExcept(Object key, Object value, Object ...keys);

    public MongoCursor<Document> findByJust(Object key, Object value, Object ...keys);

    public MongoCursor<Document> findAllJust(Object ...keys);

    MongoCursor<Document> findAllExcept(Object... keys);

    public MongoCursor<Document> findAllAndSorter(Object key, boolean isAscending);

    public Document findOneBy(Object key, Object value);

    public Document findOneById(String id);

}
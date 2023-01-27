package database.CRUD;

import org.bson.Document;
import org.bson.conversions.Bson;

public interface Put {

    public void findByAndUpdateMany(String key, Object value, Bson schema);

    public void findByIdAndUpdateMany(String id, Bson schema);

    public void findByIdAndUpdateOne(String id, String keyToUpdate, Object valueToUpdate);

    public void  findByAndUpdateOne(String key, Object value, String keyToUpdate, Object valueToUpdate);

}
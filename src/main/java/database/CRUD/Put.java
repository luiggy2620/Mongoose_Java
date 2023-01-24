package database.CRUD;

import org.bson.conversions.Bson;

public interface Put {

    public void findByAndUpdate(String key, Object value, Bson schema);

    public void findByIdAndUpdate(String id, Bson schema);
}
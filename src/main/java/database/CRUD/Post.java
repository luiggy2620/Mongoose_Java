package database.CRUD;

import org.bson.conversions.Bson;

public interface Post {

    public void save(Bson schema);
}
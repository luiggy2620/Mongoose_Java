package database.CRUD;

public interface Post {

    public void insertOne(Object object);

    public void insertMany(Object ...objects);
}
package com.example.mongoose_java.database.CRUD;

public interface Delete {

    public void findByAndDelete(String key, Object value);

    public void findByIdAndDelete(String id);
}
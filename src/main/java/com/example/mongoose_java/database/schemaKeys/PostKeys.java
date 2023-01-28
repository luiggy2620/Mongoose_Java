package com.example.mongoose_java.database.schemaKeys;

public enum PostKeys {

    ID("_id"),
    TITLE("title"),
    DESCRIPTION("description"),
    DATE_CREATED("dateCreated"),
    LIKES("likes"),
    ID_USER("idUser");

    private final String text;

    PostKeys(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
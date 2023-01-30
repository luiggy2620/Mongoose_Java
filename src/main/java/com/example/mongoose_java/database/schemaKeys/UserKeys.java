package com.example.mongoose_java.database.schemaKeys;

public enum UserKeys {

    ID("_id"),
    NAME("name"),
    USERNAME("userName"),
    EMAIL("email"),
    PASSWORD("password"),
    PUBLIC_STATUS("isPublic"),
    POSTS("posts"),
    FOLLOWERS("followers"),
    FOLLOWING("following");

    private final String text;

    UserKeys (String text) {
        this.text = text;
    }

    public String toText() {
        return text;
    }


}
package com.example.mongoose_java.database.schemaKeys;

public enum UserKeys implements Schemaable{

    ID("_id"),
    NAME("name"),
    USERNAME("username"),
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

    @Override
    public String toText() {
        return text;
    }


}
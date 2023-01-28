package database.schemaKeys;

public enum UserKeys {

    ID("_id"),
    NAME("name"),
    USERNAME("userName"),
    EMAIL("email"),
    PASSWORD("password"),
    PUBLIC_STATUS("isPublic"),
    POSTS("posts");

    private final String text;

    UserKeys (String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


}
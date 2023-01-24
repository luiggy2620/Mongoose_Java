import database.Connection;

public class Main {
    public static void main(String[] args) {
        Connection.getConnection().getMongodb();
    }
}
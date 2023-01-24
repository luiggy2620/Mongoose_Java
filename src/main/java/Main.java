import database.Operations.UserOperations;
import model.User;


public class Main {
    public static void main(String[] args) {

        UserOperations userOperations = UserOperations.getUserOperations();

        User userPrivate = new User("private", "private923", "private@gmail.com", "1234");
        userPrivate.setPublic(false);
        userOperations.insertMany(
                new User("nose", "ghnose", "nose@gmail.com", "1234"),
                new User("user", "user02", "user@gmail.com", "1234"),
                userPrivate
        );

        /*MongoCursor<Document> users = userOperations.findAllExcept("name", "password");*/
    }
}
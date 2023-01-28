import com.mongodb.client.MongoCursor;
import database.operations.PostOperations;
import database.operations.UserOperations;
import database.schemaKeys.PostKeys;
import database.schemaKeys.UserKeys;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {

        UserOperations userOperations = UserOperations.getUserOperations();
        MongoCursor<Document> users = userOperations.findByJust(UserKeys.PASSWORD, "1234",
                UserKeys.NAME, UserKeys.EMAIL, UserKeys.USERNAME);

        users.forEachRemaining(user -> {
            System.out.println(user.toJson());
        });

        PostOperations postOperations = PostOperations.getPostOperations();
        MongoCursor<Document> posts = postOperations.findByExcept(PostKeys.DESCRIPTION, "some description",
                PostKeys.LIKES, PostKeys.ID_USER, PostKeys.ID);

        posts.forEachRemaining(post -> {
            System.out.println(post.toJson());
        });
    }
}
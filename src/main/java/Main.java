import database.Operations.PostOperations;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {


        PostOperations postOperations = PostOperations.getPostOperations();
        postOperations.findByAndDelete("title", "post 3");

    }
}
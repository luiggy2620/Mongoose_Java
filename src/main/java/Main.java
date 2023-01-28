import database.Operations.PostOperations;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {


        PostOperations postOperations = PostOperations.getPostOperations();
        postOperations.findByAndUpdateMany("title", "post 2",
                new Document()
                        .append("title", "post 2 updated")
                        .append("description", "some description updated")
        );


    }
}
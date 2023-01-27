import database.Operations.UserOperations;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {

        UserOperations userOperations = UserOperations.getUserOperations();

        userOperations.findByIdAndDelete("63d0502545b1570f993c1e2e");

    }
}
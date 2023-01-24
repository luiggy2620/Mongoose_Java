package database;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public class Connection {

    private static Connection connection;
    private static MongoClient mongodb;

    public static Connection getConnection() {
        if (connection == null) {
            connection = new Connection();
        }
        return connection;
    }

    public MongoClient getMongodb() {
        if (mongodb == null) {
            try {
                Dotenv dotenv = Dotenv.load();
                ConnectionString connectionString =
                        new ConnectionString(Objects.requireNonNull(dotenv.get("MONGO_URI")));
                MongoClientSettings settings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                        .build();
                mongodb = MongoClients.create(settings);
                System.out.println("successful connection");
            } catch (MongoException exception) {
                System.out.println("fail connection");
            }
        }
        return mongodb;
    }
}
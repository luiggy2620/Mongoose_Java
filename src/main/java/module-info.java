module com.example.mongoose_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires java.dotenv;


    opens com.example.mongoose_java to javafx.fxml;
    exports com.example.mongoose_java;
    exports com.example.mongoose_java.controller;
    opens com.example.mongoose_java.controller to javafx.fxml;
}
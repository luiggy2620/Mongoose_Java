package com.example.mongoose_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent app = FXMLLoader.load(getClass().getResource("posts.fxml"));
        Scene scene = new Scene(app, 1000, 700);

        stage.setTitle("Social Media");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
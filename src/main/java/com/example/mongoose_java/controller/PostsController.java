package com.example.mongoose_java.controller;

import com.example.mongoose_java.App;
import com.example.mongoose_java.components.PostComponent;
import com.example.mongoose_java.database.operations.PostOperations;
import com.example.mongoose_java.database.schemaKeys.PostKeys;
import com.example.mongoose_java.database.schemaKeys.UserKeys;
import com.mongodb.client.MongoCursor;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class PostsController {

    @FXML private GridPane mainContainer;
    @FXML private VBox menuContainer;
    @FXML private FlowPane postsContainer;
    @FXML private TextField toSearchInput;
    private static String valueToSearch = "";
    private static MongoCursor<Document> posts;
    private Collection<VBox> postsFX;
    private PostOperations postOperations;
    private PostComponent postComponent;

    public void initialize() {
        toSearchInput.setText(valueToSearch);
        initializeVariables();
        loadPostsFX();
        addEventToInput();
    }

    private void loadDefaultPosts() {
        posts = postOperations.findAll();
    }

    private void addEventToInput() {
        toSearchInput.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                searchByUsername(event);
            }
        });
    }

    @FXML
    public void searchByUsername(Event event) {
        if (toSearchInput.getText() != null) {
            valueToSearch = toSearchInput.getText();
            MongoCursor<Document> newPosts = postOperations.findBy(
                    UserKeys.USERNAME, valueToSearch);
            if (newPosts.hasNext())
                posts = newPosts;
            else loadDefaultPosts();
            reload(event);
        }
    }

    private void initializeVariables() {
        postOperations = PostOperations.getPostOperations();
        postsFX = new ArrayList<>();
    }

    private void loadPostsFX() {
        if (posts != null) {
            posts.forEachRemaining(post -> {
                postComponent = new PostComponent(
                        (post.get(PostKeys.ID.toText())).toString(),
                        (post.get(PostKeys.ID_USER.toText())).toString(),
                        false
                );

                postsFX.add(postComponent.getPostComponent(post));
            });
            postsContainer.getChildren().addAll(postsFX);
        } else {
            loadDefaultPosts();
            loadPostsFX();
        }
    }

    private void reload(Event event) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("posts.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
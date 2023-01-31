package com.example.mongoose_java.controller;

import com.example.mongoose_java.components.PostComponent;
import com.example.mongoose_java.database.operations.PostOperations;
import com.example.mongoose_java.database.schemaKeys.PostKeys;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collection;

public class PostsController {

    @FXML private GridPane mainContainer;
    @FXML private VBox menuContainer;
    @FXML private FlowPane postsContainer;
    private Collection<VBox> postsFX;
    private PostOperations postOperations;
    private PostComponent postComponent;

    public void initialize() {
        getPostToDatabase();
        postsContainer.getChildren().addAll(postsFX);
    }

    private void getPostToDatabase() {
        if (postOperations != null) {
            postOperations.findAll().forEachRemaining(post -> {
                postComponent = new PostComponent(
                        (post.get(PostKeys.ID.toText())).toString(),
                        (post.get(PostKeys.ID_USER.toText())).toString(),
                        false
                );
                postsFX.add(postComponent.getPostComponent(post));
            });

        } else {
            postOperations = PostOperations.getPostOperations();
            postsFX = new ArrayList<>();
            getPostToDatabase();
        }
    }
}
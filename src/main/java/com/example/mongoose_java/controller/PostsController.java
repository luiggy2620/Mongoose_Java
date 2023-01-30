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
    private PostComponent postComponent;

    public void initialize() {
        Collection<VBox> postsFX = new ArrayList<>();

        PostOperations postOperations = PostOperations.getPostOperations();
        if (postOperations != null) {
            postOperations.findAll().forEachRemaining(post -> {
                postComponent = new PostComponent(
                        (post.get(PostKeys.ID.toText())).toString(),
                        (post.get(PostKeys.ID_USER.toText())).toString()
                );
                postsFX.add(postComponent.getPostComponent(post, false));
            });

            postsContainer.getChildren().addAll(postsFX);
        }
    }
}
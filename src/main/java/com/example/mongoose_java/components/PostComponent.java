package com.example.mongoose_java.components;

import com.example.mongoose_java.database.schemaKeys.PostKeys;
import com.example.mongoose_java.elements.PostElements;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.bson.Document;

import java.util.Date;

public class PostComponent {

    private VBox postComponent;
    private HBox userContainer;
    private HBox likesContainer;
    private Button likeButton;
    private PostElements elements;
    private String idPost;
    private String idUser;

    public PostComponent(String idPost, String idUser) {
        this.idPost = idPost;
        this.idUser = idUser;
        elements = new PostElements();
    }

    public VBox getPostComponent(Document post, boolean isLiked) {
        postComponent = elements.postContainerFX();
        postComponent.getChildren().addAll(
            getUserContainer("User Name"),
            elements.descriptionFX(String.valueOf(post.get(PostKeys.DESCRIPTION.toText()))),
            elements.dateCreatedFX((Date) post.get(PostKeys.DATE_CREATED.toText())),
            getLikesContainer(isLiked, String.valueOf(post.get(PostKeys.LIKES.toText())))
        );

        addFunctionToUserContainer();
        addFunctionToLikeButton();

        return postComponent;
    }

    private HBox getUserContainer(String userName) {
        userContainer = elements.userContainerFX();
        userContainer.getChildren().addAll(
            elements.iconUserFX(),
            elements.userNameFX(userName)
        );
        return userContainer;
    }

    private HBox getLikesContainer(boolean isLiked, String numberLikes) {
        likeButton = elements.likeButtonFX(isLiked);
        likesContainer = elements.likesContainerFX();
        likesContainer.getChildren().addAll(
                likeButton,
                elements.numberLikesFX(numberLikes)
        );
        return likesContainer;
    }

    private void addFunctionToUserContainer() {
        userContainer.setOnMouseClicked(event -> {
            System.out.println(idUser);
        });
    }

    private void addFunctionToLikeButton() {
        likeButton.setOnMouseClicked(event -> {
            System.out.println(idPost);
        });
    }
}
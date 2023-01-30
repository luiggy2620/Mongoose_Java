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
    private String idUser;

    public PostComponent(String idUser) {
        this.idUser = idUser;
        elements = new PostElements(idUser);
    }

    public VBox getPostComponent(Document post, boolean isLiked) {
        postComponent = elements.postContainerFX();
        postComponent.getChildren().addAll(
            getUserContainer(String.valueOf(post.get(PostKeys.TITLE.getText()))),
            elements.descriptionFX(String.valueOf(post.get(PostKeys.DESCRIPTION.getText()))),
            elements.dateCreatedFX((Date) post.get(PostKeys.DATE_CREATED.getText())),
            getLikesContainer(isLiked, String.valueOf(post.get(PostKeys.LIKES.getText())))
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
            System.out.println(idUser);
        });
    }
}
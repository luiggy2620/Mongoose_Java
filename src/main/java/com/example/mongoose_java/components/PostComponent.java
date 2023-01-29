package com.example.mongoose_java.components;

import com.example.mongoose_java.elements.PostElements;
import com.example.mongoose_java.model.Post;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class PostComponent {

    private VBox postComponent;
    private HBox userContainer;
    private HBox likesContainer;
    private PostElements elements;

    public PostComponent() {
        elements = new PostElements();
    }

    public VBox getPostComponent(Post post, boolean isLiked) {
        postComponent = elements.postContainerFX();
        postComponent.getChildren().addAll(
            getUserContainer(post.getTitle()),
            elements.descriptionFX(post.getDescription()),
            elements.dateCreatedFX(post.getDateCreated()),
            getLikesContainer(isLiked, post.getLikes())
        );

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

    private HBox getLikesContainer(boolean isLiked, int numberLikes) {
        likesContainer = elements.likesContainerFX();
        likesContainer.getChildren().addAll(
                elements.likeButtonFX(isLiked),
                elements.numberLikesFX(numberLikes)
        );
        return likesContainer;
    }
}
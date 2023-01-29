package com.example.mongoose_java.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Date;

public class PostElements {

    public VBox postContainerFX() {
        VBox postContainerFX = new VBox();
        postContainerFX.setPrefWidth(Double.MAX_VALUE);
        postContainerFX.setMinHeight(50);
        postContainerFX.setPrefHeight(Control.USE_PREF_SIZE);
        postContainerFX.setAlignment(Pos.CENTER_LEFT);

        postContainerFX.setSpacing(20);
        postContainerFX.setPadding(new Insets(20));

        postContainerFX.getStyleClass().add("postContainer");
        return postContainerFX;
    }

    public HBox userContainerFX() {
        HBox userContainerFX = new HBox();
        userContainerFX.setPrefWidth(Control.USE_PREF_SIZE);
        userContainerFX.setPrefHeight(Control.USE_PREF_SIZE);
        userContainerFX.setPadding(new Insets(15, 0, 15, 0));

        userContainerFX.getStyleClass().add("child");
        return userContainerFX;
    }

    public ImageView iconUserFX() {
        Image icon = new Image("src/main/resources/com/example/mongoose_java/icons/user.png");
        return new ImageView(icon);
    }

    public Label userNameFX(String userName) {
        Label userNameFX = new Label(userName);
        userNameFX.setAlignment(Pos.CENTER_LEFT);

        userNameFX.getStyleClass().add("child");
        return userNameFX;
    }

    public Label descriptionFX(String description) {
        Label descriptionFX = new Label(description);
        descriptionFX.setWrapText(true);
        descriptionFX.setAlignment(Pos.CENTER_LEFT);

        descriptionFX.getStyleClass().add("child");
        return descriptionFX;
    }

    public Label dateCreatedFX(Date dateCreated) {
        Label dateCreatedFX = new Label(dateCreated.toString());
        dateCreatedFX.setAlignment(Pos.CENTER_LEFT);

        dateCreatedFX.getStyleClass().add("child");
        return dateCreatedFX;
    }

    public HBox likesContainerFX() {
        HBox likesContainerFX = new HBox();
        likesContainerFX.setPrefWidth(Control.USE_PREF_SIZE);
        likesContainerFX.setPrefWidth(Control.USE_PREF_SIZE);
        likesContainerFX.setAlignment(Pos.CENTER_LEFT);

        likesContainerFX.setPadding(new Insets(10));
        likesContainerFX.getStyleClass().add("child");
        return likesContainerFX;
    }

    public Button likeButtonFX(boolean isLiked) {
        Button likeButtonFX = new Button();
        likeButtonFX.setPrefWidth(20);
        likeButtonFX.setPrefHeight(20);

        String iconURL = "src/main/resources/com/example/mongoose_java/icons/like.png";
        if (isLiked) iconURL = "src/main/resources/com/example/mongoose_java/icons/liked.png";
        Image image = new Image(iconURL);
        ImageView icon = new ImageView(image);
        likeButtonFX.setGraphic(icon);

        likeButtonFX.getStyleClass().add("child");
        return likeButtonFX;
    }

    public Label numberLikesFX(int numberLikes) {
        Label numberLikesFX = new Label(String.valueOf(numberLikes));
        numberLikesFX.setPrefWidth(Control.USE_PREF_SIZE);
        numberLikesFX.setPrefHeight(20);

        numberLikesFX.getStyleClass().add("child");
        return numberLikesFX;
    }
}
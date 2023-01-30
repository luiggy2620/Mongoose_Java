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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PostElements {

    private String idUser;

    public PostElements(String idUser) {
        this.idUser = idUser;
    }

    public VBox postContainerFX() {
        VBox postContainerFX = new VBox();
        postContainerFX.setPrefWidth(550);
        postContainerFX.setPrefHeight(150);
        postContainerFX.setId(idUser);

        postContainerFX.getStyleClass().add("postContainer");
        return postContainerFX;
    }

    public HBox userContainerFX() {
        HBox userContainerFX = new HBox();
        userContainerFX.setPrefWidth(600);
        userContainerFX.setPrefHeight(Control.USE_PREF_SIZE);
        userContainerFX.setSpacing(10);
        userContainerFX.setPadding(new Insets(0, 0, 10, 0));

        userContainerFX.getStyleClass().addAll("child", "userContainer");
        return userContainerFX;
    }

    public ImageView iconUserFX() {
        ImageView iconUserFX = null;
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/icons/user.png");
            Image icon = new Image(inputStream);
            iconUserFX = new ImageView(icon);
            iconUserFX.setFitWidth(25);
            iconUserFX.setFitHeight(25);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return iconUserFX;
    }

    public Label userNameFX(String userName) {
        Label userNameFX = new Label(userName);
        userNameFX.setAlignment(Pos.CENTER_LEFT);

        userNameFX.getStyleClass().addAll("child", "userName");
        return userNameFX;
    }

    public Label descriptionFX(String description) {
        Label descriptionFX = new Label(description);
        descriptionFX.setWrapText(true);
        descriptionFX.setAlignment(Pos.CENTER_LEFT);

        descriptionFX.getStyleClass().addAll("child", "description");
        return descriptionFX;
    }

    public Label dateCreatedFX(Date dateCreated) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH);
        Label dateCreatedFX = new Label(formatter.format(dateCreated));
        dateCreatedFX.setAlignment(Pos.CENTER_LEFT);

        dateCreatedFX.getStyleClass().addAll("child", "dateCreated");
        return dateCreatedFX;
    }

    public HBox likesContainerFX() {
        HBox likesContainerFX = new HBox();
        likesContainerFX.setPrefWidth(Control.USE_COMPUTED_SIZE);
        likesContainerFX.setPrefWidth(Control.USE_COMPUTED_SIZE);
        likesContainerFX.setAlignment(Pos.CENTER_LEFT);

        likesContainerFX.setPadding(new Insets(10, 0, 0, 0));
        likesContainerFX.getStyleClass().addAll("child", "likesContainer");
        return likesContainerFX;
    }

    public Button likeButtonFX(boolean isLiked) {
        Button likeButtonFX = new Button();
        likeButtonFX.setPrefSize(20, 20);
        likeButtonFX.setMaxSize(20, 20);
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/icons/like.png");
            if (isLiked) inputStream = new FileInputStream("src/main/resources/icons/liked.png");
            Image image = new Image(inputStream);
            ImageView icon = new ImageView(image);
            icon.setPreserveRatio(true);
            icon.setFitWidth(likeButtonFX.getPrefWidth());
            icon.setFitHeight(likeButtonFX.getPrefHeight());
            likeButtonFX.setGraphic(icon);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        likeButtonFX.setId(idUser);
        likeButtonFX.getStyleClass().addAll("child", "likeButton");
        return likeButtonFX;
    }

    public Label numberLikesFX(String numberLikes) {
        Label numberLikesFX = new Label(numberLikes);
        numberLikesFX.setPrefWidth(Control.USE_COMPUTED_SIZE);
        numberLikesFX.setPrefHeight(Control.USE_COMPUTED_SIZE);

        numberLikesFX.getStyleClass().addAll("child", "likes");
        return numberLikesFX;
    }
}
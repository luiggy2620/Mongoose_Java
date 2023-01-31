package com.example.mongoose_java.model;

import org.bson.types.ObjectId;

import java.util.Date;

public class Post {

    private String description;
    private Date dateCreated;
    private int likes;
    private ObjectId idUser;

    public Post(String description, ObjectId idUser) {
        this.description = description;
        this.idUser = idUser;
        this.dateCreated = new Date();
        this.likes = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ObjectId getIdUser() {
        return idUser;
    }

    public void setIdUser(ObjectId idUser) {
        this.idUser = idUser;
    }
}
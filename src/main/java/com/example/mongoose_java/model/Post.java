package com.example.mongoose_java.model;

import java.util.Date;

public class Post {

    private String description;
    private Date dateCreated;
    private int likes;
    private String idUser;

    public Post(String description, String idUser) {
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
package edu.northeastern.movieapi.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Movie {
    private String title;
    private String image;
    private String description;
    private String id;
    private String runtimeStr;
    private String genres;
    private String contentRating;
    private String imDbRating;

  public Movie(String title, String image, String description, String id,String runtimeStr,String genres,String contentRating,String imDbRating) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.id = id;
        this.runtimeStr=runtimeStr;
        this.genres=genres;
        this.contentRating=contentRating;
        this.imDbRating=imDbRating;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }
}


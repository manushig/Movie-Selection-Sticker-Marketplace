package edu.northeastern.movieapi.model;

import androidx.annotation.NonNull;

import java.util.List;

public class MovieDetail {
    private String id;
    private String title;
    private String image;
    private String year;
    private String releaseDate;
    private String awards;
    private String directors;
    private String stars;
    private String genres;
    private String contentRating;
    private String imDbRating;
    private String plot;
    private String runtimeStr;

    public MovieDetail(String id, String title, String image, String year, String releaseDate, String awards, String directors, String stars, String genres, String contentRating, String imDbRating, String plot, String runtimeStr) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.year = year;
        this.releaseDate = releaseDate;
        this.awards = awards;
        this.directors = directors;
        this.stars = stars;
        this.genres = genres;
        this.contentRating = contentRating;
        this.imDbRating = imDbRating;
        this.plot = plot;
        this.runtimeStr = runtimeStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
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

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


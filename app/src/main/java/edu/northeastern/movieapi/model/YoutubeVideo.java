package edu.northeastern.movieapi.model;

public class YoutubeVideo {
    private String imDbId;
    private String title;
  //  private String fullTitle;
   // private String year;
    private String videoId;
    private String videoUrl;

    public YoutubeVideo(String imDbId, String title, String videoId, String videoUrl) {
        this.imDbId = imDbId;
        this.title = title;
        this.videoId = videoId;
        this.videoUrl = videoUrl;
    }

    public String getImDbId() {
        return imDbId;
    }

    public void setImDbId(String imDbId) {
        this.imDbId = imDbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}

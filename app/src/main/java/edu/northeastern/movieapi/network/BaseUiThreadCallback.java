package edu.northeastern.movieapi.network;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;

public class BaseUiThreadCallback implements MovieWebService.UiThreadCallback {
    @Override
    public void onSearchResultGet(List<Movie> movies) {}

    @Override
    public void onDetailGet(MovieDetail movieDetails) {}

    @Override
    public void onVideoGet(YoutubeVideo youtubeVideo) {}

    @Override
    public void onEmptyResult() {}

    @Override
    public void onError() {}
}

package edu.northeastern.movieapi;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.YoutubeVideo;

public interface OnItemActionListener {
    void onClick(Movie movie);
}

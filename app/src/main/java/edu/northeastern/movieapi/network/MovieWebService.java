package edu.northeastern.movieapi.network;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;

public class MovieWebService {

//    private static final String API_KEY = "k_2luv1h1i";k_0gmz1nay
    private static final String API_KEY = "k_2luv1h1i";
    private static final String BASE_URL = "https://imdb-api.com/API/AdvancedSearch/" + API_KEY;
    private static final String DETAIL_SEARCH_URL = "https://imdb-api.com/en/API/Title/";
    private static final String YOUTUBE_SEARCH_URL = "https://imdb-api.com/en/API/YouTubeTrailer/";
    private UiThreadCallback uiThreadCallback;

    private Handler handler;

    public MovieWebService(UiThreadCallback uiThreadCallback) {
        this.uiThreadCallback = uiThreadCallback;
        this.handler = new Handler(Looper.getMainLooper());
    }

    public interface UiThreadCallback {
        void onSearchResultGet(List<Movie> movies);

        void onDetailGet(MovieDetail movieDetails);

        void onVideoGet(YoutubeVideo youtubeVideo);

        void onEmptyResult();

        void onError();
    }

    public void getNowPlaying() {
        String uri = BASE_URL + "?groups=now-playing-us";
        makeNetworkCall(uri);
    }

    public void getSearchResult(String keyword) {
        String uri = BASE_URL + "?" + keyword;
        makeNetworkCall(uri);
    }

    public void makeNetworkCall(String uri) {
        new NetworkThread(uri, new NetworkThread.NetworkCallback() {
            @Override
            public void processResponse(String responseData) {
                List<Movie> movies = handleResponse(responseData);
                postToUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uiThreadCallback.onSearchResultGet(movies);
                    }
                });
            }

            @Override
            public void onError() {
                postErrorToUiThread();
            }

            @Override
            public void onEmptyResult() {
                postEmptyResultToUiThread();
            }
        }).start();
    }

    public void getDetailResult(String movieId) {
        //  movieId ="tt1375666";
        //https://imdb-api.com/en/API/Title/k_0gmz1nay/tt1375666
        String uri = DETAIL_SEARCH_URL + API_KEY + "/" + movieId;
        new NetworkThread(uri, new NetworkThread.NetworkCallback() {
            @Override
            public void processResponse(String responseData) {
                MovieDetail movieDetail = handleDetailResponse(responseData);
                postToUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uiThreadCallback.onDetailGet(movieDetail);
                    }
                });
            }

            @Override
            public void onError() {
                postErrorToUiThread();
            }

            @Override
            public void onEmptyResult() {
                postEmptyResultToUiThread();
            }
        }).start();

    }

    public void getVideo(String movieId) {
        //  movieId ="tt1375666";
        //https://imdb-api.com/en/API/Title/k_0gmz1nay/tt1375666
        String uri = YOUTUBE_SEARCH_URL + API_KEY + "/" + movieId;
        new NetworkThread(uri, new NetworkThread.NetworkCallback() {
            @Override
            public void processResponse(String responseData) {
                YoutubeVideo youtubeVideo = handleYoutubeResponse(responseData);
                postToUiThread(new Runnable() {
                    @Override
                    public void run() {
                        uiThreadCallback.onVideoGet(youtubeVideo);
                    }
                });
            }

            @Override
            public void onError() {
                postErrorToUiThread();
            }

            @Override
            public void onEmptyResult() {
                postEmptyResultToUiThread();
            }
        }).start();

    }

    private void postToUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    private void postErrorToUiThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                uiThreadCallback.onError();
            }
        });
    }

    private void postEmptyResultToUiThread() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                uiThreadCallback.onEmptyResult();
            }
        });
    }

    private List<Movie> handleResponse(String responseData) {
        List<Movie> movies = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONArray moviesArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < moviesArray.length(); i++) {
                JSONObject movieObject = moviesArray.getJSONObject(i);
                String title = movieObject.getString("title");
                String image = movieObject.getString("image");
                String description = movieObject.getString("description");
                String id = movieObject.getString("id");
                String runtimeStr = movieObject.getString("runtimeStr");
                String genres = movieObject.getString("genres");
                String contentRating = movieObject.getString("contentRating");
                String imDbRating = movieObject.getString("imDbRating");
                Movie movie = new Movie(title, image, description, id, runtimeStr, genres, contentRating, imDbRating);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing errors
        }
        return movies;
    }

    private MovieDetail handleDetailResponse(String responseData) {
        MovieDetail movieDetail = null;
        try {
            JSONObject movieObject = new JSONObject(responseData);
            String id = movieObject.getString("id");
            String title = movieObject.getString("title");
            String image = movieObject.getString("image");
            String year = movieObject.getString("year");
            String releaseDate = movieObject.getString("releaseDate");
            String awards = movieObject.getString("awards");
            String directors = movieObject.getString("directors");
            String stars = movieObject.getString("stars");
            String genres = movieObject.getString("genres");
            String contentRating = movieObject.getString("contentRating");
            String imDbRating = movieObject.getString("imDbRating");
            String plot = movieObject.getString("plot");
            String runtimeStr = movieObject.getString("runtimeStr");
            movieDetail = new MovieDetail(id, title, image, year, releaseDate, awards, directors, stars, genres, contentRating, imDbRating, plot, runtimeStr);
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing errors
        }
        return movieDetail;
    }

    private YoutubeVideo handleYoutubeResponse(String responseData) {
        YoutubeVideo youtubeVideo = null;
        try {
            JSONObject movieObject = new JSONObject(responseData);
            String id = movieObject.getString("imDbId");
            String title = movieObject.getString("title");
            String videoId = movieObject.getString("videoId");
            String videoUrl = movieObject.getString("videoUrl");
            youtubeVideo = new YoutubeVideo(id, title,videoId,videoUrl);
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing errors
        }
        return youtubeVideo;
    }
}

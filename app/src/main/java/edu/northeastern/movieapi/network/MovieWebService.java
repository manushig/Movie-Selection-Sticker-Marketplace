package edu.northeastern.movieapi.network;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.model.Movie;

public class MovieWebService {

    private static final String BASE_URL = "https://imdb-api.com/en/API/Search/k_0gmz1nay/";

    private UiThreadCallback uiThreadCallback;

    public  MovieWebService(UiThreadCallback uiThreadCallback) {
        this.uiThreadCallback = uiThreadCallback;
    }

    public interface UiThreadCallback {
        void onSearchResultGet(List<Movie> movies);
    }

    public void getSearchResult(String keyword) {
        String uri = BASE_URL + keyword;
        new NetworkThread(uri, new NetworkThread.NetworkCallback() {
            @Override
            public void processResponse(String responseData) {
                List<Movie> movies = handleResponse(responseData);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        uiThreadCallback.onSearchResultGet(movies);
                    }
                });
            }
        }).start();
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
                Movie movie = new Movie(title, image, description);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing errors
        }
        return movies;
    }
}

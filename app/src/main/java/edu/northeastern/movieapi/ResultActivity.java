package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.MovieWebService;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private MovieWebService movieWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();
        String searchKeyword = intent.getStringExtra("searchKeyword");

        MovieWebService.UiThreadCallback uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "movies size = " + movies.size());
            }

            @Override
            public void onDetailGet(MovieDetail movieDetails) {
                Log.d(TAG, "movieDetails title = " + movieDetails.getTitle());
            }

            @Override
            public void onVideoGet(YoutubeVideo youtubeVideo) {

            }
        };

        movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getSearchResult(searchKeyword);

        progressBar.setVisibility(View.VISIBLE);


        // Move this to Details activity. This is only for testing.
        findViewById(R.id.test_details_call_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieWebService.getDetailResult("tt1375666");
            }
        });
    }


}
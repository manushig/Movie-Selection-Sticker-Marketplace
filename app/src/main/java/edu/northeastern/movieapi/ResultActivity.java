package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.network.MovieWebService;

public class ResultActivity extends AppCompatActivity {
    private MovieWebService.UiThreadCallback uiThreadCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();
        String searchKeyword = intent.getStringExtra("searchKeyword");

        uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                progressBar.setVisibility(View.GONE);
            }
        };
        new MovieWebService(uiThreadCallback).getSearchResult(searchKeyword);
        progressBar.setVisibility(View.VISIBLE);

    }


}
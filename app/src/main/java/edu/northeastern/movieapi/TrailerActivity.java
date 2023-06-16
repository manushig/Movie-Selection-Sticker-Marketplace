package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.adapters.TrailerAdapter;
import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.MovieWebService;

public class TrailerActivity extends AppCompatActivity {
    TrailerAdapter trailerAdapter;
    private MovieWebService movieWebService;
    private OnItemActionListener onItemActionListener;
    Context context;


    List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        ProgressBar progressBar = findViewById(R.id.progressBar2);
        initializeItemActionListener();
        trailerAdapter = new TrailerAdapter(movieList, onItemActionListener);
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trailerAdapter);


        MovieWebService.UiThreadCallback uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                movieList.addAll(movies);
                trailerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDetailGet(MovieDetail movieDetails) {

            }

            @Override
            public void onVideoGet(YoutubeVideo youtubeVideo) {

            }

        };

        movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getNowPlaying();
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initializeItemActionListener() {
        onItemActionListener = new OnItemActionListener() {
            @Override
            public void onClick(Movie movie) {
                Intent intent = new Intent(TrailerActivity.this, VideoActivity.class);
                intent.putExtra("movieId",movie.getId());
                startActivity(intent);
            }
        };
    }

}
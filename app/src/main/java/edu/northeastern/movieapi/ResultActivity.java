package edu.northeastern.movieapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.adapters.MovieListAdapter;
import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.MovieWebService;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private MovieWebService movieWebService;

    private RecyclerView recyclerView;
    private MovieListAdapter movieListAdapter;
    private List<Movie> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();
        String searchKeyword = intent.getStringExtra("searchQueryPath");

        MovieWebService.UiThreadCallback uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "movies size = " + movies.size());
                moviesList.clear();
                moviesList.addAll(movies);
                movieListAdapter.notifyDataSetChanged();
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

        buildRecyclerView();
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewMovies);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesList = new ArrayList<>();


        movieListAdapter = new MovieListAdapter(moviesList);

        recyclerView.setAdapter(movieListAdapter);

        movieListAdapter.setOnItemClickListener(new MovieListAdapter.OnItemClickListener() {

            @Override
            public void onImageClick(int position) {
                launchTrailer(position);
            }

            @Override
            public void onItemClick(int position) {
                launchItem(position);
            }
        });
    }



    public void launchItem(int position) {

        String selectedId = ((Movie) moviesList.get(position)).getId();

        if (selectedId.equals("") || selectedId.equals(null)) {
            Toast.makeText(this, R.string.invalid_id, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
            intent.putExtra("movieId", selectedId);
            startActivity(intent);
        }
    }

    public void launchTrailer(int position) {

        String selectedId = ((Movie) moviesList.get(position)).getId();

        Intent intent = new Intent(ResultActivity.this, VideoActivity.class);
        intent.putExtra("movieId",selectedId);
        startActivity(intent);

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current scroll position
        int scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                .findFirstCompletelyVisibleItemPosition();
        outState.putInt("SCROLL_POSITION", scrollPosition);
    }

}
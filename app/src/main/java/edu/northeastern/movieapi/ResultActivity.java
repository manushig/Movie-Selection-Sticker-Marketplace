package edu.northeastern.movieapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.northeastern.movieapi.adapters.MovieListAdapter;
import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.BaseUiThreadCallback;
import edu.northeastern.movieapi.network.MovieWebService;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private MovieWebService movieWebService;

    private RecyclerView recyclerView;
    private MovieListAdapter movieListAdapter;
    private List<Movie> moviesList;

    private Map<String, Movie> selectedMovies;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        Intent intent = getIntent();
        String searchKeyword = intent.getStringExtra("searchQueryPath");

        gson = new Gson();

        MovieWebService.UiThreadCallback uiThreadCallback = new BaseUiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "movies size = " + movies.size());
                moviesList.clear();
                moviesList.addAll(movies);
                movieListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError() {
                progressBar.setVisibility(View.GONE);
                AlertDialogHelper.showErrorDialog(ResultActivity.this);
            }

            @Override
            public void onEmptyResult() {
                progressBar.setVisibility(View.GONE);
                AlertDialogHelper.showEmptyResult(ResultActivity.this);
            }
        };

        movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getSearchResult(searchKeyword);

        progressBar.setVisibility(View.VISIBLE);

        selectedMovies = getSelectedMoviesFromSharedPreferences();

        buildRecyclerView();
    }


    private Map<String, Movie> getSelectedMoviesFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteMovies", MODE_PRIVATE);
        Set<String> selectedMoviesJsonSet = sharedPreferences.getStringSet("SelectedMovies", new HashSet<>());
        Map<String, Movie> selectedMoviesMap = new HashMap<>();

        for (String selectedMovieJson : selectedMoviesJsonSet) {
            Movie movie = gson.fromJson(selectedMovieJson, Movie.class);
            selectedMoviesMap.put(movie.getId(), movie);
        }

        return selectedMoviesMap;
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewMovies);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        moviesList = new ArrayList<>();

        movieListAdapter = new MovieListAdapter(moviesList, this, selectedMovies);

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

            @Override
            public void onFavoriteClick(int position) {
                toggleFavorite(position);
            }
        });
    }

    public void toggleFavorite(int position) {
        Movie movie = moviesList.get(position);
        String movieId = movie.getId();

        if (selectedMovies.containsKey(movieId)) {
            selectedMovies.remove(movieId);
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        } else {
            selectedMovies.put(movieId, movie);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }

        saveSelectedMoviesToSharedPreferences();
    }

    private void saveSelectedMoviesToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("FavoriteMovies", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String selectedMoviesJson = gson.toJson(selectedMovies.values());
        editor.putString("SelectedMovies", selectedMoviesJson);
        editor.apply();
    }

    public void launchItem(int position) {
        String selectedId = moviesList.get(position).getId();
        if (selectedId.equals("") || selectedId.equals(null)) {
            Toast.makeText(this, R.string.invalid_id, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
            intent.putExtra("movieId", selectedId);
            startActivity(intent);
        }
    }

    public void launchTrailer(int position) {
        String selectedId = moviesList.get(position).getId();
        Intent intent = new Intent(ResultActivity.this, VideoActivity.class);
        intent.putExtra("movieId", selectedId);
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

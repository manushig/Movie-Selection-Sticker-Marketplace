package edu.northeastern.movieapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    private List<Movie> movies;

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

        buildRecyclerView();


/*        // Move this to Details activity. This is only for testing.
        findViewById(R.id.test_details_call_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieWebService.getDetailResult("tt1375666");
            }
        });*/
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewMovies);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = getMovieList();


        movieListAdapter = new MovieListAdapter(movies);

        recyclerView.setAdapter(movieListAdapter);

        movieListAdapter.setOnItemClickListener(new MovieListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                launchItem(position);
            }
        });
    }

    private List<Movie> getMovieList() {
        List<Movie> movies = new ArrayList<>();


        // Add movies to the list
        movies.add(new Movie("Spider-Man: Into the Spider-Verse", "https://m.media-amazon.com/images/M/MV5BNmMzODkwNDktMTkyMy00MmU5LWE4MGMtYzIzZjdjNmJiZDRiXkEyXkFqcGdeQXVyNDU1NDQ0NzE@._V1_Ratio0.7041_AL_.jpg", "1h 57m", "PG", "8.4", "1"));
        movies.add(new Movie("The Flash", "https://m.media-amazon.com/images/M/MV5BMDY0MGJlYzktNzZiNS00MWZhLWJjNWEtNmJmZTg3NjMwZDYwXkEyXkFqcGdeQXVyMTUzOTcyODA5._V1_Ratio0.6837_AL_.jpg", "2h 24m", "PG-13", "", "2"));
        movies.add(new Movie("Avatar: The Way of Water", "https://imdb-api.com/images/original/nopicture.jpg", "3h 12m", "PG-13", "7.7", "3"));
        movies.add(new Movie("Fast X",
                "https://imdb-api.com/images/original/nopicture.jpg", "2h 21m", "PG-13", "6.1", "4"));
        movies.add(new Movie("Avatar: The Way of Water", "https://imdb-api.com/images/original/nopicture.jpg", "3h 12m", "PG-13", "7.7", "3"));
        movies.add(new Movie("Fast X",
                "https://imdb-api.com/images/original/nopicture.jpg", "2h 21m", "PG-13", "6.1", "4"));
        movies.add(new Movie("Avatar: The Way of Water", "https://imdb-api.com/images/original/nopicture.jpg", "3h 12m", "PG-13", "7.7", "3"));
        movies.add(new Movie("Fast X",
                "https://imdb-api.com/images/original/nopicture.jpg", "2h 21m", "PG-13", "6.1", "4"));
        // Add more movies as needed

        return movies;
    }


    public void launchItem(int position) {

        String selectedId = ((Movie) movies.get(position)).getId();

        if (selectedId.equals("") || selectedId.equals(null)) {
            Toast.makeText(this, R.string.invalid_id, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ResultActivity.this, DetailsActivity.class);
            intent.putExtra("movieId", selectedId);
            startActivity(intent);
        }
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
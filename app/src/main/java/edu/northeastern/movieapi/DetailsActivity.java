package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.MovieWebService;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    private MovieWebService movieWebService;
    private TextView textViewTitle;
    private TextView textViewId;
    private TextView textViewYear;
    private TextView textViewReleaseDate;
    private TextView textViewAwards;
    private TextView textViewDirectors;
    private TextView textViewStars;
    private TextView textViewGenres;
    private TextView textViewContentRating;
    private TextView textViewImdbRating;
    private TextView textViewPlot;
    private TextView textViewRuntime;
    private ImageView imageViewMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);
        Intent intent = getIntent();
        String searchKeyword = intent.getStringExtra("movieId");
        MovieWebService.UiThreadCallback uiThreadCallback = new MovieWebService.UiThreadCallback() {


            @Override
            public void onSearchResultGet(List<Movie> movies) {

            }

            @Override
            public void onDetailGet(MovieDetail movieDetails) {

                imageViewMovie = findViewById(R.id.imgView);
                Glide.with(DetailsActivity.this)
                        .load(movieDetails.getImage())
                        .into(imageViewMovie);

                textViewTitle = (TextView) findViewById(R.id.textView4);
                textViewTitle.setText(movieDetails.getTitle());

                textViewYear = (TextView) findViewById(R.id.textView6);
                textViewYear.setText(movieDetails.getYear());

                textViewReleaseDate = (TextView) findViewById(R.id.textView8);
                textViewReleaseDate.setText(movieDetails.getReleaseDate());

                textViewAwards = (TextView) findViewById(R.id.textView10);
                textViewAwards.setText(movieDetails.getAwards());

                textViewDirectors = (TextView) findViewById(R.id.textView12);
                textViewDirectors.setText(movieDetails.getDirectors());

                textViewStars = (TextView) findViewById(R.id.textView14);
                textViewStars.setText(movieDetails.getStars());

                textViewGenres = (TextView) findViewById(R.id.textView16);
                textViewGenres.setText(movieDetails.getGenres());

                textViewContentRating = (TextView) findViewById(R.id.textView18);
                textViewContentRating.setText(movieDetails.getContentRating());

                textViewImdbRating = (TextView) findViewById(R.id.textView20);
                textViewImdbRating.setText(movieDetails.getImDbRating());

                textViewPlot = (TextView) findViewById(R.id.textView22);
                textViewPlot.setText(movieDetails.getPlot());

                textViewRuntime = (TextView) findViewById(R.id.textView24);
                textViewRuntime.setText(movieDetails.getRuntimeStr());


                Log.d(TAG, "movieDetails title = " + movieDetails.getTitle());
            }

            @Override
            public void onVideoGet(YoutubeVideo youtubeVideo) {

            }
        };

        movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getDetailResult(searchKeyword);
    }
}
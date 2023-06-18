package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.model.MovieDetail;
import edu.northeastern.movieapi.model.YoutubeVideo;
import edu.northeastern.movieapi.network.BaseUiThreadCallback;
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
        MovieWebService.UiThreadCallback uiThreadCallback = new BaseUiThreadCallback() {

            public void onDetailGet(MovieDetail movieDetails) {
                imageViewMovie = findViewById(R.id.imgView);
                Glide.with(DetailsActivity.this)
                        .load(movieDetails.getImage())
                        .into(imageViewMovie);

                textViewTitle = (TextView) findViewById(R.id.textView4);
                if (movieDetails.getTitle() == null ||
                        Objects.equals(movieDetails.getTitle(), "") ||
                        Objects.equals(movieDetails.getTitle(), "null")) {
                    textViewTitle.setText("Movie Title not found");
                } else {
                    textViewTitle.setText(movieDetails.getTitle());
                }


                textViewYear = (TextView) findViewById(R.id.textView6);
                if (movieDetails.getYear() == null || Objects.equals(movieDetails.getYear(), "")
                        || Objects.equals(movieDetails.getYear(), "null")) {
                    textViewYear.setText("Year not found");
                } else {
                    textViewYear.setText(movieDetails.getYear());
                }


                textViewReleaseDate = (TextView) findViewById(R.id.textView8);
                if (movieDetails.getReleaseDate() == null
                        || Objects.equals(movieDetails.getReleaseDate(), "") ||
                        Objects.equals(movieDetails.getReleaseDate(), "null")) {
                    textViewReleaseDate.setText("Release Date not found");
                } else {
                    textViewReleaseDate.setText(movieDetails.getReleaseDate());
                }


                textViewAwards = (TextView) findViewById(R.id.textView10);
                if (movieDetails.getAwards() == null || Objects.equals(movieDetails.getAwards(), "")
                        || Objects.equals(movieDetails.getAwards(), "null")) {
                    textViewAwards.setText("Awards not found");
                } else {
                    textViewAwards.setText(movieDetails.getAwards());
                }


                textViewDirectors = (TextView) findViewById(R.id.textView12);
                if (movieDetails.getDirectors() == null || Objects.equals(movieDetails.getDirectors(), "")
                        || Objects.equals(movieDetails.getDirectors(), "null")) {
                    textViewDirectors.setText("Directors not found");
                } else {
                    textViewDirectors.setText(movieDetails.getDirectors());
                }


                textViewStars = (TextView) findViewById(R.id.textView14);
                if (movieDetails.getStars() == null || Objects.equals(movieDetails.getStars(), "")
                        || Objects.equals(movieDetails.getStars(), "null")) {
                    textViewStars.setText("Stars not found");
                } else {
                    textViewStars.setText(movieDetails.getStars());
                }


                textViewGenres = (TextView) findViewById(R.id.textView16);
                if (movieDetails.getGenres() == null || Objects.equals(movieDetails.getGenres(), "")
                        || Objects.equals(movieDetails.getGenres(), "null")) {
                    textViewGenres.setText("Genre not found");
                } else {
                    textViewGenres.setText(movieDetails.getGenres());
                }


                textViewContentRating = (TextView) findViewById(R.id.textView18);
                if (movieDetails.getContentRating() == null
                        || Objects.equals(movieDetails.getContentRating(), "")
                        || Objects.equals(movieDetails.getContentRating(), "null")) {
                    textViewContentRating.setText("Content Rating not found");
                } else {
                    textViewContentRating.setText(movieDetails.getContentRating());
                }


                textViewImdbRating = (TextView) findViewById(R.id.textView20);
                if (movieDetails.getImDbRating() == null || Objects.equals(movieDetails.getImDbRating(), "")
                        || Objects.equals(movieDetails.getImDbRating(), "null")) {
                    textViewImdbRating.setText("IMDB Rating not found");
                } else {
                    textViewImdbRating.setText(movieDetails.getImDbRating());
                }


                textViewPlot = (TextView) findViewById(R.id.textView22);
                if (movieDetails.getPlot() == null || Objects.equals(movieDetails.getPlot(), "")
                        || Objects.equals(movieDetails.getPlot(), "null")) {
                    textViewPlot.setText("Plot not found");
                } else {
                    textViewPlot.setText(movieDetails.getPlot());
                }


                textViewRuntime = (TextView) findViewById(R.id.textView24);
                if (movieDetails.getRuntimeStr() == null || Objects.equals(movieDetails.getRuntimeStr(), "")
                        || Objects.equals(movieDetails.getRuntimeStr(), "null")) {
                    textViewRuntime.setText("Runtime not found");
                } else {
                    textViewRuntime.setText(movieDetails.getRuntimeStr());
                }


                Log.d(TAG, "movieDetails title = " + movieDetails.getTitle());
            }
        };

        movieWebService = new MovieWebService(uiThreadCallback);
        movieWebService.getDetailResult(searchKeyword);
    }
}
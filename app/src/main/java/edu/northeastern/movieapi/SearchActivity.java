package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.network.MovieWebService;
import edu.northeastern.movieapi.network.NetworkThread;

public class SearchActivity extends AppCompatActivity {

    private MovieWebService.UiThreadCallback uiThreadCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchEditText = findViewById(R.id.searchEditText);
        Button seeResultButton = findViewById(R.id.seeResultButton);

        // Move to ResultActivity, just testing.
        uiThreadCallback = new MovieWebService.UiThreadCallback() {
            @Override
            public void onSearchResultGet(List<Movie> movies) {
                // Movies
            }
        };
        seeResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MovieWebService(uiThreadCallback).getSearchResult(searchEditText.getText().toString());
            }
        });
    }
}
package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.network.MovieWebService;
import edu.northeastern.movieapi.network.NetworkThread;

public class SearchActivity extends AppCompatActivity {
    EditText searchEditText;
    public void cancelEdit(View view){
        searchEditText.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchEditText= findViewById(R.id.searchEditText);
        Button seeResultButton = findViewById(R.id.seeResultButton);
        seeResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                intent.putExtra("searchKeyword", searchEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
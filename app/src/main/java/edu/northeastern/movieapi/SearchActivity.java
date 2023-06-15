package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import edu.northeastern.movieapi.model.Movie;
import edu.northeastern.movieapi.network.MovieWebService;
import edu.northeastern.movieapi.network.NetworkThread;

public class SearchActivity extends AppCompatActivity {
    EditText searchEditText;
    TextView actionText;
    TextView animationText;
    TextView adventureText;
    TextView dramaText;
    TextView crimeText;
    TextView comedyText;
    TextView documentaryText;
    TextView historicText;
    TextView fourStarsText;
    TextView threeStarsText;
    TextView newReleaseText;

    public void optionClicked(View view){
        String tagNum = view.getTag().toString();
        Log.i("info","pressed");
        switch(tagNum){
            case "0": actionText.setBackgroundColor(Color.LTGRAY);
                break;
            case "1": animationText.setBackgroundColor(Color.LTGRAY);
                break;
            case "2": adventureText.setBackgroundColor(Color.LTGRAY);
                break;
            case "3": dramaText.setBackgroundColor(Color.LTGRAY);
                break;
            case "4": crimeText.setBackgroundColor(Color.LTGRAY);
                break;
            case "5": comedyText.setBackgroundColor(Color.LTGRAY);
                break;
            case "6": documentaryText.setBackgroundColor(Color.LTGRAY);
                break;
            case "7": historicText.setBackgroundColor(Color.LTGRAY);
                break;
            case "8": fourStarsText.setBackgroundColor(Color.LTGRAY);
                break;
            case "9": threeStarsText.setBackgroundColor(Color.LTGRAY);
                break;
            case "10": newReleaseText.setBackgroundColor(Color.LTGRAY);
                break;
            default:
                break;
        }
    }
    public void clearOptionsClicked(View view){
        actionText.setBackgroundColor(Color.WHITE);
        animationText.setBackgroundColor(Color.WHITE);
        adventureText.setBackgroundColor(Color.WHITE);
        dramaText.setBackgroundColor(Color.WHITE);
        crimeText.setBackgroundColor(Color.WHITE);
        comedyText.setBackgroundColor(Color.WHITE);
        documentaryText.setBackgroundColor(Color.WHITE);
        historicText.setBackgroundColor(Color.WHITE);
        fourStarsText.setBackgroundColor(Color.WHITE);
        threeStarsText.setBackgroundColor(Color.WHITE);
        newReleaseText.setBackgroundColor(Color.WHITE);
    }
    public void cancelEdit(View view){
        searchEditText.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchEditText= findViewById(R.id.searchEditText);
        Button seeResultButton = findViewById(R.id.seeResultButton);
        actionText = findViewById(R.id.actionText);
        animationText = findViewById(R.id.animationText);
        adventureText = findViewById(R.id.adventureText);
        dramaText = findViewById(R.id.dramaText);
        crimeText = findViewById(R.id.crimeText);
        comedyText = findViewById(R.id.comedyText);
        documentaryText = findViewById(R.id.documentaryText);
        historicText = findViewById(R.id.historicalText);
        fourStarsText = findViewById(R.id.fourStarsText);
        threeStarsText = findViewById(R.id.threeStarsText);
        newReleaseText = findViewById(R.id.newReleasesText);
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
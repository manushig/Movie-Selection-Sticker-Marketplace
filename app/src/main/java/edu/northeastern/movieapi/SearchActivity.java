package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A class that make related reactions in searching page layout
 */
public class SearchActivity extends AppCompatActivity {
    Button seeResultButton;
    EditText searchEditText;
    Button actionText,animationText,adventureText,dramaText,crimeText,comedyText,documentaryText,historicText;
    Button nineScoreText,eightScoreText,sevenScoreText,sixScoreText;
    Button s2010Text,s2000Text,s1990Text,s1980Text,s1970Text,s1960Text;
    Button zeroToOneText,oneToTwoText, twoAndAboveText;

    /**
     * A on click method that give filter options reaction, including backgrounds being gray,
     * @param view
     */
    public void optionClicked(View view){
        String tagNum = view.getTag().toString();
        Log.i("info","pressed");
        switch(tagNum){
            case "0": actionText.setBackgroundColor(Color.DKGRAY);
                break;
            case "1": animationText.setBackgroundColor(Color.DKGRAY);
                break;
            case "2": adventureText.setBackgroundColor(Color.DKGRAY);
                break;
            case "3": dramaText.setBackgroundColor(Color.DKGRAY);
                break;
            case "4": crimeText.setBackgroundColor(Color.DKGRAY);
                break;
            case "5": comedyText.setBackgroundColor(Color.DKGRAY);
                break;
            case "6": documentaryText.setBackgroundColor(Color.DKGRAY);
                break;
            case "7": historicText.setBackgroundColor(Color.DKGRAY);
                break;
            case "8": nineScoreText.setBackgroundColor(Color.DKGRAY);
                break;
            case "9": eightScoreText.setBackgroundColor(Color.DKGRAY);
                break;
            case "10": sevenScoreText.setBackgroundColor(Color.DKGRAY);
                break;
            case "11": sixScoreText.setBackgroundColor(Color.DKGRAY);
                break;
            case "12": s2010Text.setBackgroundColor(Color.DKGRAY);
                break;
            case "13": s2000Text.setBackgroundColor(Color.DKGRAY);
                break;
            case "14": s1990Text.setBackgroundColor(Color.DKGRAY);
                break;
            case "15": s1980Text.setBackgroundColor(Color.DKGRAY);
                break;
            case "16": s1970Text.setBackgroundColor(Color.DKGRAY);
                break;
            case "17": s1960Text.setBackgroundColor(Color.DKGRAY);
                break;
            case "18": zeroToOneText.setBackgroundColor(Color.DKGRAY);
                break;
            case "19": oneToTwoText.setBackgroundColor(Color.DKGRAY);
                break;
            case "20": twoAndAboveText.setBackgroundColor(Color.DKGRAY);
                break;
            default:
                break;
        }
    }

    /**
     * A method that make all clicked filter options back to the original status.
     * @param view
     */
    public void clearOptionsClicked(View view){
        actionText.setBackgroundColor(Color.LTGRAY);
        animationText.setBackgroundColor(Color.LTGRAY);
        adventureText.setBackgroundColor(Color.LTGRAY);
        dramaText.setBackgroundColor(Color.LTGRAY);
        crimeText.setBackgroundColor(Color.LTGRAY);
        comedyText.setBackgroundColor(Color.LTGRAY);
        documentaryText.setBackgroundColor(Color.LTGRAY);
        historicText.setBackgroundColor(Color.LTGRAY);
        nineScoreText.setBackgroundColor(Color.LTGRAY);
        eightScoreText.setBackgroundColor(Color.LTGRAY);
        sevenScoreText.setBackgroundColor(Color.LTGRAY);
        sixScoreText.setBackgroundColor(Color.LTGRAY);
        s2010Text.setBackgroundColor(Color.LTGRAY);
        s2000Text.setBackgroundColor(Color.LTGRAY);
        s1990Text.setBackgroundColor(Color.LTGRAY);
        s1980Text.setBackgroundColor(Color.LTGRAY);
        s1970Text.setBackgroundColor(Color.LTGRAY);
        s1960Text.setBackgroundColor(Color.LTGRAY);
        zeroToOneText.setBackgroundColor(Color.LTGRAY);
        oneToTwoText.setBackgroundColor(Color.LTGRAY);
        twoAndAboveText.setBackgroundColor(Color.LTGRAY);
    }

    /**
     * A on click method of cancel edit button
     * @param view
     */
    public void cancelEdit(View view){
        searchEditText.setText("");
    }

    /**
     * A helper method to initialize view in searching page
     */
    public void initView(){
        searchEditText= findViewById(R.id.searchEditText);
        seeResultButton = findViewById(R.id.seeResultButton);
        actionText = findViewById(R.id.actionText);
        animationText = findViewById(R.id.animationText);
        adventureText = findViewById(R.id.adventureText);
        dramaText = findViewById(R.id.dramaText);
        crimeText = findViewById(R.id.crimeText);
        comedyText = findViewById(R.id.comedyText);
        documentaryText = findViewById(R.id.documentaryText);
        historicText = findViewById(R.id.historicalText);
        nineScoreText = findViewById(R.id.nineScoreText);
        eightScoreText = findViewById(R.id.eightScoreText);
        sevenScoreText = findViewById(R.id.sevenScoreText);
        sixScoreText = findViewById(R.id.sixScoreText);
        s2010Text = findViewById(R.id.s2010Text);
        s2000Text = findViewById(R.id.s2000Text);
        s1990Text = findViewById(R.id.s1990Text);
        s1980Text = findViewById(R.id.s1980Text);
        s1970Text = findViewById(R.id.s1970Text);
        s1960Text = findViewById(R.id.s1960Text);
        zeroToOneText = findViewById(R.id.zeroToOneText);
        oneToTwoText =findViewById(R.id.oneToTwoText);
        twoAndAboveText = findViewById(R.id.twoAndAboveText);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        // https://imdb-api.com/API/AdvancedSearch/k_2luv1h1i
//String passed to searchKeyword should be like this:  ?user_rating=8.0,&release_date=2010-01-01,&genres=action,adventure&moviemeter=2,3
        Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
        intent.putExtra("searchKeyword", searchEditText.getText().toString());
        startActivity(intent);
        seeResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // https://imdb-api.com/API/AdvancedSearch/k_2luv1h1i
                //String passed to searchKeyword should be like this:  ?user_rating=8.0,&release_date=2010-01-01,&genres=action,adventure&moviemeter=2,3
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                intent.putExtra("searchKeyword", searchEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
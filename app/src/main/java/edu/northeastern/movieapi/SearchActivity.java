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
    Button actionText, animationText, adventureText, dramaText, crimeText, comedyText, documentaryText, historicText;
    Button nineScoreText, eightScoreText, sevenScoreText, sixScoreText;
    Button s2010Text, s2000Text, s1990Text, s1980Text, s1970Text, s1960Text;
    Button zeroToOneText, oneToTwoText, twoAndAboveText;
    boolean[] optionsBoolean = new boolean[21];

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < 21; i++) {
            outState.putBoolean("tag" + i + "clicked", optionsBoolean[i]);
        }
    }

    /**
     * A on click method that give filter options reaction, including backgrounds being gray,
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getTag().toString()) {
            case "0":
                actionText.setTextColor(Color.RED);
                optionsBoolean[0] = true;
                break;
            case "1":
                animationText.setTextColor(Color.RED);
                optionsBoolean[1] = true;
                break;
            case "2":
                adventureText.setTextColor(Color.RED);
                optionsBoolean[2] = true;
                break;
            case "3":
                dramaText.setTextColor(Color.RED);
                optionsBoolean[3] = true;
                break;
            case "4":
                crimeText.setTextColor(Color.RED);
                optionsBoolean[4] = true;
                break;
            case "5":
                comedyText.setTextColor(Color.RED);
                optionsBoolean[5] = true;
                break;
            case "6":
                documentaryText.setTextColor(Color.RED);
                optionsBoolean[6] = true;
                break;
            case "7":
                historicText.setTextColor(Color.RED);
                optionsBoolean[7] = true;
                break;
            case "8":
                nineScoreText.setTextColor(Color.RED);
                optionsBoolean[8] = true;
                break;
            case "9":
                eightScoreText.setTextColor(Color.RED);
                optionsBoolean[9] = true;
                break;
            case "10":
                sevenScoreText.setTextColor(Color.RED);
                optionsBoolean[10] = true;
                break;
            case "11":
                sixScoreText.setTextColor(Color.RED);
                optionsBoolean[11] = true;
                break;
            case "12":
                s2010Text.setTextColor(Color.RED);
                optionsBoolean[12] = true;
                break;
            case "13":
                s2000Text.setTextColor(Color.RED);
                optionsBoolean[13] = true;
                break;
            case "14":
                s1990Text.setTextColor(Color.RED);
                optionsBoolean[14] = true;
                break;
            case "15":
                s1980Text.setTextColor(Color.RED);
                optionsBoolean[15] = true;
                break;
            case "16":
                s1970Text.setTextColor(Color.RED);
                optionsBoolean[16] = true;
                break;
            case "17":
                s1960Text.setTextColor(Color.RED);
                optionsBoolean[17] = true;
                break;
            case "18":
                zeroToOneText.setTextColor(Color.RED);
                optionsBoolean[18] = true;
                break;
            case "19":
                oneToTwoText.setTextColor(Color.RED);
                optionsBoolean[19] = true;
                break;
            case "20":
                twoAndAboveText.setTextColor(Color.RED);
                optionsBoolean[20] = true;
                break;
            default:
                break;
        }
    }

    /**
     * A method that make all clicked filter options back to the original status.
     *
     * @param view
     */
    public void clearOptionsClicked(View view) {
        actionText.setTextColor(Color.BLACK);
        animationText.setTextColor(Color.BLACK);
        adventureText.setTextColor(Color.BLACK);
        dramaText.setTextColor(Color.BLACK);
        crimeText.setTextColor(Color.BLACK);
        comedyText.setTextColor(Color.BLACK);
        documentaryText.setTextColor(Color.BLACK);
        historicText.setTextColor(Color.BLACK);
        nineScoreText.setTextColor(Color.BLACK);
        eightScoreText.setTextColor(Color.BLACK);
        sevenScoreText.setTextColor(Color.BLACK);
        sixScoreText.setTextColor(Color.BLACK);
        s2010Text.setTextColor(Color.BLACK);
        s2000Text.setTextColor(Color.BLACK);
        s1990Text.setTextColor(Color.BLACK);
        s1980Text.setTextColor(Color.BLACK);
        s1970Text.setTextColor(Color.BLACK);
        s1960Text.setTextColor(Color.BLACK);
        zeroToOneText.setTextColor(Color.BLACK);
        oneToTwoText.setTextColor(Color.BLACK);
        twoAndAboveText.setTextColor(Color.BLACK);
    }

    /**
     * A on click method of cancel edit button
     *
     * @param view
     */
    public void cancelEdit(View view) {
        searchEditText.setText("");
    }

    /**
     * A helper method to initialize view in searching page
     */
    public void initView() {
        for (int i = 0; i < 21; i++) {
            if (optionsBoolean[i]) {
                switch (i) {
                    case 0:
                        actionText.setTextColor(Color.RED);
                        optionsBoolean[0] = true;
                        break;
                    case 1:
                        animationText.setTextColor(Color.RED);
                        optionsBoolean[1] = true;
                        break;
                    case 2:
                        adventureText.setTextColor(Color.RED);
                        optionsBoolean[2] = true;
                        break;
                    case 3:
                        dramaText.setTextColor(Color.RED);
                        optionsBoolean[3] = true;
                        break;
                    case 4:
                        crimeText.setTextColor(Color.RED);
                        optionsBoolean[4] = true;
                        break;
                    case 5:
                        comedyText.setTextColor(Color.RED);
                        optionsBoolean[5] = true;
                        break;
                    case 6:
                        documentaryText.setTextColor(Color.RED);
                        optionsBoolean[6] = true;
                        break;
                    case 7:
                        historicText.setTextColor(Color.RED);
                        optionsBoolean[7] = true;
                        break;
                    case 8:
                        nineScoreText.setTextColor(Color.RED);
                        optionsBoolean[8] = true;
                        break;
                    case 9:
                        eightScoreText.setTextColor(Color.RED);
                        optionsBoolean[9] = true;
                        break;
                    case 10:
                        sevenScoreText.setTextColor(Color.RED);
                        optionsBoolean[10] = true;
                        break;
                    case 11:
                        sixScoreText.setTextColor(Color.RED);
                        optionsBoolean[11] = true;
                        break;
                    case 12:
                        s2010Text.setTextColor(Color.RED);
                        optionsBoolean[12] = true;
                        break;
                    case 13:
                        s2000Text.setTextColor(Color.RED);
                        optionsBoolean[13] = true;
                        break;
                    case 14:
                        s1990Text.setTextColor(Color.RED);
                        optionsBoolean[14] = true;
                        break;
                    case 15:
                        s1980Text.setTextColor(Color.RED);
                        optionsBoolean[15] = true;
                        break;
                    case 16:
                        s1970Text.setTextColor(Color.RED);
                        optionsBoolean[16] = true;
                        break;
                    case 17:
                        s1960Text.setTextColor(Color.RED);
                        optionsBoolean[17] = true;
                        break;
                    case 18:
                        zeroToOneText.setTextColor(Color.RED);
                        optionsBoolean[18] = true;
                        break;
                    case 19:
                        oneToTwoText.setTextColor(Color.RED);
                        optionsBoolean[19] = true;
                        break;
                    case 20:
                        twoAndAboveText.setTextColor(Color.RED);
                        optionsBoolean[20] = true;
                        break;
//                    default:
//                        break;
                }
            }
        }
        searchEditText = findViewById(R.id.searchEditText);
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
        oneToTwoText = findViewById(R.id.oneToTwoText);
        twoAndAboveText = findViewById(R.id.twoAndAboveText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        if (savedInstanceState != null) {
            Log.i("info", "runnable is not null");
            for (int i = 0; i < 21; i++) {
                this.optionsBoolean[i] = savedInstanceState.getBoolean("tag" + i + "clicked");
            }
        }else{
            for (int i = 0; i < 21; i++) {
                optionsBoolean[i] = false;
            }
        }
        // https://imdb-api.com/API/AdvancedSearch/k_2luv1h1i
//String passed to searchKeyword should be like this:  ?user_rating=8.0,&release_date=2010-01-01,&genres=action,adventure&moviemeter=2,3
//        Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
//        intent.putExtra("searchKeyword", searchEditText.getText().toString());
//        startActivity(intent);

        actionText.setOnClickListener(this::onClick);
        animationText.setOnClickListener(this::onClick);
        adventureText.setOnClickListener(this::onClick);
        dramaText.setOnClickListener(this::onClick);
        crimeText.setOnClickListener(this::onClick);
        comedyText.setOnClickListener(this::onClick);
        documentaryText.setOnClickListener(this::onClick);
        historicText.setOnClickListener(this::onClick);
        actionText.setOnClickListener(this::onClick);
        actionText.setOnClickListener(this::onClick);
        actionText.setOnClickListener(this::onClick);
        actionText.setOnClickListener(this::onClick);

        nineScoreText.setOnClickListener(this::onClick);
        eightScoreText.setOnClickListener(this::onClick);
        sevenScoreText.setOnClickListener(this::onClick);
        sixScoreText.setOnClickListener(this::onClick);

        s2010Text.setOnClickListener(this::onClick);
        s2000Text.setOnClickListener(this::onClick);
        s1990Text.setOnClickListener(this::onClick);
        s1980Text.setOnClickListener(this::onClick);
        s1970Text.setOnClickListener(this::onClick);
        s1960Text.setOnClickListener(this::onClick);

        zeroToOneText.setOnClickListener(this::onClick);
        oneToTwoText.setOnClickListener(this::onClick);
        twoAndAboveText.setOnClickListener(this::onClick);

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
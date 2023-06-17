package edu.northeastern.movieapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    boolean genresSelected, ratingSelected, yearSelected, timeSelected;
    StringBuilder genresSum,ratingSum,yearSum,timeSum;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < 21; i++) {
            outState.putBoolean("tag" + i + "clicked", optionsBoolean[i]);
        }
        outState.putBoolean("genresSelected", genresSelected);
        outState.putBoolean("ratingSelected", ratingSelected);
        outState.putBoolean("yearSelected", yearSelected);
        outState.putBoolean("timeSelected", timeSelected);
    }

    /**
     * A on click method that give filter options reaction, including backgrounds being gray,
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getTag().toString()) {
            case "0":
                actionText.setTextColor(Color.WHITE);
                genresSum.append("action,");
                optionsBoolean[0] = true;
                genresSelected = true;
                break;
            case "1":
                animationText.setTextColor(Color.WHITE);
                optionsBoolean[1] = true;
                genresSelected = true;
                genresSum.append("animation,");
                break;
            case "2":
                adventureText.setTextColor(Color.WHITE);
                optionsBoolean[2] = true;
                genresSelected = true;
                genresSum.append("adventure,");
                break;
            case "3":
                dramaText.setTextColor(Color.WHITE);
                optionsBoolean[3] = true;
                genresSelected = true;
                genresSum.append("drama,");
                break;
            case "4":
                crimeText.setTextColor(Color.WHITE);
                optionsBoolean[4] = true;
                genresSelected = true;
                genresSum.append("crime,");
                break;
            case "5":
                comedyText.setTextColor(Color.WHITE);
                optionsBoolean[5] = true;
                genresSelected = true;
                genresSum.append("comedy,");
                break;
            case "6":
                documentaryText.setTextColor(Color.WHITE);
                optionsBoolean[6] = true;
                genresSelected = true;
                genresSum.append("documentary,");
                break;
            case "7":
                historicText.setTextColor(Color.WHITE);
                optionsBoolean[7] = true;
                genresSelected = true;
                genresSum.append("history,");
                break;
            case "8":
                if (!ratingSelected){
                    Log.i("info","wrong");
                    ratingSelected = true;
                    nineScoreText.setTextColor(Color.WHITE);
                    optionsBoolean[8] = true;
                    ratingSum.append("9.0,10");
                } else {
                    Toast.makeText(this,"Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "9":
                if (!ratingSelected){
                    ratingSelected = true;
                    eightScoreText.setTextColor(Color.WHITE);
                    optionsBoolean[9] = true;
                    ratingSum.append("8.0,10");
                } else {
                    Toast.makeText(this,"Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "10":
                if (!ratingSelected){
                    ratingSelected = true;
                    sevenScoreText.setTextColor(Color.WHITE);
                    optionsBoolean[10] = true;
                    ratingSum.append("7.0,10");
                } else {
                    Toast.makeText(this,"Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "11":
                if (!ratingSelected){
                    ratingSelected = true;
                    sixScoreText.setTextColor(Color.WHITE);
                    optionsBoolean[11] = true;
                    ratingSum.append("6.0,10");
                } else {
                    Toast.makeText(this,"Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "12":
                if (!yearSelected){
                    yearSelected = true;
                    s2010Text.setTextColor(Color.WHITE);
                    optionsBoolean[12] = true;
                    yearSum.append("2010-01-01,2023-01-01");
                } else {
                    Toast.makeText(this,"Only can select one period and later", Toast.LENGTH_SHORT).show();
                }
                break;
            case "13":
                if (!yearSelected){
                    s2000Text.setTextColor(Color.WHITE);
                    optionsBoolean[13] = true;
                    yearSelected = true;
                    yearSum.append("2000-01-01,2010-01-01");
                } else {
                    Toast.makeText(this,"Only can select one period and later", Toast.LENGTH_SHORT).show();
                }
                break;
            case "14":
                if (!yearSelected){
                    s1990Text.setTextColor(Color.WHITE);
                    optionsBoolean[14] = true;
                    yearSelected = true;
                    yearSum.append("1990-01-01,2000-01-01");
                } else {
                    Toast.makeText(this,"Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "15":
                if (!yearSelected){
                    s1980Text.setTextColor(Color.WHITE);
                    optionsBoolean[15] = true;
                    yearSelected = true;
                    yearSum.append("1980-01-01,1990-01-01");
                } else {
                    Toast.makeText(this,"Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "16":
                if (!yearSelected){
                    s1970Text.setTextColor(Color.WHITE);
                    optionsBoolean[16] = true;
                    yearSelected = true;
                    yearSum.append("1970-01-01,1980-01-01");
                } else {
                    Toast.makeText(this,"Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "17":
                if (!yearSelected){
                    s1960Text.setTextColor(Color.WHITE);
                    optionsBoolean[17] = true;
                    yearSelected = true;
                    yearSum.append("1960-01-01,1970-01-01");
                } else {
                    Toast.makeText(this,"Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "18":
                if (!timeSelected){
                    zeroToOneText.setTextColor(Color.WHITE);
                    optionsBoolean[18] = true;
                    timeSelected = true;
                    timeSum.append("0,60");
                } else {
                    Toast.makeText(this,"Only can select one range", Toast.LENGTH_SHORT).show();
                }
                break;
            case "19":
                if (!timeSelected){
                    oneToTwoText.setTextColor(Color.WHITE);
                    optionsBoolean[19] = true;
                    timeSelected = true;
                    timeSum.append("60,120");
                } else {
                    Toast.makeText(this,"Only can select one range", Toast.LENGTH_SHORT).show();
                }
                break;
            case "20":
                if (!timeSelected){
                    twoAndAboveText.setTextColor(Color.WHITE);
                    optionsBoolean[20] = true;
                    timeSelected = true;
                    timeSum.append("120,300");
                } else {
                    Toast.makeText(this,"Only can select one range", Toast.LENGTH_SHORT).show();
                }
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

        for (int i = 0; i < 21; i++) {
            optionsBoolean[i] = false;
        }

        genresSelected = false;
        ratingSelected = false;
        timeSelected = false;
        yearSelected = false;
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

        searchEditText.setFocusable(false);
    }

    public String createApiLinkSum(){
        //Make the full link of API including all filters
        StringBuilder apiLinkSum = new StringBuilder();
        if (!searchEditText.getText().toString().equals("")) {
            apiLinkSum.append("title=");
            apiLinkSum.append(searchEditText.getText().toString());
            apiLinkSum.append("&");
        }

        if (ratingSelected) {
            apiLinkSum.append("user_rating=");
            apiLinkSum.append(ratingSum);
            apiLinkSum.append("&");
        }

        if (yearSelected) {
            apiLinkSum.append("release_date=");
            apiLinkSum.append(yearSum);
            apiLinkSum.append("&");
        }

        if (genresSelected) {
            apiLinkSum.append("genres=");
            apiLinkSum.append(genresSum);
            apiLinkSum.deleteCharAt(apiLinkSum.length()-1);
            apiLinkSum.append("&");
        }

        if (timeSelected) {
            apiLinkSum.append("moviemeter=");
            apiLinkSum.append(timeSum);
        }

        if (apiLinkSum.charAt(apiLinkSum.length() - 1) == '&') {
            apiLinkSum.deleteCharAt(apiLinkSum.length() - 1);
        }

        return apiLinkSum.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEditText.setFocusableInTouchMode(true);
                searchEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });


        if (savedInstanceState != null) {
            for (int i = 0; i < 21; i++) {
                this.optionsBoolean[i] = savedInstanceState.getBoolean("tag" + i + "clicked");
                if (optionsBoolean[i]) {
                    switch (i) {
                        case 0:
                            actionText.setTextColor(Color.WHITE);
                            break;
                        case 1:
                            animationText.setTextColor(Color.WHITE);
                            break;
                        case 2:
                            adventureText.setTextColor(Color.WHITE);
                            break;
                        case 3:
                            dramaText.setTextColor(Color.WHITE);
                            break;
                        case 4:
                            crimeText.setTextColor(Color.WHITE);
                            break;
                        case 5:
                            comedyText.setTextColor(Color.WHITE);
                            break;
                        case 6:
                            documentaryText.setTextColor(Color.WHITE);
                            break;
                        case 7:
                            historicText.setTextColor(Color.WHITE);
                            break;
                        case 8:
                            nineScoreText.setTextColor(Color.WHITE);
                            break;
                        case 9:
                            eightScoreText.setTextColor(Color.WHITE);
                            break;
                        case 10:
                            sevenScoreText.setTextColor(Color.WHITE);
                            break;
                        case 11:
                            sixScoreText.setTextColor(Color.WHITE);
                            break;
                        case 12:
                            s2010Text.setTextColor(Color.WHITE);
                            break;
                        case 13:
                            s2000Text.setTextColor(Color.WHITE);
                            break;
                        case 14:
                            s1990Text.setTextColor(Color.WHITE);
                            break;
                        case 15:
                            s1980Text.setTextColor(Color.WHITE);
                            break;
                        case 16:
                            s1970Text.setTextColor(Color.WHITE);
                            break;
                        case 17:
                            s1960Text.setTextColor(Color.WHITE);
                            break;
                        case 18:
                            zeroToOneText.setTextColor(Color.WHITE);
                            break;
                        case 19:
                            oneToTwoText.setTextColor(Color.WHITE);
                            break;
                        case 20:
                            twoAndAboveText.setTextColor(Color.WHITE);
                            break;
                        default:
                            break;
                    }
                }
            }
            this.genresSelected = savedInstanceState.getBoolean("genresSelected");
            this.ratingSelected = savedInstanceState.getBoolean("ratingSelected");
            this.yearSelected = savedInstanceState.getBoolean("yearSelected");
            this.timeSelected = savedInstanceState.getBoolean("timeSelected");
        } else {
            genresSelected = false;
            ratingSelected = false;
            yearSelected = false;
            timeSelected = false;
            for (int i = 0; i < 21; i++) {
                optionsBoolean[i] = false;
            }
        }
        // https://imdb-api.com/API/AdvancedSearch/k_2luv1h1i
//String passed to searchKeyword should be like this:  ?user_rating=8.0,&release_date=2010-01-01,&genres=action,adventure&moviemeter=2,3
//        Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
//        intent.putExtra("searchKeyword", searchEditText.getText().toString());
//        startActivity(intent);
        genresSum = new StringBuilder();
        ratingSum = new StringBuilder();
        yearSum = new StringBuilder();
        timeSum = new StringBuilder();

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

        actionText.setOnClickListener(this::onClick);
        animationText.setOnClickListener(this::onClick);
        adventureText.setOnClickListener(this::onClick);
        dramaText.setOnClickListener(this::onClick);
        crimeText.setOnClickListener(this::onClick);
        comedyText.setOnClickListener(this::onClick);
        documentaryText.setOnClickListener(this::onClick);
        historicText.setOnClickListener(this::onClick);


        zeroToOneText.setOnClickListener(this::onClick);
        oneToTwoText.setOnClickListener(this::onClick);
        twoAndAboveText.setOnClickListener(this::onClick);


        seeResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchKeyWord = createApiLinkSum();
                Log.i("apiLinkSum", searchKeyWord);
                //String passed to searchKeyword should be like this:  ?user_rating=8.0,&release_date=2010-01-01,&genres=action,adventure&moviemeter=2,3
                Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                intent.putExtra("searchKeyword", searchKeyWord);
                startActivity(intent);
            }
        });
    }
}
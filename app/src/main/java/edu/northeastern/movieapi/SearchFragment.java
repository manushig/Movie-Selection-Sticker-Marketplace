package edu.northeastern.movieapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * A class that make related reactions in searching page layout
 */
public class SearchFragment extends Fragment {
    Button seeResultButton;
    EditText searchEditText;
    Button actionText, animationText, adventureText, dramaText, crimeText, comedyText, documentaryText, historicText;
    Button nineScoreText, eightScoreText, sevenScoreText, sixScoreText;
    Button s2010Text, s2000Text, s1990Text, s1980Text, s1970Text, s1960Text;
    Button zeroToOneText, oneToTwoText, twoAndAboveText;
    int bgColor, textColor;
    int buttonNum;
    int ratingSelectedNum,yearSelectedNum,timeSelectedNum;
    boolean[] optionsBoolean = new boolean[21];
    String[] buttonApiString = new String[21];
    boolean genresSelected, ratingSelected, yearSelected, timeSelected;
    StringBuilder apiLinkSum, genresSum, ratingSum, yearSum, timeSum;
    private Button clearFilterButton;
    private Button cancelEditTextButton;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < buttonNum; i++) {
            outState.putBoolean("tag" + i + "clicked", optionsBoolean[i]);
        }
        outState.putBoolean("genresSelected", genresSelected);
        outState.putBoolean("ratingSelected", ratingSelected);
        outState.putBoolean("yearSelected", yearSelected);
        outState.putBoolean("timeSelected", timeSelected);
        outState.putInt("ratingSelectedNum",ratingSelectedNum);
        outState.putInt("yearSelectedNum",yearSelectedNum);
        outState.putInt("timeSelectedNum",timeSelectedNum);
    }

    /**
     * Helper method to change background color to transparent
     */
    private void buttonReset(Button name, int tagNum) {
        name.setBackgroundColor(Color.TRANSPARENT);
        optionsBoolean[tagNum] = false;
        if (tagNum >= 0 && tagNum < 8) {
            genresSum.delete(genresSum.indexOf(buttonApiString[tagNum]), buttonApiString[tagNum].length() + 1);
        } else if (tagNum >= 8 && tagNum < 12) {
            ratingSelectedNum--;
            ratingSum.delete(ratingSum.indexOf(buttonApiString[tagNum]), buttonApiString[tagNum].length() + 1);
        } else if (tagNum >= 12 && tagNum < 18) {
            yearSelectedNum--;
            yearSum.delete(yearSum.indexOf(buttonApiString[tagNum]), buttonApiString[tagNum].length() + 1);
        } else {
            timeSelectedNum--;
            timeSum.delete(timeSum.indexOf(buttonApiString[tagNum]), buttonApiString[tagNum].length() + 1);
        }
    }

    /**
     * Helper method to change background color to transparent
     */
    private void buttonSelected(Button name, int tagNum){
        name.setBackgroundColor(bgColor);
        optionsBoolean[tagNum] = true;
        if (tagNum >= 0 && tagNum < 8) {
            genresSum.append(buttonApiString[tagNum]+",");
        } else if (tagNum >= 8 && tagNum < 12) {
            ratingSelectedNum++;
            ratingSum.append(buttonApiString[tagNum]);
        } else if (tagNum >= 12 && tagNum < 18) {
            yearSelectedNum++;
            yearSum.append(buttonApiString[tagNum]);
        } else {
            timeSelectedNum++;
            timeSum.append(buttonApiString[tagNum]);
        }
    }

    /**
     * A on click method that give filter options reaction, including backgrounds being gray,
     *
     * @param view the search activity view
     */
    private void onClick(View view) {
        switch (view.getTag().toString()) {
            case "0":
                if (optionsBoolean[0]) {
                    buttonReset(actionText, 0);
                    break;
                }
                buttonSelected(actionText, 0);
                break;
            case "1":
                if (optionsBoolean[1]) {
                    buttonReset(animationText, 1);
                    break;
                }
                buttonSelected(animationText, 1);
                break;
            case "2":
                if (optionsBoolean[2]) {
                    buttonReset(adventureText, 2);
                    break;
                }
                buttonSelected(adventureText, 2);
                break;
            case "3":
                if (optionsBoolean[3]) {
                    buttonReset(dramaText, 3);
                    break;
                }
                buttonSelected(dramaText, 3);
                break;
            case "4":
                if (optionsBoolean[4]) {
                    buttonReset(crimeText, 4);
                    break;
                }
                buttonSelected(crimeText, 4);
                break;
            case "5":
                if (optionsBoolean[5]) {
                    buttonReset(comedyText, 5);
                    break;
                }
                buttonSelected(comedyText, 5);
                break;
            case "6":
                if (optionsBoolean[6]) {
                    buttonReset(documentaryText, 6);
                    break;
                }
                buttonSelected(documentaryText, 6);
                break;
            case "7":
                if (optionsBoolean[7]) {
                    buttonReset(historicText, 7);
                    break;
                }
                buttonSelected(historicText, 7);
                break;
            case "8":
                if (optionsBoolean[8]) {
                    buttonReset(nineScoreText, 8);
                    break;
                }
                if (ratingSelectedNum == 0) {
                    buttonSelected(nineScoreText, 8);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "9":
                if (optionsBoolean[9]) {
                    buttonReset(eightScoreText, 9);
                    break;
                }
                if (ratingSelectedNum == 0) {
                    buttonSelected(eightScoreText, 9);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "10":
                if (optionsBoolean[10]) {
                    buttonReset(sevenScoreText, 10);
                    break;
                }
                if (ratingSelectedNum == 0) {
                    buttonSelected(sevenScoreText, 10);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "11":
                if (optionsBoolean[11]) {
                    buttonReset(sixScoreText, 11);
                    break;
                }
                if (ratingSelectedNum == 0) {
                    buttonSelected(sixScoreText, 11);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one score", Toast.LENGTH_SHORT).show();
                }
                break;
            case "12":
                if (optionsBoolean[12]) {
                    buttonReset(s2010Text, 12);
                    break;
                }
                if (yearSelectedNum == 0) {
                    buttonSelected(s2010Text, 12);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one period and later", Toast.LENGTH_SHORT).show();
                }
                break;
            case "13":
                if (optionsBoolean[13]) {
                    buttonReset(s2000Text, 13);
                    break;
                }
                if (yearSelectedNum == 0) {
                    buttonSelected(s2000Text, 13);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one period and later", Toast.LENGTH_SHORT).show();
                }
                break;
            case "14":
                if (optionsBoolean[14]) {
                    buttonReset(s1990Text, 14);
                    break;
                }
                if (yearSelectedNum == 0) {
                    buttonSelected(s1990Text, 14);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "15":
                if (optionsBoolean[15]) {
                    buttonReset(s1980Text, 15);
                    break;
                }
                if (yearSelectedNum == 0) {
                    buttonSelected(s1980Text, 15);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "16":
                if (optionsBoolean[16]) {
                    buttonReset(s1970Text, 16);
                    break;
                }
                if (yearSelectedNum == 0) {
                    buttonSelected(s1970Text, 16);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "17":
                if (optionsBoolean[17]) {
                    buttonReset(s1960Text, 17);
                    break;
                }
                if (yearSelectedNum == 0) {
                    buttonSelected(s1960Text, 17);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one period", Toast.LENGTH_SHORT).show();
                }
                break;
            case "18":
                if (optionsBoolean[18]) {
                    buttonReset(zeroToOneText, 18);
                    break;
                }
                if (timeSelectedNum == 0) {
                    buttonSelected(zeroToOneText, 18);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one range", Toast.LENGTH_SHORT).show();
                }
                break;
            case "19":
                if (optionsBoolean[19]) {
                    buttonReset(oneToTwoText, 19);
                    break;
                }
                if (timeSelectedNum == 0) {
                    buttonSelected(oneToTwoText, 19);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one range", Toast.LENGTH_SHORT).show();
                }
                break;
            case "20":
                if (optionsBoolean[20]) {
                    buttonReset(twoAndAboveText, 20);
                    break;
                }
                if (timeSelectedNum == 0) {
                    buttonSelected(twoAndAboveText, 20);
                } else {
                    Toast.makeText(requireActivity(), "Only can select one range", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * A method that make all clicked filter options back to the original status.
     *
     */
    private void clearOptionsClicked() {
        searchEditText.setText("");
        //Set back text color
        actionText.setBackgroundColor(Color.TRANSPARENT);
        animationText.setBackgroundColor(Color.TRANSPARENT);
        adventureText.setBackgroundColor(Color.TRANSPARENT);
        dramaText.setBackgroundColor(Color.TRANSPARENT);
        crimeText.setBackgroundColor(Color.TRANSPARENT);
        comedyText.setBackgroundColor(Color.TRANSPARENT);
        documentaryText.setBackgroundColor(Color.TRANSPARENT);
        historicText.setBackgroundColor(Color.TRANSPARENT);
        nineScoreText.setBackgroundColor(Color.TRANSPARENT);
        eightScoreText.setBackgroundColor(Color.TRANSPARENT);
        sevenScoreText.setBackgroundColor(Color.TRANSPARENT);
        sixScoreText.setBackgroundColor(Color.TRANSPARENT);
        s2010Text.setBackgroundColor(Color.TRANSPARENT);
        s2000Text.setBackgroundColor(Color.TRANSPARENT);
        s1990Text.setBackgroundColor(Color.TRANSPARENT);
        s1980Text.setBackgroundColor(Color.TRANSPARENT);
        s1970Text.setBackgroundColor(Color.TRANSPARENT);
        s1960Text.setBackgroundColor(Color.TRANSPARENT);
        zeroToOneText.setBackgroundColor(Color.TRANSPARENT);
        oneToTwoText.setBackgroundColor(Color.TRANSPARENT);
        twoAndAboveText.setBackgroundColor(Color.TRANSPARENT);

        //Set optionBoolean[] back to false help onSaveInstanceState memory
        for (int i = 0; i < buttonNum; i++) {
            optionsBoolean[i] = false;
        }

        //Make all category select status become false
        genresSelected = false;
        ratingSelected = false;
        timeSelected = false;
        yearSelected = false;

        ratingSelectedNum = 0;
        yearSelectedNum = 0;
        timeSelectedNum = 0;
    }

    /**
     * A on click method of cancel edit button
     *
     */
    private void cancelEdit() {
        searchEditText.setText("");
    }

    /**
     * A helper method to initialize view in searching page
     */
    @SuppressLint("ResourceType")
    private void initView(View view) {
        searchEditText = view.findViewById(R.id.searchEditText);
        seeResultButton = view.findViewById(R.id.seeResultButton);
        actionText = view.findViewById(R.id.actionText);
        animationText = view.findViewById(R.id.animationText);
        adventureText = view.findViewById(R.id.adventureText);
        dramaText = view.findViewById(R.id.dramaText);
        crimeText = view.findViewById(R.id.crimeText);
        comedyText = view.findViewById(R.id.comedyText);
        documentaryText = view.findViewById(R.id.documentaryText);
        historicText = view.findViewById(R.id.historicalText);
        nineScoreText = view.findViewById(R.id.nineScoreText);
        eightScoreText = view.findViewById(R.id.eightScoreText);
        sevenScoreText = view.findViewById(R.id.sevenScoreText);
        sixScoreText = view.findViewById(R.id.sixScoreText);
        s2010Text = view.findViewById(R.id.s2010Text);
        s2000Text = view.findViewById(R.id.s2000Text);
        s1990Text = view.findViewById(R.id.s1990Text);
        s1980Text = view.findViewById(R.id.s1980Text);
        s1970Text = view.findViewById(R.id.s1970Text);
        s1960Text = view.findViewById(R.id.s1960Text);
        zeroToOneText = view.findViewById(R.id.zeroToOneText);
        oneToTwoText = view.findViewById(R.id.oneToTwoText);
        twoAndAboveText = view.findViewById(R.id.twoAndAboveText);
        clearFilterButton = view.findViewById(R.id.clearFilterButton);
        cancelEditTextButton = view.findViewById(R.id.cancelEditTextButton);

        searchEditText.setFocusable(false);

        bgColor = ContextCompat.getColor(requireContext(), R.color.bg_color);
        textColor = ContextCompat.getColor(requireContext(), R.color.text_color);

        buttonNum = 21;
        ratingSelectedNum = 0;
        yearSelectedNum = 0;
        timeSelectedNum = 0;

        //the relative api string
        for (int i = 0; i < buttonNum; i++) {
            switch (i) {
                case 0:
                    buttonApiString[i] = "action";
                    break;
                case 1:
                    buttonApiString[i] = "animation";
                    break;
                case 2:
                    buttonApiString[i] = "adventure";
                    break;
                case 3:
                    buttonApiString[i] = "drama";
                    break;
                case 4:
                    buttonApiString[i] = "crime";
                    break;
                case 5:
                    buttonApiString[i] = "comedy";
                    break;
                case 6:
                    buttonApiString[i] = "documentary";
                    break;
                case 7:
                    buttonApiString[i] = "history";
                    break;
                case 8:
                    buttonApiString[i] = "9.0,10";
                    break;
                case 9:
                    buttonApiString[i] = "8.0,10";
                    break;
                case 10:
                    buttonApiString[i] = "7.0,10";
                    break;
                case 11:
                    buttonApiString[i] = "6.0,10";
                    break;
                case 12:
                    buttonApiString[i] = "2010-01-01,2023-01-01";
                    break;
                case 13:
                    buttonApiString[i] = "2000-01-01,2010-01-01";
                    break;
                case 14:
                    buttonApiString[i] = "1990-01-01,2000-01-01";
                    break;
                case 15:
                    buttonApiString[i] = "1980-01-01,1990-01-01";
                    break;
                case 16:
                    buttonApiString[i] = "1970-01-01,1980-01-01";
                    break;
                case 17:
                    buttonApiString[i] = "1950-01-01,1970-01-01";
                    break;
                case 18:
                    buttonApiString[i] = "0,1";
                    break;
                case 19:
                    buttonApiString[i] = "1,2";
                    break;
                case 20:
                    buttonApiString[i] = "2,5";
                    break;
                default:
                    break;
            }
        }
    }

    private String createApiLinkSum() {
        apiLinkSum = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (optionsBoolean[i]){
                genresSelected = true;
                break;
            }
        }
        for (int i = 8; i < 12; i++) {
            if (optionsBoolean[i]){
                ratingSelected = true;
                break;
            }
        }
        for (int i = 12; i < 18; i++) {
            if (optionsBoolean[i]){
                yearSelected = true;
                break;
            }
        }
        for (int i = 18; i < buttonNum; i++) {
            if (optionsBoolean[i]){
                timeSelected = true;
                break;
            }
        }
        if (apiLinkSum.toString().equals("")){
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
                apiLinkSum.deleteCharAt(apiLinkSum.length() - 1);
                apiLinkSum.append("&");
            }

            if (timeSelected) {
                apiLinkSum.append("moviemeter=");
                apiLinkSum.append(timeSum);
            }

            if (apiLinkSum.length() == 0) {
                return "";
            }

            int indexToDelete = apiLinkSum.lastIndexOf("&");
            if (indexToDelete == apiLinkSum.length()-1) {
                apiLinkSum.deleteCharAt(indexToDelete);
            }
        }

        return apiLinkSum.toString();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

//        if (savedInstanceState != null) {
//            for (int i = 0; i < buttonNum; i++) {
//                this.optionsBoolean[i] = savedInstanceState.getBoolean("tag" + i + "clicked");
//                if (optionsBoolean[i]) {
//                    switch (i) {
//                        case 0:
//                            actionText.setBackgroundColor(bgColor);
//                            break;
//                        case 1:
//                            animationText.setBackgroundColor(bgColor);
//                            break;
//                        case 2:
//                            adventureText.setBackgroundColor(bgColor);
//                            break;
//                        case 3:
//                            dramaText.setBackgroundColor(bgColor);
//                            break;
//                        case 4:
//                            crimeText.setBackgroundColor(bgColor);
//                            break;
//                        case 5:
//                            comedyText.setBackgroundColor(bgColor);
//                            break;
//                        case 6:
//                            documentaryText.setBackgroundColor(bgColor);
//                            break;
//                        case 7:
//                            historicText.setBackgroundColor(bgColor);
//                            break;
//                        case 8:
//                            nineScoreText.setBackgroundColor(bgColor);
//                            break;
//                        case 9:
//                            eightScoreText.setBackgroundColor(bgColor);
//                            break;
//                        case 10:
//                            sevenScoreText.setBackgroundColor(bgColor);
//                            break;
//                        case 11:
//                            sixScoreText.setBackgroundColor(bgColor);
//                            break;
//                        case 12:
//                            s2010Text.setBackgroundColor(bgColor);
//                            break;
//                        case 13:
//                            s2000Text.setBackgroundColor(bgColor);
//                            break;
//                        case 14:
//                            s1990Text.setBackgroundColor(bgColor);
//                            break;
//                        case 15:
//                            s1980Text.setBackgroundColor(bgColor);
//                            break;
//                        case 16:
//                            s1970Text.setBackgroundColor(bgColor);
//                            break;
//                        case 17:
//                            s1960Text.setBackgroundColor(bgColor);
//                            break;
//                        case 18:
//                            zeroToOneText.setBackgroundColor(bgColor);
//                            break;
//                        case 19:
//                            oneToTwoText.setBackgroundColor(bgColor);
//                            break;
//                        case 20:
//                            twoAndAboveText.setBackgroundColor(bgColor);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//            this.ratingSelectedNum = savedInstanceState.getInt("ratingSelectedNum");
//            this.yearSelectedNum = savedInstanceState.getInt("yearSelectedNum");
//            this.timeSelectedNum = savedInstanceState.getInt("timeSelectedNum");
//            this.genresSelected = savedInstanceState.getBoolean("genresSelected");
//            this.ratingSelected = savedInstanceState.getBoolean("ratingSelected");
//            this.yearSelected = savedInstanceState.getBoolean("yearSelected");
//            this.timeSelected = savedInstanceState.getBoolean("timeSelected");
//        }
//        //The savedInstanceState didn't call before, do initialize
//        else {
//            genresSelected = false;
//            ratingSelected = false;
//            timeSelected = false;
//            yearSelected = false;
//            for (int i = 0; i < buttonNum; i++) {
//                optionsBoolean[i] = false;
//            }
//        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //Initialize view
        initView(view);
        searchEditText.setOnClickListener(v -> {
            searchEditText.setFocusableInTouchMode(true);
            searchEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
        });
        //If the savedInstanceState have called, make these action
        if (savedInstanceState != null) {
            Log.i("info","saveinsatance called");
            for (int i = 0; i < buttonNum; i++) {
                optionsBoolean[i] = savedInstanceState.getBoolean("tag" + i + "clicked");
                if (optionsBoolean[i]) {
                    switch (i) {
                        case 0:
                            actionText.setBackgroundColor(bgColor);
                            break;
                        case 1:
                            animationText.setBackgroundColor(bgColor);
                            break;
                        case 2:
                            adventureText.setBackgroundColor(bgColor);
                            break;
                        case 3:
                            dramaText.setBackgroundColor(bgColor);
                            break;
                        case 4:
                            crimeText.setBackgroundColor(bgColor);
                            break;
                        case 5:
                            comedyText.setBackgroundColor(bgColor);
                            break;
                        case 6:
                            documentaryText.setBackgroundColor(bgColor);
                            break;
                        case 7:
                            historicText.setBackgroundColor(bgColor);
                            break;
                        case 8:
                            nineScoreText.setBackgroundColor(bgColor);
                            break;
                        case 9:
                            eightScoreText.setBackgroundColor(bgColor);
                            break;
                        case 10:
                            sevenScoreText.setBackgroundColor(bgColor);
                            break;
                        case 11:
                            sixScoreText.setBackgroundColor(bgColor);
                            break;
                        case 12:
                            s2010Text.setBackgroundColor(bgColor);
                            break;
                        case 13:
                            s2000Text.setBackgroundColor(bgColor);
                            break;
                        case 14:
                            s1990Text.setBackgroundColor(bgColor);
                            break;
                        case 15:
                            s1980Text.setBackgroundColor(bgColor);
                            break;
                        case 16:
                            s1970Text.setBackgroundColor(bgColor);
                            break;
                        case 17:
                            s1960Text.setBackgroundColor(bgColor);
                            break;
                        case 18:
                            zeroToOneText.setBackgroundColor(bgColor);
                            break;
                        case 19:
                            oneToTwoText.setBackgroundColor(bgColor);
                            break;
                        case 20:
                            twoAndAboveText.setBackgroundColor(bgColor);
                            break;
                        default:
                            break;
                    }
                }
            }
            this.ratingSelectedNum = savedInstanceState.getInt("ratingSelectedNum");
            this.yearSelectedNum = savedInstanceState.getInt("yearSelectedNum");
            this.timeSelectedNum = savedInstanceState.getInt("timeSelectedNum");
            this.genresSelected = savedInstanceState.getBoolean("genresSelected");
            this.ratingSelected = savedInstanceState.getBoolean("ratingSelected");
            this.yearSelected = savedInstanceState.getBoolean("yearSelected");
            this.timeSelected = savedInstanceState.getBoolean("timeSelected");
        }
        //The savedInstanceState didn't call before, do initialize
        else {
            genresSelected = false;
            ratingSelected = false;
            timeSelected = false;
            yearSelected = false;
            for (int i = 0; i < buttonNum; i++) {
                optionsBoolean[i] = false;
            }
        }
        // https://imdb-api.com/API/AdvancedSearch/k_2luv1h1i
//String passed to searchKeyword should be like this:  ?user_rating=8.0,&release_date=2010-01-01,&genres=action,adventure&moviemeter=2,3
//        Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
//        intent.putExtra("searchKeyword", searchEditText.getText().toString());
//        startActivity(intent);

        apiLinkSum = new StringBuilder();
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

        clearFilterButton.setOnClickListener(v -> clearOptionsClicked());
        cancelEditTextButton.setOnClickListener(v -> cancelEdit());

        seeResultButton.setOnClickListener(v -> {
            String searchKeyWord = createApiLinkSum();
            Log.i("apiLinkSum", searchKeyWord);

            if (TextUtils.isEmpty(searchKeyWord)) {
                Toast.makeText(requireActivity(),"Please input valid search strings in search bar or a valid filter",Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra("searchQueryPath", searchKeyWord);
            startActivity(intent);
        });
    }
}
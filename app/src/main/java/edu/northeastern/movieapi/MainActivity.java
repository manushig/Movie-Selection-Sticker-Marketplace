package edu.northeastern.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void openSearchActivity(View view){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
    public void openTrailerActivity(View view){
        Intent intent = new Intent(this,TrailerActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
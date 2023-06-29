package edu.northeastern.movieapi;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {
    public void openHomeActivity(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openChatActivity(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.giphy).into(imageView);
    }
}
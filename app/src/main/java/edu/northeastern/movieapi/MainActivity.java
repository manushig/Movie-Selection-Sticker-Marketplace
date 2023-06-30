package edu.northeastern.movieapi;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import edu.northeastern.stickers.AboutActivity;
import edu.northeastern.stickers.LoginActivity;
import edu.northeastern.stickers.StickerHomeActivity;
import edu.northeastern.stickers.StickerStoreFragment;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button btnChat;

    public void openHomeActivity(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(View view) {
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

        auth = FirebaseAuth.getInstance();

        btnChat = findViewById(R.id.btnChat);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, StickerHomeActivity.class));
                }
            }
        });
    }

}
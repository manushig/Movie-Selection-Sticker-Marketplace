package edu.northeastern.stickers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.northeastern.movieapi.R;

public class LoginActivity extends AppCompatActivity {

    private TextView registerTextView;
    private TextView emailEditText;
    private Button loginButton;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private int emailCursorPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerTextView = findViewById(R.id.registerTextView);
        emailEditText = findViewById(R.id.emailEditText);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.GONE);

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);

                String email = emailEditText.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String password = String.valueOf(R.string.dummy_password);

                if(!email.matches(emailPattern) || TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginActivity.this, R.string.valid_email, Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(LoginActivity.this, StickerHomeActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, R.string.unregistered_email, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String email = emailEditText.getText().toString();
        outState.putString("email", email);

        emailCursorPosition = emailEditText.getSelectionStart();
        outState.putInt("emailCursorPosition", emailCursorPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            String email = savedInstanceState.getString("email");
            emailEditText.setText(email);

            emailCursorPosition = savedInstanceState.getInt("emailCursorPosition");
            EditText editText = (EditText) emailEditText;
            editText.setSelection(emailCursorPosition);
        }
    }
}
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.models.Users;

public class RegisterActivity extends AppCompatActivity {
    private TextView emailEditText, firstNameEditText, lastNameEditText;
    private Button registerButton;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ProgressBar progressBar;
    private int emailCursorPosition, firstNameCursorPosition, lastNameCursorPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar.setVisibility(View.GONE);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);

                String email = emailEditText.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String password = String.valueOf(R.string.dummy_password);
                String name = firstNameEditText.getText().toString().trim().concat(" ").concat(lastNameEditText.getText().toString().trim());

                if (!email.matches(emailPattern) || TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, R.string.valid_email, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(firstNameEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, R.string.valid_firstname, Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lastNameEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, R.string.valid_lastname, Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference dbReference = database.getReference().child("Users").child(auth.getUid());

                                Users user = new Users(email, name);

                                dbReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(RegisterActivity.this, StickerHomeActivity.class));
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterActivity.this, R.string.unsuccessful_registration, Toast.LENGTH_SHORT).show();
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

        String firstName = firstNameEditText.getText().toString();
        outState.putString("firstName", firstName);
        firstNameCursorPosition = firstNameEditText.getSelectionStart();
        outState.putInt("firstNameCursorPosition", firstNameCursorPosition);

        String lastName = lastNameEditText.getText().toString();
        outState.putString("lastName", lastName);
        lastNameCursorPosition = lastNameEditText.getSelectionStart();
        outState.putInt("lastNameCursorPosition", lastNameCursorPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            String email = savedInstanceState.getString("email");
            emailEditText.setText(email);
            emailCursorPosition = savedInstanceState.getInt("emailCursorPosition");
            EditText editEmailText = (EditText) emailEditText;
            editEmailText.setSelection(emailCursorPosition);

            String firstName = savedInstanceState.getString("firstName");
            firstNameEditText.setText(firstName);
            firstNameCursorPosition = savedInstanceState.getInt("firstNameCursorPosition");
            EditText editFirstNameText = (EditText) firstNameEditText;
            editFirstNameText.setSelection(firstNameCursorPosition);

            String lastName = savedInstanceState.getString("lastName");
            lastNameEditText.setText(lastName);
            lastNameCursorPosition = savedInstanceState.getInt("lastNameCursorPosition");
            EditText editLastNameText = (EditText) lastNameEditText;
            editLastNameText.setSelection(lastNameCursorPosition);
        }
    }
}
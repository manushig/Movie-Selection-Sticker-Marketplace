package edu.northeastern.stickers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseUtils {
    public static void saveFcmToken(String fcmToken) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = auth.getUid();

        if (uid != null && fcmToken != null) {
            DatabaseReference dbReference = database.getReference().child("Users").child(auth.getUid());

            dbReference.child("fcmToken").setValue(fcmToken);
        }
    }
}

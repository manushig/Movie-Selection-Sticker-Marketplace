package edu.northeastern.stickers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.UserStickerActivityAdapter;
import edu.northeastern.stickers.models.UserStickerActivity;

public class DisplayStickersActivity extends AppCompatActivity {
    private RecyclerView recyclerDisplay;
    private UserStickerActivityAdapter adapter;
    private List<UserStickerActivity> usersStickerActivityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_stickers);

        recyclerDisplay = findViewById(R.id.recyclerOfDisplay);

        recyclerDisplay.setLayoutManager(new LinearLayoutManager(this));
        usersStickerActivityList = new ArrayList<UserStickerActivity>();

        createListData();

        adapter = new UserStickerActivityAdapter(this, usersStickerActivityList);
        recyclerDisplay.setAdapter(adapter);
        recyclerDisplay.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



    }

    private void createListData(){
//        usersStickerActivityList = new ArrayList<>();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    UserStickerActivity newUserActivity = new UserStickerActivity(snapshot.getValue(),);
//                    usersStickerActivityList.add(newUserActivity);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
    }
}
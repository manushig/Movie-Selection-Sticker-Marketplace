package edu.northeastern.stickers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.UserStickerHistoryAdapter;
import edu.northeastern.stickers.models.UserStickerHistory;

public class StickerHistoryFragment extends Fragment {
    private RecyclerView recyclerDisplay;
    private UserStickerHistoryAdapter adapter;
    private List<UserStickerHistory> usersStickerHistoryList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerDisplay = view.findViewById(R.id.recyclerOfDisplay);

        recyclerDisplay.setLayoutManager(new LinearLayoutManager(this.getContext()));
        usersStickerHistoryList = new ArrayList<UserStickerHistory>();

        createListData();

        adapter = new UserStickerHistoryAdapter(this.getContext(), usersStickerHistoryList);
        recyclerDisplay.setAdapter(adapter);
        recyclerDisplay.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticker_history, container, false);
    }

    private void createListData(){
        usersStickerHistoryList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserStickerHistory newUserActivity;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("SentStickerCount").exists()){
                        List<UserStickerHistory.StickerSentCount>  stickerSentCountList = new ArrayList<>();
                        DataSnapshot sentStickerCountSnapshot = snapshot.child("SentStickerCount");
                        for (DataSnapshot snapshotChild : sentStickerCountSnapshot.getChildren()){
                            UserStickerHistory.StickerSentCount newUserSent = new UserStickerHistory.StickerSentCount(snapshotChild.getKey(),Integer.parseInt(snapshotChild.getValue().toString()));
                            stickerSentCountList.add(newUserSent);
                        }
                        newUserActivity = new UserStickerHistory(snapshot.getKey(),stickerSentCountList);
                    } else {
                        newUserActivity = new UserStickerHistory(snapshot.getKey());
                    }
                    usersStickerHistoryList.add(newUserActivity);
                }
                //                 StickerPack stickerPack = new StickerPack(snapshot1.getKey(),
                //                                snapshot1.child("Name").getValue(String.class),
                //                                snapshot1.child("StickerPath").getValue(String.class));
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}
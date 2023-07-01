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
                List<UserStickerHistory.StickerSentCount>  stickerSentCountList = new ArrayList<>();
                List<UserStickerHistory.StickerReceivedCount> stickerReceivedCountList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if (snapshot.child("SentStickerCount").exists()){
                        DataSnapshot sentStickerCountSnapshot = snapshot.child("SentStickerCount");
                        for (DataSnapshot snapshotChild : sentStickerCountSnapshot.getChildren()){
                            UserStickerHistory.StickerSentCount newUserSent = new UserStickerHistory.StickerSentCount(snapshotChild.getKey(),Integer.parseInt(snapshotChild.getValue().toString()));
                            stickerSentCountList.add(newUserSent);
                        }
                    }

                    if (snapshot.child("ReceivedHistory").exists()){
                        DataSnapshot receiveCountSnapshot = snapshot.child("ReceivedHistory");
                        for (DataSnapshot snapshotChild : receiveCountSnapshot.getChildren()){
                            UserStickerHistory.StickerReceivedCount newUserReceive = new UserStickerHistory.StickerReceivedCount(snapshotChild.child(
                                    "receivedFromUserID").getValue().toString(),snapshotChild.child("receivedTimestamp").getValue().toString(),snapshotChild.child(
                                    "stickerReceivedID").getValue().toString());
                            stickerReceivedCountList.add(newUserReceive);
                        }
                    }

                    if(!stickerReceivedCountList.isEmpty() && !stickerSentCountList.isEmpty()){
                        newUserActivity = new UserStickerHistory(snapshot.getKey(),stickerSentCountList,stickerReceivedCountList);
                    } else if (!stickerSentCountList.isEmpty()){
                        newUserActivity = new UserStickerHistory(snapshot.getKey(),stickerSentCountList,null);
                    } else if (!stickerReceivedCountList.isEmpty()){
                        newUserActivity = new UserStickerHistory(snapshot.getKey(),null,stickerReceivedCountList);
                    } else{
                        newUserActivity = new UserStickerHistory(snapshot.getKey(),null,null);
                    }

                    usersStickerHistoryList.add(newUserActivity);
                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}
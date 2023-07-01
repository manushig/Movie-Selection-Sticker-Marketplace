package edu.northeastern.stickers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.StickerInboxAdapter;
import edu.northeastern.stickers.models.ReceivingInfo;

public class StickerInboxFragment extends Fragment {

    String userID;
    private RecyclerView receiveHistoryRecyclerView;
    private List<ReceivingInfo> receivedHistoryCollectors;
    private StickerInboxAdapter receivedHIstoryAdapter;
    private DatabaseReference mDatabase;

    FirebaseUser user;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID= user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        receiveHistoryRecyclerView = view.findViewById(R.id.recyclerView_receive_history);
        receiveHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        receiveHistoryRecyclerView.setHasFixedSize(true);

        receivedHistoryCollectors = new ArrayList<>();
        receivedHIstoryAdapter = new StickerInboxAdapter(receivedHistoryCollectors, this.getContext());
        receiveHistoryRecyclerView.setAdapter(receivedHIstoryAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(receiveHistoryRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        receiveHistoryRecyclerView.addItemDecoration(dividerItemDecoration);

        mDatabase.orderByChild("receivedTimestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReceivingInfo receivingInfo;

                if (dataSnapshot.child(userID).child("ReceivedHistory").exists()){
                    DataSnapshot sentStickerHistorySnapshot = dataSnapshot.child(userID).child("ReceivedHistory");
                    for (DataSnapshot snapshotChild : sentStickerHistorySnapshot.getChildren()){
                        String receivedFromUserID = snapshotChild.child("receivedFromUserID").getValue().toString();
                        receivingInfo = new ReceivingInfo(
                                dataSnapshot.child(receivedFromUserID).child("name").getValue().toString(),
                                snapshotChild.child("stickerReceivedID").getValue().toString(),
                                snapshotChild.child("receivedTimestamp").getValue().toString(),
                                snapshotChild.child("receivingStickerPath").getValue().toString());

                        receivedHistoryCollectors.add(receivingInfo);
                    }
                }
                Log.i("list",receivedHistoryCollectors.get(0).toString());
                receivedHIstoryAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticker_inbox, container, false);
    }
}

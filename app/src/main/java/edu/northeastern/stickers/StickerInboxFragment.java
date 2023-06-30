package edu.northeastern.stickers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.StickerInboxAdapter;
import edu.northeastern.stickers.models.StickerInboxCollector;

public class StickerInboxFragment extends Fragment {

    String userID;
    private RecyclerView receiveHistoryRecyclerView;
    private List<StickerInboxCollector> receivedHistoryCollectors;
    private StickerInboxAdapter receivedHIstoryAdapter;
    private DatabaseReference mDatabase;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference("stickerExchangeDetails");

        receiveHistoryRecyclerView = view.findViewById(R.id.recyclerView_receive_history);
        receiveHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        receiveHistoryRecyclerView.setHasFixedSize(true);

        receivedHistoryCollectors = new ArrayList<>();
        receivedHIstoryAdapter = new StickerInboxAdapter(receivedHistoryCollectors, this.getContext());
        receiveHistoryRecyclerView.setAdapter(receivedHIstoryAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(receiveHistoryRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        receiveHistoryRecyclerView.addItemDecoration(dividerItemDecoration);

//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        userID = sharedPreferences.getString("username", "");


//        mDatabase.child("allExchanges").orderByChild("dateSent").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                receivedHistoryCollectors.clear();
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    StickerExchangeDetails stickerExchangeDetails = dataSnapshot.getValue(StickerExchangeDetails.class);
//                    if (stickerExchangeDetails.receiverId.equals(userID)) {
//                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("stickerExchangeDetails").child("allExchanges").child(dataSnapshot.getKey()).child("viewed");
//                        db.setValue(true);
//
//                        ReceivedHistoryCollector receivedHistoryCollector = new ReceivedHistoryCollector(stickerExchangeDetails.getSenderId(), stickerExchangeDetails.getDateSent(), stickerExchangeDetails.getStickerId());
//
//                        stickerExchangeDetails.setViewed(true);
//                        if (!receivedHistoryCollectors.contains(receivedHistoryCollector)) {
//                            receivedHistoryCollectors.add(receivedHistoryCollector);
//
//                        }
//
//                    }
//
//                }
//
//                Collections.reverse(receivedHistoryCollectors);
//                receivedHIstoryAdapter.notifyDataSetChanged();
            }

//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

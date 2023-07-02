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
import android.widget.Toast;

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
import edu.northeastern.stickers.adapters.UserStickerHistoryAdapter;
import edu.northeastern.stickers.models.UserStickerHistory;

/**
 * History fragment to show logged in user's sending history.
 */
public class StickerHistoryFragment extends Fragment {
    private RecyclerView recyclerDisplay;
    private UserStickerHistoryAdapter adapter;
    private List<UserStickerHistory> usersStickerHistoryList;
    private DatabaseReference referenceOfUser;
    private FirebaseUser user;
    private String uid;

    private ValueEventListener listener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        recyclerDisplay = view.findViewById(R.id.recyclerOfDisplay);
        recyclerDisplay.setLayoutManager(new LinearLayoutManager(this.getContext()));
        usersStickerHistoryList = new ArrayList<UserStickerHistory>();

        createListData(view);

        adapter = new UserStickerHistoryAdapter(this.getContext(), usersStickerHistoryList);
        recyclerDisplay.setAdapter(adapter);
        recyclerDisplay.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticker_history, container, false);
    }

    /**
     * Fetch data from database to show user's sending history to the xml relative textviews.
     */
    private void createListData(View view) {
        usersStickerHistoryList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        referenceOfUser = database.getReference().child("Users");

        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserStickerHistory newUserHistory;
                usersStickerHistoryList.clear();
                if (dataSnapshot.child(uid).child("SentHistory").exists()) {
                    DataSnapshot sentStickerHistorySnapshot = dataSnapshot.child(uid).child("SentHistory");
                    for (DataSnapshot snapshotChild : sentStickerHistorySnapshot.getChildren()) {
                        String sendToUserId = snapshotChild.child("sendToUserID").getValue().toString();
                        String sentStickerId = snapshotChild.child("stickerSentID").getValue().toString();
                        String sentStickerPath = snapshotChild.child("sentStickerPath").getValue().toString();
                        newUserHistory = new UserStickerHistory(
                                dataSnapshot.child(sendToUserId).child("name").getValue().toString(),
                                snapshotChild.child("sentTimestamp").getValue().toString(),
                                sentStickerId,sentStickerPath);
                        usersStickerHistoryList.add(newUserHistory);
                    }

                    sort(usersStickerHistoryList);

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(view.getContext(), "Sending History is  empty. No stickers received yet.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        referenceOfUser.addValueEventListener(listener);
    }

    /**
     * Sort List of UserStickerHistory by descending time order.
     * @param list of UserStickerHistory
     */
    private void sort(List<UserStickerHistory> list) {
        list.sort(((o1, o2) -> o2.getTime().compareTo(o1.getTime())));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listener != null) {
            referenceOfUser.removeEventListener(listener);
        }
    }
}
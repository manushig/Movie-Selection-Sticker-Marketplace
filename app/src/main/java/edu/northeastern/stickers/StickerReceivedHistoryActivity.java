package edu.northeastern.stickers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.adapters.ReceivedHistoryAdapter;
import edu.northeastern.stickers.adapters.UserStickerHistoryAdapter;
import edu.northeastern.stickers.models.UserStickerHistory;

public class StickerReceivedHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerDisplay;
    private ReceivedHistoryAdapter adapter;
    private List<UserStickerHistory.StickerReceivedCount> stickerReceivedCountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticker_history_recycler);

        recyclerDisplay = findViewById(R.id.history_recycler);
        recyclerDisplay.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReceivedHistoryAdapter(stickerReceivedCountList,this);
        recyclerDisplay.setAdapter(adapter);
        recyclerDisplay.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public void setStickerReceivedCountList(List<UserStickerHistory.StickerReceivedCount> stickerReceivedCountList) {
        this.stickerReceivedCountList = stickerReceivedCountList;
    }
}
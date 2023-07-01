package edu.northeastern.stickers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.models.UserStickerHistory;

public class ReceivedHistoryAdapter extends RecyclerView.Adapter<ReceivedHistoryAdapter.ReceivedHistoryHolder> {
    private List<UserStickerHistory.StickerReceivedCount> stickerReceivedCountList;
    private Context context;

    public ReceivedHistoryAdapter(List<UserStickerHistory.StickerReceivedCount> stickerReceivedCountList, Context context) {
        this.stickerReceivedCountList = stickerReceivedCountList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceivedHistoryAdapter.ReceivedHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_history_recycler, null, false);
        return new ReceivedHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceivedHistoryAdapter.ReceivedHistoryHolder holder, int position) {
        UserStickerHistory.StickerReceivedCount stickerReceivedCount = stickerReceivedCountList.get(position);
        holder.receivedTime.setText(stickerReceivedCount.getTime().toString());
        holder.receivedStickerId.setText(stickerReceivedCount.getStickerId().toString());
        holder.receivedFromUserId.setText(stickerReceivedCount.getUserIdSentSticker().toString());
    }

    @Override
    public int getItemCount() {
        if (stickerReceivedCountList != null){
            return stickerReceivedCountList.size();
        } else {
            return 0;
        }
    }

    class ReceivedHistoryHolder extends RecyclerView.ViewHolder{
        private TextView receivedFromUserText, receivedStickerIdText,receivedTimeText;
        private TextView receivedFromUserId, receivedStickerId,receivedTime;
        public ReceivedHistoryHolder(@NonNull View itemView) {
            super(itemView);
            receivedFromUserText = itemView.findViewById(R.id.receivedFromUserText);
            receivedStickerIdText = itemView.findViewById(R.id.recievedStickerIdText);
            receivedTimeText = itemView.findViewById(R.id.receivedTimeText);
            receivedFromUserId = itemView.findViewById(R.id.userName);
            receivedStickerId = itemView.findViewById(R.id.recievedStickerId);
            receivedTime = itemView.findViewById(R.id.receivedTime);
        }
    }
}

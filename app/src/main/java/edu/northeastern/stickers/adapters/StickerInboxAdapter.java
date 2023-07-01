package edu.northeastern.stickers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.StickerInboxViewHolder;
import edu.northeastern.stickers.models.ReceivingInfo;

public class StickerInboxAdapter extends RecyclerView.Adapter<StickerInboxViewHolder> {

    private List<ReceivingInfo> receivedHistoryCollectors;
    private final Context context;

    public StickerInboxAdapter(List<ReceivingInfo> receivedHistoryCollectors, Context context) {
        this.receivedHistoryCollectors = receivedHistoryCollectors;
        this.context = context;
    }


    @NonNull
    @Override
    public StickerInboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StickerInboxViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recieve_history, null));
    }

    @Override
    public void onBindViewHolder(@NonNull StickerInboxViewHolder holder, int position) {
        holder.bindThisData(receivedHistoryCollectors.get(position));

    }

    @Override
    public int getItemCount() {
        return receivedHistoryCollectors.size();
    }
}

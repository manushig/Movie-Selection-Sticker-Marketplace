package edu.northeastern.stickers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.models.StickerPack;
import edu.northeastern.stickers.models.StickerSection;

public class StickerPackAdapter extends RecyclerView.Adapter<StickerPackAdapter.StickerViewHolder> {

    private final List<StickerSection> stickerSectionPackList;
    private final Map<String, List<StickerPack>> stickerPackMap;
    private final LayoutInflater inflater;

    public StickerPackAdapter(Context context, List<StickerSection> stickerSectionPackList, Map<String, List<StickerPack>> stickerPackMap) {
        this.stickerSectionPackList = stickerSectionPackList;
        this.stickerPackMap = stickerPackMap;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public StickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout = inflater.inflate(R.layout.sticker_item_row, parent, false);
        return new StickerViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder holder, int position) {
        String sectionName = stickerSectionPackList.get(holder.getAdapterPosition()).getStickerPack();
        holder.stickerName.setText(sectionName);


        for (StickerPack stickerPack : stickerPackMap.get(sectionName)) {
            ImageView imageView = new ImageView(holder.itemView.getContext());
            Glide.with(holder.itemView.getContext())
                    .load(stickerPack.getStickerPath())
                    .apply(new RequestOptions().override(350, 350))
                    .into(imageView);
            holder.linearLayout.addView(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return stickerSectionPackList.size();
    }

    public class StickerViewHolder extends RecyclerView.ViewHolder {
        TextView stickerName;
        LinearLayout linearLayout;
        View itemView;

        public StickerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.stickerName = itemView.findViewById(R.id.textView2);
            this.linearLayout = itemView.findViewById(R.id.ll);
        }
    }
}

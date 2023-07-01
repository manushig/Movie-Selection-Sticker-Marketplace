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

public class UserHistoryChildAdapter extends RecyclerView.Adapter<UserHistoryChildAdapter.UserHistoryChildHolder> {

    private List<UserStickerHistory.StickerSentCount> stickerSentCountList;
    private Context context;

    public UserHistoryChildAdapter(List<UserStickerHistory.StickerSentCount> stickerSentCountList, Context context) {
        this.stickerSentCountList = stickerSentCountList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserHistoryChildHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_user_item_child, null, false);
        return new UserHistoryChildHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHistoryChildHolder holder, int position) {
        holder.stickerId.setText(stickerSentCountList.get(position).getStickerId());
        holder.countNumber.setText(stickerSentCountList.get(position).getSentCountNumber());
    }

    @Override
    public int getItemCount() {
        try{
            return stickerSentCountList.size();
        } catch (Exception e){
            return 0;
        }
    }

    public class UserHistoryChildHolder extends RecyclerView.ViewHolder {
        TextView stickerId, countNumber;

        public UserHistoryChildHolder(@NonNull View itemView) {
            super(itemView);
            stickerId = itemView.findViewById(R.id.stickerId);
            countNumber = itemView.findViewById(R.id.countNumber);
        }
    }
}

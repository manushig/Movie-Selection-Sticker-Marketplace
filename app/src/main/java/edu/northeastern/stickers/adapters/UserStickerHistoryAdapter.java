package edu.northeastern.stickers.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.StickerReceivedHistoryActivity;
import edu.northeastern.stickers.models.UserStickerHistory;

public class UserStickerHistoryAdapter extends RecyclerView.Adapter<UserStickerHistoryAdapter.UserStickerHistoryHolder> {
    private Context context;
    private List<UserStickerHistory> userStickerHistoryList;
    private Button receivedHistoryButton;


    public UserStickerHistoryAdapter(Context context, List<UserStickerHistory> userStickerHistoryList) {
        this.context = context;
        this.userStickerHistoryList = userStickerHistoryList;
    }

    @NonNull
    @Override
    public UserStickerHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_user_item, null, false);
        return new UserStickerHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserStickerHistoryHolder holder, int position) {
        UserStickerHistory userStickerHistory = userStickerHistoryList.get(position);
        holder.userName.setText(userStickerHistory.getUserId().toString());
        holder.stickerId.setText(userStickerHistory.getStickerId().toString());
        holder.time.setText(userStickerHistory.getTime().toString());
        UserHistoryChildAdapter childAdapter;
        childAdapter = new UserHistoryChildAdapter(userStickerHistory.getStickerSentCountList(), context);
//        holder.childRecyclerOfDisplay.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//        holder.childRecyclerOfDisplay.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return userStickerHistoryList.size();
    }

    class UserStickerHistoryHolder extends RecyclerView.ViewHolder {
        private TextView userNameText, userName,stickerId,time, timeText;
        private RecyclerView childRecyclerOfDisplay;

        public UserStickerHistoryHolder(@NonNull View itemView) {
            super(itemView);
            userNameText = itemView.findViewById(R.id.userNameText);
            userName = itemView.findViewById(R.id.userName);
            timeText = itemView.findViewById(R.id.timeText);
//            receivedHistoryButton = itemView.findViewById(R.id.stickerRecievedHistoryButton);
//            childRecyclerOfDisplay = itemView.findViewById(R.id.childRecyclerOfDisplay);
            stickerId = itemView.findViewById(R.id.stickerId);
            time = itemView.findViewById(R.id.sentTime);
//            receivedHistoryButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, StickerReceivedHistoryActivity.class);
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}


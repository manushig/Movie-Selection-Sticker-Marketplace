package edu.northeastern.stickers.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.StickerReceivedHistoryActivity;
import edu.northeastern.stickers.models.UserStickerActivity;

public class UserStickerActivityAdapter extends RecyclerView.Adapter<UserStickerActivityAdapter.UserStickerActivityHolder> {
    private Context context;
    private List<UserStickerActivity> userStickerActivityList;
    private Button receivedHistoryButton;

    public UserStickerActivityAdapter(Context context, List<UserStickerActivity> userStickerActivityList) {
        this.context = context;
        this.userStickerActivityList = userStickerActivityList;
    }

    @NonNull
    @Override
    public UserStickerActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sticker_activity_user_item,parent,false);
        return new UserStickerActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserStickerActivityHolder holder, int position) {
        UserStickerActivity userStickerActivity = userStickerActivityList.get(position);
        holder.SetDetails(userStickerActivity);
    }

    @Override
    public int getItemCount() {
        return userStickerActivityList.size();
    }

    class UserStickerActivityHolder extends  RecyclerView.ViewHolder{
        private TextView userName, stickerSentActivity;

        public UserStickerActivityHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userNameText);
            stickerSentActivity = itemView.findViewById(R.id.numOfStickersText);
            receivedHistoryButton = itemView.findViewById(R.id.stickerRecievedHistoryButton);
            receivedHistoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StickerReceivedHistoryActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        void SetDetails(UserStickerActivity userStickerActivity){
            userName.setText(userStickerActivity.getUser().getName());
            for (UserStickerActivity.StickerSentCount stickerSentCount : userStickerActivity.getStickerSentCountList()){
                stickerSentActivity.setText(stickerSentCount.getStickerId() + stickerSentCount.getSentCountNumber());
            }
        }
    }
}


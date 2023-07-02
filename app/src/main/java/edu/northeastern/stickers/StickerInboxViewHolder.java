package edu.northeastern.stickers;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.models.ReceivingInfo;

public class StickerInboxViewHolder extends RecyclerView.ViewHolder {
    public TextView senderId;
    public TextView timeSent;
    public ImageView sticker;

    public StickerInboxViewHolder(@NonNull View itemView) {
        super(itemView);
        this.senderId = itemView.findViewById(R.id.textView_senderId);
        this.timeSent = itemView.findViewById(R.id.textView_dateSent);
        this.sticker = itemView.findViewById(R.id.imageView_sticker);
    }

    public void bindThisData(ReceivingInfo theLinkToBind) {

        senderId.setText(("From: " + theLinkToBind.getReceivedFromUserID()));
        timeSent.setText(("Time: " + theLinkToBind.getReceivedTimestamp()));
        Glide.with(this.itemView)
                .load(theLinkToBind.getReceivingStickerPath())
                .into(sticker);



    }
}

package edu.northeastern.stickers;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.models.StickerInboxCollector;

public class StickerInboxViewHolder extends RecyclerView.ViewHolder {
    public TextView senderId;
    public TextView dateSent;
    public ImageView sticker;

    public StickerInboxViewHolder(@NonNull View itemView) {
        super(itemView);
        this.senderId = itemView.findViewById(R.id.textView_senderId);
        this.dateSent = itemView.findViewById(R.id.textView_dateSent);
        this.sticker = itemView.findViewById(R.id.imageView_sticker);
    }

    public void bindThisData(StickerInboxCollector theLinkToBind) {

        senderId.setText(("Sender: " + theLinkToBind.getSenderId()));
        dateSent.setText(("Time: " + theLinkToBind.getDateSent()));
        String uri = "@drawable/" + theLinkToBind.getSticker(); // where myresource (without the extension) is the file

        int imageResource = itemView.getContext().getResources().getIdentifier(uri, null, itemView.getContext().getPackageName());
        try {
            Drawable res = itemView.getContext().getResources().getDrawable(imageResource);
            sticker.setImageDrawable(res);
        } catch (Exception exception) {

            Toast.makeText(itemView.getContext(), "Images not loaded due to different version", Toast.LENGTH_SHORT).show();
        }

//        sticker.setImageDrawable(res);


    }
}

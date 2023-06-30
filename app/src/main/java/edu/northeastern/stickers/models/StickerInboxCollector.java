package edu.northeastern.stickers.models;

public class StickerInboxCollector {
    public String senderId;
    public String dateSent;
    public String sticker;

    public StickerInboxCollector(String senderId, String dateSent, String sticker) {
        this.senderId = senderId;
        this.dateSent = dateSent;
        this.sticker = sticker;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public String getDateSent() {
        return this.dateSent;
    }

    public String getSticker() {
        return this.sticker;
    }
}

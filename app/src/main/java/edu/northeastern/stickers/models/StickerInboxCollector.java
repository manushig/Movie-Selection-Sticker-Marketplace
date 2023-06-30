package edu.northeastern.stickers.models;

public class StickerInboxCollector {
    public Users senderId;
    public Users dateSent;
    public Users sticker;

    public StickerInboxCollector(Users senderId, Users dateSent, Users sticker) {
        this.senderId = senderId;
        this.dateSent = dateSent;
        this.sticker = sticker;
    }

    public Users getSenderId() {
        return this.senderId;
    }

    public Users getDateSent() {
        return this.dateSent;
    }

    public Users getSticker() {
        return this.sticker;
    }
}

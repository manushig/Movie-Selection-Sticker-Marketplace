package edu.northeastern.stickers.models;

public class StickerPack {
    private String stickerID;
    private String stickerName;
    private String stickerPath;

    private int sentCount;

    public StickerPack() {
    }

    public StickerPack(String stickerID, String stickerName, String stickerPath) {
        this.stickerID = stickerID;
        this.stickerName = stickerName;
        this.stickerPath = stickerPath;
    }

    public String getStickerID() {
        return stickerID;
    }

    public void setStickerID(String stickerID) {
        this.stickerID = stickerID;
    }

    public String getStickerName() {
        return stickerName;
    }

    public void setStickerName(String stickerName) {
        this.stickerName = stickerName;
    }

    public String getStickerPath() {
        return stickerPath;
    }

    public void setStickerPath(String stickerPath) {
        this.stickerPath = stickerPath;
    }

    public int getSentCount() {
        return sentCount;
    }

    public void setSentCount(int sentCount) {
        this.sentCount = sentCount;
    }
}

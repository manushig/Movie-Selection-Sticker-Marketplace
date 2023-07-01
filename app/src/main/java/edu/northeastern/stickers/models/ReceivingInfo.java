package edu.northeastern.stickers.models;

public class ReceivingInfo {
    private String receivedFromUserID;
    private String stickerReceivedID;
    private String receivedTimestamp;
    private String receivingStickerPath;

    public ReceivingInfo(String sendToUserID, String stickerSentID, String sentTimestamp, String receivingStickerPath) {
        this.receivedFromUserID = sendToUserID;
        this.stickerReceivedID = stickerSentID;
        this.receivedTimestamp = sentTimestamp;
        this.receivingStickerPath = receivingStickerPath;
    }

    public String getReceivedFromUserID() {
        return receivedFromUserID;
    }

    public void setReceivedFromUserID(String receivedFromUserID) {
        this.receivedFromUserID = receivedFromUserID;
    }

    public String getStickerReceivedID() {
        return stickerReceivedID;
    }

    public void setStickerReceivedID(String stickerReceivedID) {
        this.stickerReceivedID = stickerReceivedID;
    }

    public String getReceivedTimestamp() {
        return receivedTimestamp;
    }

    public String getReceivingStickerPath() {
        return receivingStickerPath;
    }

    public void setReceivingStickerPath(String receivingStickerPath) {
        this.receivingStickerPath = receivingStickerPath;
    }

    public void setReceivedTimestamp(String receivedTimestamp) {
        this.receivedTimestamp = receivedTimestamp;
    }
}
package edu.northeastern.stickers.models;

public class ReceivingInfo {
    private String receivedFromUserID;
    private String stickerReceivedID;
    private String receivedTimestamp;

    public ReceivingInfo(String sendToUserID, String stickerSentID, String sentTimestamp) {
        this.receivedFromUserID = sendToUserID;
        this.stickerReceivedID = stickerSentID;
        this.receivedTimestamp = sentTimestamp;
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

    public void setReceivedTimestamp(String receivedTimestamp) {
        this.receivedTimestamp = receivedTimestamp;
    }
}
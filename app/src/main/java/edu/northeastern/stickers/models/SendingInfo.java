package edu.northeastern.stickers.models;

public class SendingInfo {
    private String sendToUserID;
    private String stickerSentID;
    private String sentTimestamp;

    public SendingInfo(String sendToUserID, String stickerSentID, String sentTimestamp) {
        this.sendToUserID = sendToUserID;
        this.stickerSentID = stickerSentID;
        this.sentTimestamp = sentTimestamp;
    }


    public String getSendToUserID() {
        return sendToUserID;
    }

    public void setSendToUserID(String sendToUserID) {
        this.sendToUserID = sendToUserID;
    }

    public String getStickerSentID() {
        return stickerSentID;
    }

    public void setStickerSentID(String stickerSentID) {
        this.stickerSentID = stickerSentID;
    }

    public String getSentTimestamp() {
        return sentTimestamp;
    }

    public void setSentTimestamp(String sentTimestamp) {
        this.sentTimestamp = sentTimestamp;
    }
}

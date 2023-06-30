package edu.northeastern.stickers.models;

import java.util.List;

public class UserStickerHistory {
    String userId;
    List<StickerSentCount> stickerSentCountList;
    List<StickerReceivedCount> stickerReceivedCountList;

    public UserStickerHistory(String userId) {
        this.userId = userId;
    }

    public UserStickerHistory(String userId, List<StickerSentCount> stickerSentCountList, List<StickerReceivedCount> stickerReceivedCountList) {
        super();
        this.stickerSentCountList = stickerSentCountList;
        this.stickerReceivedCountList = stickerReceivedCountList;
    }

    public String getUserId() {
        return userId;
    }

    public List<StickerSentCount> getStickerSentCountList() {
        return stickerSentCountList;
    }

    public List<StickerReceivedCount> getStickerReceivedCountList() {
        return stickerReceivedCountList;
    }

    /**
     * Class to fetch data in this user's sending sticker activity
     */
    public static class StickerSentCount {
        java.lang.String stickerId;
        int sentCountNumber;

        public StickerSentCount(java.lang.String stickerId, int sentCountNumber) {
            this.stickerId = stickerId;
            this.sentCountNumber = sentCountNumber;
        }

        public java.lang.String getStickerId() {
            return stickerId;
        }

        public int getSentCountNumber() {
            return sentCountNumber;
        }
    }
    /**
     * Class to fetch data in this user's receiving sticker activity
     */
    class StickerReceivedCount {
        Users userSentSticker;
        java.lang.String stickerId;
        java.lang.String time;

        public StickerReceivedCount(Users userSentSticker, java.lang.String stickerId, java.lang.String time) {
            this.userSentSticker = userSentSticker;
            this.stickerId = stickerId;
            this.time = time;
        }

        public Users getUserSentSticker() {
            return userSentSticker;
        }

        public java.lang.String getStickerId() {
            return stickerId;
        }

        public java.lang.String getTime() {
            return time;
        }
    }
}


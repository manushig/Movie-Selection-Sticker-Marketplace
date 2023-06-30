package edu.northeastern.stickers;

import java.util.List;

import edu.northeastern.stickers.models.Users;

public class UserStickerHistory {
    Users user;
    List<StickerSentCount> stickerSentCountList;
    List<StickerReceivedCount> stickerReceivedCountList;

    public UserStickerHistory(Users user, List<StickerSentCount> stickerSentCountList, List<StickerReceivedCount> stickerReceivedCountList) {
        this.user = user;
        this.stickerSentCountList = stickerSentCountList;
        this.stickerReceivedCountList = stickerReceivedCountList;
    }

    public Users getUser() {
        return user;
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
    public class StickerSentCount {
        String stickerId;
        int sentCountNumber;

        public StickerSentCount(String stickerId, int sentCountNumber) {
            this.stickerId = stickerId;
            this.sentCountNumber = sentCountNumber;
        }

        public String getStickerId() {
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
        String stickerId;
        String time;

        public StickerReceivedCount(Users userSentSticker, String stickerId, String time) {
            this.userSentSticker = userSentSticker;
            this.stickerId = stickerId;
            this.time = time;
        }

        public Users getUserSentSticker() {
            return userSentSticker;
        }

        public String getStickerId() {
            return stickerId;
        }

        public String getTime() {
            return time;
        }
    }
}


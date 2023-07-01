package edu.northeastern.stickers;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.*;

public class NotificationSender {

    private static final String SERVER_KEY = "AAAA3WW5cxQ:APA91bEi-G14_H75cX5DzDEZ_VpqDXbgctUb-U6YqOkriI_cN0DC9dNmGsFqpBQWYU5lz4juFI0Vf8T-TiuWwjc_lIrb42m_X5YiA5EXAMADrhdBhrgFFx_BKepxvMBiEg2BQtfh2bvW";
    private static final String API_URL = "https://fcm.googleapis.com/fcm/send";

    public static void sendNotification(final String fcmToken, final String title, final String body, final String stickerPath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/json");
               // RequestBody requestBody = RequestBody.create(mediaType, buildNotificationPayload(fcmToken, title, body, stickerPath));
                String payloadObj=null;
                try {
                    payloadObj = payload(fcmToken,title,body,stickerPath).toString();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                RequestBody requestBody = RequestBody.create(mediaType, payloadObj);
                Request request = new Request.Builder()
                        .url(API_URL)
                        .post(requestBody)
                        .addHeader("Authorization", "key=" + SERVER_KEY)
                        .addHeader("Content-Type", "application/json")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        System.out.println("Notification sent successfully");
                    } else {
                        System.out.println("Failed to send notification: " + response.code() + " " + response.message());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static String buildNotificationPayload(String fcmToken, String title, String body, String stickerPath) {
        return "{"
                + "\"to\": \"" + fcmToken + "\","
                + "\"notification\": {"
                + "\"title\": \"" + title + "\","
                + "\"body\": \"" + body + "\","
                + "\"image\": \"" + stickerPath + "\""
                + "}"
                + "}";
    }

    private static JSONObject payload(String fcmToken, String title, String body, String stickerPath) throws JSONException {
        JSONObject notification = new JSONObject();
        JSONObject jsonObject = new JSONObject();

        notification.put("title", title);
        notification.put("body", body);
        notification.put("image", stickerPath);
        jsonObject.put("to", fcmToken);
        jsonObject.put("notification", notification);

        return jsonObject;
    }
}
package edu.northeastern.stickers.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import edu.northeastern.movieapi.R;
import edu.northeastern.stickers.FirebaseUtils;
import edu.northeastern.stickers.StickerHomeActivity;

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG = "MessagingService";
    private static final String CHANNEL_ID = "default_channel";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {
            handleDataMessage(remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            handleNotificationMessage(remoteMessage.getNotification());
        }
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        FirebaseUtils.saveFcmToken(token);
    }


    private void handleDataMessage(final java.util.Map<String, String> data) {
        final String stickerPath = data.get("stickerPath");

        downloadAndShowImage(stickerPath);

    }

    private void handleNotificationMessage(RemoteMessage.Notification notification) {
        String title = notification.getTitle();
        String body = notification.getBody();
        String stickerPath = notification.getImageUrl().toString();

        downloadAndShowNotification(title, body, stickerPath);
    }

    private void downloadAndShowNotification(String title, String body, String imageUrl) {
        Bitmap bitmap = downloadImage(imageUrl);

        if (bitmap != null) {
            showNotificationWithBigPicture(title, body, bitmap);
        } else {
            showNotificationWithTextOnly(title, body);
        }
    }

    private void showNotificationWithBigPicture(String title, String body, Bitmap bigPicture) {
        Intent intent = new Intent(this, StickerHomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(bigPicture)
                .setBigContentTitle(title)
                .setSummaryText(body);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24) // Replace with your small icon
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setStyle(bigPictureStyle)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create notification channel for Android Oreo and above
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Default Channel", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    private void showNotificationWithTextOnly(String title, String body) {
        Intent intent = new Intent(this, StickerHomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24) // Replace with your small icon
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create notification channel for Android Oreo and above
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Default Channel", NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    private Bitmap downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void downloadAndShowImage(String imageUrl) {
        Bitmap bitmap = downloadImage(imageUrl);
        if (bitmap != null) {
            Toast.makeText(this, "Success to download the image", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to download the image", Toast.LENGTH_SHORT).show();
        }
    }

    public void getAndSaveFcmToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String token = task.getResult();
                        FirebaseUtils.saveFcmToken(token);
                    } else {
                        // Handle error
                        Log.e("FCM Token", "Failed to retrieve FCM token: " + task.getException());
                    }
                });
    }

}

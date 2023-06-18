package edu.northeastern.movieapi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class AlertDialogHelper {

    static void showErrorDialog(Activity activity) {
        showDialog(activity, "An error occurred", "Try again later");
    }

    static void showEmptyResult(Activity activity) {
        showDialog(activity, "No results found", "Please check your search query");
    }
    private static void showDialog(Activity activity, String title, String message) {
        if (activity.isDestroyed() || activity.isFinishing()) {
            return;
        }
        new AlertDialog.Builder(activity, R.style.DialogTheme)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setMessage(message).create().show();
    }
}

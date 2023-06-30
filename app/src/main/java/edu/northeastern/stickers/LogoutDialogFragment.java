package edu.northeastern.stickers;

import android.content.Intent;
import android.os.Bundle;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.Dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;

import edu.northeastern.movieapi.R;

public class LogoutDialogFragment extends AppCompatDialogFragment {

    private LogoutDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setIcon(R.drawable.baseline_logout_black_24)
                .setTitle(R.string.logout_title)
                .setMessage(R.string.logout_message)
                .setPositiveButton(R.string.logout_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null) {
                            listener.onLogoutConfirmed();
                        }
                    }
                })
                .setNegativeButton(R.string.logout_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        return builder.create();
    }

    public void setLogoutDialogListener(LogoutDialogListener listener) {
        this.listener = listener;
    }
}
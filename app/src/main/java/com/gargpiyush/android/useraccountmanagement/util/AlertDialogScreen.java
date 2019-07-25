package com.gargpiyush.android.useraccountmanagement.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.gargpiyush.android.useraccountmanagement.R;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 07:44.
 */
public class AlertDialogScreen {
    public void showAlert(final Context context) {
        new AlertDialog
                .Builder(context, R.style.AlertDialogTheme)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.close_application_title)
                .setMessage(R.string.close_application_message)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((Activity) context).finish();
                    }
                })
                .setNegativeButton(R.string.negative, null)
                .show();
    }
}

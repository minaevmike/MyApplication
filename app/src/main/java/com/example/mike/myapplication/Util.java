package com.example.mike.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

/**
 * Created by mike on 02.10.14.
 */
public class Util {
    public static AlertDialog createAlertDialog(FragmentActivity fragmentActivity, String header, String body){
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
        builder.setTitle(header)
                .setMessage(body)
                .setCancelable(false)
                .setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        return alert;
    }
}

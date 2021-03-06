package com.example.mike.myapplication.quit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.mike.myapplication.R;
import com.example.mike.myapplication.quit.YesNoQuitListener;

/**
 * Created by Andrey
 * 04.10.2014.
 */
public class AppQuitDialogFragment extends DialogFragment {
    public static String TAG = "AppQuitDialogFragment";
    public static String Title = "Are you sure that you want to exit??";
    public static String Message = "From now you can't get very important information about weather";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof YesNoQuitListener)) {
            throw new ClassCastException(activity.toString() + " must implement YesNoListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(Title)
                .setIcon(R.drawable.exit)
                .setMessage(Message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((YesNoQuitListener) getActivity()).onQuitYes();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((YesNoQuitListener) getActivity()).onQuitNo();
                    }
                })
                .create();
    }
}
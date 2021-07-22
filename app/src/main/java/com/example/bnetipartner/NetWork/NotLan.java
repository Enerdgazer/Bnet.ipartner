package com.example.bnetipartner.NetWork;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.bnetipartner.R;
import com.example.bnetipartner.UI.MainActivity;

public class NotLan extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title)
                .setTitle("Нет сети").setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case Dialog.BUTTON_POSITIVE:
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                break;
                        }

                    }
                })
                .setMessage(R.string.message_text);
        return adb.create();
    }


}

package com.example.design.material.broadcastreceiver.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.design.material.broadcastreceiver.MainActivity;

/**
 * Created by zulup on 2/2/2018.
 */

public class ConnectionReciver extends BroadcastReceiver {

    private final String TAG = "asdf";


    @Override
    public void onReceive(Context context, Intent intent) {


        if (MainActivity.netWorkStatus(context) == 1) {

            MySnakeBar.getSnackbar().hideSnakeBar();

        } else if (MainActivity.netWorkStatus(context) == 0) {
            MySnakeBar.getSnackbar().showSnakeBar();
        }
    }
}

/*to change color of snakbar
* View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
* */

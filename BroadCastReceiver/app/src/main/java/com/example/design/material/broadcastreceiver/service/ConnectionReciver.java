package com.example.design.material.broadcastreceiver.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.example.design.material.broadcastreceiver.MainActivity;

/**
 * Created by zulup on 2/2/2018.
 */

public class ConnectionReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Snackbar snackbar = Snackbar.make(MainActivity.constraintLayout, "Not connected ", Snackbar.LENGTH_INDEFINITE);
        if (MainActivity.netWorkStatus(context) == 1) {
                Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show();
               snackbar.dismiss();
        } else
        {
            if(!snackbar.isShown()) {
                Toast.makeText(context,"no service",Toast.LENGTH_SHORT).show();
                snackbar.show();
            }
        }
    }
}

/*to change color of snakbar
* View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
* */

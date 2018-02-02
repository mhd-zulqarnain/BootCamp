package com.example.design.material.broadcastreceiver;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.design.material.broadcastreceiver.service.ConnectionReciver;

public class MainActivity extends AppCompatActivity {

    ConnectionReciver broadCastReciver;
    private final static int NET_CONNECTED = 1;
    private final static int MBL_NET_CONNECTED = 2;
    private final static int CONN_FAILED = 0;
    public static ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadCastReciver = new ConnectionReciver();
        registerReceiver(broadCastReciver, new IntentFilter());
        constraintLayout = findViewById(R.id.constraintLayout);


    }

    public static int netWorkStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (null != info) {
            if (info.getType() == NET_CONNECTED || info.getType() == MBL_NET_CONNECTED)
                return NET_CONNECTED;
        }
        return CONN_FAILED;
    }

}


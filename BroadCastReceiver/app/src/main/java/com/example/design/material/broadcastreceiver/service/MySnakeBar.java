package com.example.design.material.broadcastreceiver.service;

import android.support.design.widget.Snackbar;

import com.example.design.material.broadcastreceiver.MainActivity;

/**
 * Created by faizrehman on 07/02/2018.
 */

public class MySnakeBar {
    public static Snackbar snackbar;
    private static MySnakeBar mySnakeBar;

    MySnakeBar() {
        this.snackbar = Snackbar.make(MainActivity.constraintLayout, "Not connected ", Snackbar.LENGTH_INDEFINITE);

    }

    public static MySnakeBar getSnackbar() {
        if (snackbar != null)
            return mySnakeBar;
        else {
            return mySnakeBar = new MySnakeBar();

        }
    }

    public void showSnakeBar() {
        snackbar.show();
    }

    public void hideSnakeBar() {
        snackbar.dismiss();
    }


}

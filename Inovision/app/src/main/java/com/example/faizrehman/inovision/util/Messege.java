package com.example.faizrehman.inovision.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by faizrehman on 08/02/2018.
 */
public class Messege {
    public static void messege(Context context, String args){
        Toast.makeText(context,args,Toast.LENGTH_LONG).show();
    }
}
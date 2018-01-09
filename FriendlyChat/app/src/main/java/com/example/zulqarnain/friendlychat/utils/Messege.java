package com.example.zulqarnain.friendlychat.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Zul Qarnain on 11/2/2017.
 */

public class Messege {
public static void messege(Context context, String args){
    Toast.makeText(context,args,Toast.LENGTH_LONG).show();
}
}

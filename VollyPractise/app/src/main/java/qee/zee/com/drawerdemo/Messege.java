package qee.zee.com.drawerdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Zul Qarnain on 1/4/2018.
 */

public class Messege {
    public static void messege(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}

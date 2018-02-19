package fahee.example.com.inovisionhome.util;


import android.content.Context;
import android.widget.Toast;

public class Messege {
    public static void message(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}

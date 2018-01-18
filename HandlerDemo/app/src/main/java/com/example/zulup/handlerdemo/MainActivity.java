package com.example.zulup.handlerdemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Thread thread;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.text);
        thread = new Thread(new MyHandler());
        thread.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1);
            }
        };
    }

    public class MyHandler implements Runnable {

        @Override
        public void run() {
            for(int i =0 ;i<100;i++){
                Message mssg = Message.obtain();
                mssg.arg1 = i;
                boolean b = handler.sendMessage(mssg);

            }
        }
    }
}

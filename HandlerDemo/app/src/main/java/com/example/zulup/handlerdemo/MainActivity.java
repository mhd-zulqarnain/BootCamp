package com.example.zulup.handlerdemo;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Thread thread;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.text);
        thread = new Thread(new MyHandler());
        thread.start();

    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getBaseContext(), msg.arg1 + "test", Toast.LENGTH_SHORT).show();

        }
    };

    class MyHandler implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Message message = Message.obtain();
                if (message != null) {
                    message.arg1 = i;
                    handler.sendMessage(message);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

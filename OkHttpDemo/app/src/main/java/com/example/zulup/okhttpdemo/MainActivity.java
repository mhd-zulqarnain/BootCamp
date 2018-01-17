package com.example.zulup.okhttpdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    OkHttpClient clint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text_view);
        clint = new OkHttpClient();
    }

    public void getOkRequest(View view) {
        final Request request = new Request.Builder().url("https://raw.github.com/square/okhttp/master/README.md").build();
        clint.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(final Response response) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tv.setText(response.body().toString());
                        } catch (Exception e) {
                            Log.d("", "run: error");
                        }

                    }
                });

            }
        });
    }

    public void postReq(View view) {
        String url = "https://jsonplaceholder.typicode.com/posts/";
        MediaType type = MediaType.parse("application/json;charset=utf-8");
        Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
        JSONObject actualData = new JSONObject();
        try {

            actualData.put("userId",2);
            actualData.put("id",2);
            actualData.put("title","test");
            actualData.put("body","test");
            RequestBody requestBody = RequestBody.create(type,actualData.toString());

            Request request =new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        }catch (Exception e){

        }

    }
}

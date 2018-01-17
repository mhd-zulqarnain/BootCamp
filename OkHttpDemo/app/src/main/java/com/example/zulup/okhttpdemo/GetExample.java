package com.example.zulup.okhttpdemo;


import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zulup on 1/15/2018.
 */

public class GetExample {
    private static OkHttpClient client = new OkHttpClient();

    public static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response
        response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            Log.d("", "run: "+e);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
}
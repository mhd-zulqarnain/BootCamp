package qee.zee.com.drawerdemo;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class TestActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    private TextView tv;
    private String url = "https://jsonplaceholder.typicode.com/posts/1";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.text_view);
        requestQueue = Volley.newRequestQueue(this);

    }

    public void getRes(View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv.setText(response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplication(), "error " + error, Toast.LENGTH_LONG).show();

                    }
                });
        requestQueue.add(stringRequest);
    }

    public void postTest(View view) {
        String posturl = "https://jsonplaceholder.typicode.com/posts";

        StringRequest postRequest = new StringRequest(Request.Method.POST, posturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getBaseContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userId", "14124");
                params.put("id", "13123123");
                params.put("title", "th title");
                params.put("body", "th title");

                return params;
            }
        };
        requestQueue.add(postRequest);

    }

/*
* Post request using okHttp
* */
    public void getOkHttp(View view) throws IOException {
        PostTask task = new PostTask();
        task.execute();
    }


    /*asyntask for  aysntak*/
    public class PostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = post("https://jsonplaceholder.typicode.com/posts");
            try {
                return response;
            } catch (Exception e) {
                Log.d("", "doInBackground: exception"+e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("response"+s);
            Messege.messege(getApplicationContext(),s);
        }

        public String post(String url) {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            JSONObject params = new JSONObject();
            OkHttpClient client = new OkHttpClient();
            try {
                params.put("userId", "14124");
                params.put("id", "13123123");
                params.put("title", "th title");
                params.put("body", "th title");

                RequestBody body = RequestBody.create(JSON, params.toString());
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                okhttp3.Response response = client.newCall(request).execute();
                return response.body().toString();
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;

        }
    }
}

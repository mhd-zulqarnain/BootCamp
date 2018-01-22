package com.example.zulup.authenticationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;

/**
 * Created by zulup on 1/22/2018.
 */

public class HomeFragment extends Fragment {
    private TextView textView;
    private Button btnLogout;
    String firstName;
    String lastName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        firstName = bundle.getString("first_name");
        lastName = bundle.getString("last_name");
        Log.d("", "onCreate: " + firstName);
        Log.d("", "onCreate first: " + lastName);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.home_fragement_view, container, false);
        textView = v.findViewById(R.id.text_response);
        btnLogout = v.findViewById(R.id.fb_logout_btn);
//        imageView.setImageBitmap();
        textView.setText(firstName + " " + lastName);

        logout();

        return v;
    }

    private void logout() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AccessToken.getCurrentAccessToken() == null) {
                    textView.setText("No user found");
                }
                LoginManager.getInstance().logOut();
                textView.setText("logout");
            }
        });
    }

}

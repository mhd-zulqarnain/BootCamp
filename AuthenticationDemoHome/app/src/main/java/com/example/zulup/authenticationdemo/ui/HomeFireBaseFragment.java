package com.example.zulup.authenticationdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zulup.authenticationdemo.R;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

/**
 * Created by zulup on 1/22/2018.
 */

public class HomeFireBaseFragment extends Fragment {
    private TextView textView;
    private Button btnLogout;
    private ImageView imageView;
    String userName;
    FirebaseAuth mAuth;
    String imgUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        userName = bundle.getString("username");
        imgUrl = bundle.getString("img_url");
        mAuth = FirebaseAuth.getInstance();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.home_fragement_view, container, false);
        textView = v.findViewById(R.id.text_response);
        btnLogout = v.findViewById(R.id.fb_logout_btn);
        imageView = v.findViewById(R.id.prf_img);
//        imageView.setImageBitmap();
        textView.setText(userName);
        Picasso.with(container.getContext()).load(imgUrl).into(imageView);

        logout();

        return v;
    }

    private void logout() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAuth.getCurrentUser() != null) {
                    mAuth.signOut();
                    btnLogout.setVisibility(View.GONE);
                    imageView.setImageBitmap(null);
                    Fragment gFragment = getActivity().getSupportFragmentManager().findFragmentByTag("GOOGLE_FRAG");
                    Fragment fFragment = getActivity().getSupportFragmentManager().findFragmentByTag("FACEBOOK_FRAG");
                    if (gFragment != null) {
                        Button btn = getActivity().findViewById(R.id.google_button);
                        btn.setText("GOOGLE");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(gFragment).commit();

                    }
                    if (fFragment != null) {
                        Button btn = getActivity().findViewById(R.id.face_button);
                        btn.setText("FACEBOOK");
                        getActivity().getSupportFragmentManager().beginTransaction().remove(fFragment).commit();

                    }
                }
            }
        });
    }

}

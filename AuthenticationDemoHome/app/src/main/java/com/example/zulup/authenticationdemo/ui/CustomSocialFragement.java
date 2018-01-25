package com.example.zulup.authenticationdemo.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zulup.authenticationdemo.FacebookHomeFragment;
import com.example.zulup.authenticationdemo.R;
import com.example.zulup.authenticationdemo.TwitterHomeFragment;
import com.example.zulup.authenticationdemo.utils.Messege;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import retrofit2.Call;

public class CustomSocialFragement extends Fragment {

    CallbackManager callbackManager;
    Button CustomFacebookLoginButton;
    Button CustomTwitterLoginButton;
    Button CustomGoogleLoginButton;
    TextView textView;
    TwitterAuthClient mTwitterAuthClient;
    private int RC_GOOGLE = 90;
    private Bundle args;

    private GoogleSignInClient mGoogleSignInClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        args = new Bundle();

        textView = view.findViewById(R.id.result_facebook);
        callbackManager = CallbackManager.Factory.create();
        CustomFacebookLoginButton = view.findViewById(R.id.login_button);
        CustomTwitterLoginButton = view.findViewById(R.id.twt_login_button);
        CustomGoogleLoginButton = view.findViewById(R.id.google_button);

        mTwitterAuthClient = new TwitterAuthClient();
        loginWithTwt();
        loginWithFb();
        loginWithGoogle();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(getContext());
        args = new Bundle();


    }

    private void loginWithTwt() {

        TwitterConfig config = new TwitterConfig.Builder(getActivity())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("Wi5dqnFtlbykhUbHYmyaJzyUM",
                        "9mxOThtOCUNs9N6sqIkKf57e7kFpI34xHlAKxeefKFt9DWqC3w"))
                .debug(true)
                .build();
        Twitter.initialize(config);

        CustomTwitterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTwitterAuthClient.authorize(getActivity(), new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                        AccountService accountService = twitterApiClient.getAccountService();

                        Call<User> call = accountService.verifyCredentials(true, true, true);
                        call.enqueue(new Callback<com.twitter.sdk.android.core.models.User>() {
                            @Override
                            public void success(Result<com.twitter.sdk.android.core.models.User> result) {


                                args.putString("username", result.data.name.toString());
                                args.putString("img_url", result.data.profileImageUrl.toString());

                                TwitterHomeFragment homeFragment = new TwitterHomeFragment();
                                homeFragment.setArguments(args);
                                FragmentTransaction ftransaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment);
                                ftransaction.commit();
                            }

                            @Override
                            public void failure(TwitterException exception) {
                                Messege.messege(getContext(), "inner exxeption");

                            }
                        });


                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Messege.messege(getContext(), "Exception" + exception);
                        Log.d("", "failure: " + exception);
                    }
                });
            }
        });

    }

    private void loginWithFb() {

        printhashkey();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                graphRequest(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        CustomFacebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(CustomSocialFragement.this, Arrays.asList("public_profile", "email", "user_friends"));

            }
        });
    }

    public void loginWithGoogle() {
        CustomGoogleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_GOOGLE);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            Toast.makeText(getContext(), account.getEmail() + "" + account.getDisplayName(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("", "handleSignInResult:Exception " + e);
        }
    }

    public void graphRequest(AccessToken token) {

        GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
//                    textView.setText(object.toString());\
                    args.putString("username", object.getString("first_name"));
                    args.putString("img_url", object.getJSONObject("picture").getJSONObject("data").getString("url"));

                    FacebookHomeFragment homeFragment = new FacebookHomeFragment();
                    homeFragment.setArguments(args);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment);
                    transaction.commit();

                } catch (Exception e) {
                    Toast.makeText(getContext(), "Exception" + e, Toast.LENGTH_SHORT).show();
                }

            }
        });


        Bundle b = new Bundle();
        b.putString("fields", "id,email,first_name,last_name,picture.type(large)");
        request.setParameters(b);
        request.executeAsync();

    }

    public void printhashkey() {

        try {


            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.example.zulup.authenticationdemo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {

        }
    }
}

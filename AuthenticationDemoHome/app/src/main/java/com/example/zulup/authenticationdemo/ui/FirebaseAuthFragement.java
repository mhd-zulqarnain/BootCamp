package com.example.zulup.authenticationdemo.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zulup.authenticationdemo.R;
import com.example.zulup.authenticationdemo.utils.Messege;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FirebaseAuthFragement extends Fragment {
    private GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    private int RC_GOOGLE = 90;
    private Button btnGoogle;
    private Button customFacebookLoginButton;
    private Button customTwtLoginButton;
    CallbackManager callbackManager;
    TwitterAuthClient mTwitterAuthClient;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_firebase, container, false);
        btnGoogle = v.findViewById(R.id.google_button);
        mTwitterAuthClient = new TwitterAuthClient();
        customFacebookLoginButton = v.findViewById(R.id.face_button);
//        customTwtLoginButton = v.findViewById(R.id.twt_button);
        loginWithGoogle();
        loginWithFb();
//        loginWithtwt();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    public void loginWithGoogle() {
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_GOOGLE);
            }
        });

    }
/*
    private void loginWithtwt() {
        TwitterConfig config = new TwitterConfig.Builder(getContext())
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("Wi5dqnFtlbykhUbHYmyaJzyUM",
                        "9mxOThtOCUNs9N6sqIkKf57e7kFpI34xHlAKxeefKFt9DWqC3w"))
                .debug(true)
                .build();
        Twitter.initialize(config);
        customTwtLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTwitterAuthClient.authorize(getActivity(), new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {
                        Messege.messege(getContext(),"success: "+TwitterCore.getInstance().getApiClient().getAccountService());
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Messege.messege(getContext(),"Execption:"+exception);

                    }
                });
            }
        });

    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GOOGLE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account);

            } catch (Exception e) {
                Messege.messege(getActivity(), "exception " + e);
                Log.d("", "onActivityResult: " + e);
            }

        }
    }

    private void firebaseAuth(GoogleSignInAccount acct) {
        Log.d("", "firebaseAuthWithGoogle:" + acct.getId());
        Log.d("", "firebaseAuthWithGoogle:" + acct.getIdToken());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (!user.getDisplayName().equals(null)) {
                                Bundle args = new Bundle();
                                btnGoogle.setText(user.getDisplayName());
                                args.putString("username", user.getDisplayName());
                                args.putString("img_url", String.valueOf(user.getPhotoUrl()));
                                HomeFireBaseFragment homeFireBaseFragment = new HomeFireBaseFragment();
                                homeFireBaseFragment.setArguments(args);
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.firebase_fragement_container, homeFireBaseFragment, "GOOGLE_FRAG");
                                transaction.commit();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Messege.messege(getContext(), "failed" + task.getException());
                            Log.d("", "onComplete: " + task.getException());

                        }

                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loginWithFb() {
        callbackManager = CallbackManager.Factory.create();
        printhashkey();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                firebaseAuthFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Messege.messege(getActivity(), "FacebookException" + error);

            }
        });
        customFacebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(FirebaseAuthFragement.this, Arrays.asList("public_profile", "email", "user_friends"));

            }
        });
    }

    private void firebaseAuthFacebook(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (!user.getDisplayName().equals(null)) {
                                Bundle args = new Bundle();
                                customFacebookLoginButton.setText(user.getDisplayName());
                                args.putString("username", user.getDisplayName());
                                args.putString("img_url", String.valueOf(user.getPhotoUrl()));
                                HomeFireBaseFragment homeFireBaseFragment = new HomeFireBaseFragment();
                                homeFireBaseFragment.setArguments(args);
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.firebase_fragement_container, homeFireBaseFragment, "FACEBOOK_FRAG");
                                transaction.commit();
                            }
                        } else {
                            Messege.messege(getActivity(), "Execption" + task.getException());
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuth.getCurrentUser() != null) {
            Log.d("", "signInWithCredential:success");
            mAuth.signOut();
        }
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

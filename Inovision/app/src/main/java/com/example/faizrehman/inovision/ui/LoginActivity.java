package com.example.faizrehman.inovision.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.faizrehman.inovision.R;
import com.example.faizrehman.inovision.util.Messege;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Messege.messege(this,"Login ");
    }
}

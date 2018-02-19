package fahee.example.com.inovisionhome.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fahee.example.com.inovisionhome.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void login(View v){
        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
    }
}

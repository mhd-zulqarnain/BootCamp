package fahee.example.com.inovisionhome.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fahee.example.com.inovisionhome.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void login(View view){
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }   public void signup(View view){
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
    }
}

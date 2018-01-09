package com.example.zulqarnain.friendlychat;

import android.hardware.camera2.params.LensShadingMap;
import android.media.Image;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.zulqarnain.friendlychat.utils.Messege;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mSend;
    private EditText mMessege;
    private ImageView  mImagePicker;
    private final static int DEFAULT_EDIT_TEXT_SIZE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSend = (Button) findViewById(R.id.sendButton);
        mMessege = (EditText) findViewById(R.id.messageEditText);
        mImagePicker  = (ImageView) findViewById(R.id.photoPickerButton);


        mMessege.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().trim().length()>0){
                    mSend.setEnabled(true);
                }
                else
                    mSend.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mMessege.setFilters( new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_EDIT_TEXT_SIZE)});
        //Image picker setting
        mImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  =getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.sign_out_menu){
            Messege.messege(getBaseContext(),"Logout");
        }
        return  true;
    }
}

package qee.zee.com.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    EditText edWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this,this);
        edWord = findViewById(R.id.ed_text);


// Set drawables for left, top, right, and bottom - send 0 for nothing
        edWord.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

    }
    public void listenVoice(View v){
        String text =edWord.getText().toString();
          if(text.equals("")){
              Toast.makeText(this,"Filed is empty",Toast.LENGTH_SHORT).show();
          }
          else
              tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }

    public void speakVoice(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
        try{
            startActivityForResult(intent,200);
        }catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Intent problem", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK && data != null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edWord.setText(result.get(0));
            }
        }
    }

    @Override
    public void onInit(int status) {

        if(status==TextToSpeech.SUCCESS){
            Locale loc=tts.getLanguage();
            int res = tts.setLanguage(loc);
        }
    }
}

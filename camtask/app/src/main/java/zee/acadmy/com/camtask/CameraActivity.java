package zee.acadmy.com.camtask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        askPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,11);
        askPermission(Manifest.permission.RECORD_AUDIO,2);
        askPermission(Manifest.permission.ACCESS_FINE_LOCATION,3);


    }
    public void askPermission(String permission,int req){
        if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission}, req);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            Toast.makeText(getBaseContext(), "Permission granted",Toast.LENGTH_SHORT).show();

    }

    public void takepic(View v){
     /*   Intent intent = new Intent(
                MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        this.startActivity(intent);*/
        //c = Camera.open();
    }
}

package com.waniapp.qrcode.scanner.reader.android;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_ASK_PERMISSION = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        allowPermisions();
    }

    public boolean allowPermisions() {
        int permisoCamara = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);

        if(permisoCamara == PackageManager.PERMISSION_DENIED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_CODE_ASK_PERMISSION);
            }
        }

        if(permisoCamara == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        return false;
    }

    public void buttonAccept(View view) {
        if(allowPermisions()) {
            Intent qreader = new Intent(this, com.waniapp.qrcode.scanner.reader.android.Qreader.class);
            startActivity(qreader);
        }
    }

}
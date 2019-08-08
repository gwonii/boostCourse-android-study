package com.gmail.hc.gwnoii.dynamicreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    public static final int PERMISSIONS_REQUEST_RECEIVE_SMS = 1001;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        DynamicReceiver dynamicReceiver = new DynamicReceiver();

        registerReceiver(dynamicReceiver,intentFilter);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECEIVE_SMS}, PERMISSIONS_REQUEST_RECEIVE_SMS);

            Log.d(TAG, "RECEIVE_SMS 권한을 얻었습니다..");

        } else {
            Log.d(TAG, "RECEIVE_SMS 권한이 이미 부여되어있습니다.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "사용자가 SMS 권한에 동의하셨습니다.");
        } else if (grantResults.length < 0 || grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Log.d(TAG, "사용자가 SMS 권한에 동의하지 않으셨습니다.");
        }
    }
}

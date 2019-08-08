package com.gmail.hc.gwnoii.dynamicreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class SmsActivity extends AppCompatActivity {

    public static final String SMS_ACT_TAG = "SmsActivity";

    EditText sender;
    EditText contents;
    EditText receviedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        sender = findViewById(R.id.etSender);
        contents = findViewById(R.id.etContents);
        receviedDate = findViewById(R.id.etDate);

        Intent passedIntent = getIntent();
        processPassedIntent(passedIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void processPassedIntent(Intent passedIntent) {

        if (passedIntent != null) {
            String senderMessage = passedIntent.getStringExtra("sender");
            String contentsMessage = passedIntent.getStringExtra("contents");
            String receivedDateMessage = passedIntent.getStringExtra("receivedDate");

            Log.d("SMS_ACT_TAG", "receivedDateMessage : " + receivedDateMessage);

            sender.setText(senderMessage);
            contents.setText(contentsMessage);
            receviedDate.setText(receivedDateMessage);
        }
    }
}

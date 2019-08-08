package com.gmail.hc.gwnoii.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 실행됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages != null) {
            if (messages.length > 0) {
                String sender = messages[0].getOriginatingAddress();
                Log.d(TAG, "sender : " + sender);

                String contents = messages[0].getMessageBody();
                Log.d(TAG, "contents : " + contents);

                Date receivedDate = new Date(messages[0].getTimestampMillis());
                Log.d(TAG, "receivedDate : " + receivedDate);

                sendToSmsActivity(context, sender,contents, receivedDate);
            }
        }
    }

    private void sendToSmsActivity(Context context, String sender, String contents, Date receivedDate) {
        Intent intent = new Intent(context,SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        |Intent.FLAG_ACTIVITY_SINGLE_TOP
                        |Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("sender",sender);
        intent.putExtra("contents", contents);
        intent.putExtra("receivedDate", dateFormat.format(receivedDate));

        context.startActivity(intent);

    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages;

        if (objects != null) {
            messages = new SmsMessage[objects.length];

            for ( int i = 0; i < objects.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = bundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[])objects[i], format);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[])objects[i]);
                }
            }
        } else {
          return null;
        }

        return messages;
    }
}

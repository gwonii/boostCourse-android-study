package com.gmail.hc.gwnoii.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";

    TextView result;
    Button createThread;
    Button confirm;

    ValueHandler handler;
    Handler basicHandler;

    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.tvResult);
        createThread = findViewById(R.id.btCreateThread);
        confirm = findViewById(R.id.btConfirm);

        handler = new ValueHandler();
        basicHandler = new Handler();

        createThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread1 = new BackgroundThread();
                thread1.start();

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {

                    boolean running = false;

                    @Override
                    public void run() {

                        running = true;
                        while (running) {
                            value += 17;

                            basicHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    String curString = "현재 값 : " + value;
                                   result.setText(curString);
                                }
                            });


                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                Log.d(TAG, "sleep 도중 에러가 발생하였습니다. ");
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();
            }
        });

//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String curString = "현재 값 : " + value;
//                result.setText(curString);
//            }
//        });

    }

    class BackgroundThread extends Thread {

        boolean running = false;

        @Override
        public void run() {
            running = true;

            while (running) {
                value += 1;

                Message message = handler.obtainMessage();      //  핸들러에게 보낼 메시지 정의
                Bundle bundle = new Bundle();                   //  정보를 담을 bundle 정의
                bundle.putInt("value", value);                  //  bundle에 key값과 int값을 저장
                message.setData(bundle);                        //  메시지에 보낼 bundle을 설정
                handler.sendMessage(message);                   //  핸들러에 메시지를 전달

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Log.d(TAG, "sleep 도중 에러가 발생하였습니다. ");
                    e.printStackTrace();
                }
            }

        }
    }



    class ValueHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");

            String curString = "현재 값 : " + value;
            result.setText(curString);
        }
    }
}

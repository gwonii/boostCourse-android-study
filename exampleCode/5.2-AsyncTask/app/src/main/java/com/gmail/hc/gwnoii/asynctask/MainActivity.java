package com.gmail.hc.gwnoii.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";


    TextView result;
    Button createThread;

    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = findViewById(R.id.tvResult);
        createThread = findViewById(R.id.btCreateThread);

        handler = new Handler();

        createThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlusTask plusTask = new PlusTask();
                plusTask.execute("시작");

            }
        });
    }

    class PlusTask extends AsyncTask<String, Integer, Integer> {

        int value = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... strings) {

            boolean running = true;
            while (running) {
                value += 1;


                publishProgress(value);

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Log.d(TAG, "sleep 도중 에러가 발생하였습니다. ");
                    e.printStackTrace();
                }

            }
            
            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);

            String curString = "현재 값 : " + value;
            result.setText(curString);
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }
}

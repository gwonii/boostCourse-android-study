package com.gmail.hc.gwnoii.client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Button mainButton;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        mainButton = findViewById(R.id.btMain);
        mainText = findViewById(R.id.tvMain);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });


    }


    class ClientThread extends Thread {

        String host = "localHost";
        int port = 5001;

        @Override
        public void run() {

            Socket socket;
            {
                try {
                    socket = new Socket(host, port);

                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    os.writeObject("안녕");

                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    Object input;

                    try {
                        input = is.readObject();
                        final Object finalInput = input;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                String curString = finalInput.toString();
                                mainText.setText(curString);
                            }
                        });


                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
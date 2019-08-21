package com.gmail.hc.gwnoii.server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainButton = findViewById(R.id.btMain);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerThread thread = new ServerThread();
                thread.start();
            }
        });
    }


    class ServerThread extends Thread {

        int port = 5001;

        @Override
        public void run() {

            try {
                ServerSocket serverSocket = new ServerSocket(port);     // 서버를 위한 서버소켓

                while(true) {
                    Socket socket = serverSocket.accept();                              // 데이터를 주고 받기 위한 소켓

                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    Object input = new Object();
                    try {
                        input = is.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    os.writeObject(input + "from server");
                    os.flush();

                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.gmail.hc.gwnoii.myinflater;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout container_fl;
    Button onfl_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onfl_button = findViewById(R.id.onfl_button);
        onfl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container_fl = findViewById(R.id.container_fl);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1, container_fl, true);
            }
        });
    }
}

package com.gmail.hc.gwnoii.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                SimpleData firstData = new SimpleData(27,"최호권");
                SimpleData SecondData = new SimpleData(27,"최우영");

                intent.putExtra("simpleData", firstData);
                startActivityForResult(intent, 1001);

            }
        });
    }
}

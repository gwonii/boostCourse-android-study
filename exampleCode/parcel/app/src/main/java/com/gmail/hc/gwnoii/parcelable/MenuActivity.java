package com.gmail.hc.gwnoii.parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button mainButton = findViewById(R.id.main_button);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    private void processIntent(Intent intent){
        SimpleData data = intent.getParcelableExtra("simpleData");

        if(data != null){
            Toast.makeText(getApplicationContext(), "이름 : " + data.getName() + " , 나이 : " + data.getNumber(), Toast.LENGTH_LONG).show();
        }
    }
}

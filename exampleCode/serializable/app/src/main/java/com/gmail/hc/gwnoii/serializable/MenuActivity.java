package com.gmail.hc.gwnoii.serializable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

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

    private void processIntent(Intent passedIntent){
        if( passedIntent != null){
            ArrayList<String> names = (ArrayList<String>)passedIntent.getSerializableExtra("names");

            if(names != null){
                Toast.makeText(getApplicationContext(), "전달 받은 사람 수 : " + names.size(), Toast.LENGTH_LONG ).show();
            }
        }
    }
}

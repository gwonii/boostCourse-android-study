package com.gmail.hc.gwnoii.serializable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
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

                ArrayList<String> names = new ArrayList<>();
                names.add("호권이요");
                names.add("구차하오");          // MenuActivity 에 보낼 데이터 정의

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);


                intent.putExtra("names", names);

                startActivityForResult(intent, 1001);
            }
        });
    }
}

package com.gmail.hc.gwnoii.includeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button titleButton1;
    Button titleButton2;
    Button titleButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View includeOne = findViewById(R.id.set_time1);
        View includeTwo = findViewById(R.id.set_time2);
        View includeThr = findViewById(R.id.set_time3);

        TextView title1 = includeOne.findViewById(R.id.tv_title);
        titleButton1 = includeOne.findViewById(R.id.bt_title_button);

        TextView title2 = includeTwo.findViewById(R.id.tv_title);
        titleButton2 = includeTwo.findViewById(R.id.bt_title_button);

        TextView title3 = includeThr.findViewById(R.id.tv_title);
        titleButton3 = includeThr.findViewById(R.id.bt_title_button);

        titleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "지금 누른 버튼은 : 첫번째 버튼", Toast.LENGTH_LONG).show();
            }
        });

        titleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "지금 누른 버튼은 : 두번째 버튼", Toast.LENGTH_LONG).show();
            }
        });

        titleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "지금 누른 버튼은 : 세번째 버튼 ", Toast.LENGTH_LONG).show();
            }
        });

    }
}

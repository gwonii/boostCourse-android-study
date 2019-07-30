package com.gmail.hc.gwnoii.callintent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newButton = findViewById(R.id.new_button);
        phoneNum = findViewById(R.id.et_input_number);

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = phoneNum.getText().toString();

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);

            }
        });
    }
}

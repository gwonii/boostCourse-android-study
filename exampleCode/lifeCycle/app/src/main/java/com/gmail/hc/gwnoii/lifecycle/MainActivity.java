package com.gmail.hc.gwnoii.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate 메소드 실행", Toast.LENGTH_LONG).show();

        Button btTurnOff = findViewById(R.id.btTurnOff);
        Button btTest = findViewById(R.id.btTest);

        btTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);       // 저장시켜놓을 SharePreference를 이름과 설정에 맞게 정의하고
                if(pref != null){
                    String name = pref.getString("name", "");       // 첫번째 파라미터는 전달받을 name, 만약 해당하는 내용이 없다면 "" 빈 String을 달라

                    Toast.makeText(getApplicationContext(),"전달받은 문자열 : " + name, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart 메소드 실행", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop 메소드 실행", Toast.LENGTH_LONG).show();

    }

    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause 메소드 실행", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);       // 저장시켜놓을 SharePreference를 이름과 설정에 맞게 정의하고
        SharedPreferences.Editor editor = pref.edit();                                            // edit에다가 pref를 edit()하여 저장시켜 놓는다.
        editor.putString("name", "안녕하세요");
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy 메소드 실행", Toast.LENGTH_LONG).show();



    }

    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume 메소드 실행", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);       // 저장시켜놓을 SharePreference를 이름과 설정에 맞게 정의하고
        if(pref != null){
            String name = pref.getString("name", "");       // 첫번째 파라미터는 전달받을 name, 만약 해당하는 내용이 없다면 "" 빈 String을 달라

            Toast.makeText(this,"전달받은 문자열 : " + name, Toast.LENGTH_SHORT).show();
        }
    }
}

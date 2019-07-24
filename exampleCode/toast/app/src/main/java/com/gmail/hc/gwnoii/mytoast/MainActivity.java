package com.gmail.hc.gwnoii.mytoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_toast1 = findViewById(R.id.bt_toast1);
        Button bt_toast2 = findViewById(R.id.bt_toast2);

        bt_toast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(),"위치가 바뀐 토스트",Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.TOP|Gravity.LEFT,50,50);
                toast1.show();
            }
        });

        bt_toast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast
                        ,(ViewGroup)findViewById(R.id.toast_board));
                TextView textView = findViewById(R.id.tv_toast);
//                textView.setText("");       // 에러구간

                Toast myToast = new Toast(getApplicationContext());
                myToast.setGravity(Gravity.CENTER,0,-100);         // 위치 잡아주고
                myToast.setDuration(Toast.LENGTH_LONG);                       // 지속시간 잡아주고
                myToast.setView(layout);
                myToast.show();                                               // 보여주고

            }
        });

    }
}

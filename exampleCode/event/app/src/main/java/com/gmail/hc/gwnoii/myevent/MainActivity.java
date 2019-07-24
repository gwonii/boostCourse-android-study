package com.gmail.hc.gwnoii.myevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_contents);

        View view1 = findViewById(R.id.v_button1);
        View view2 = findViewById(R.id.v_button2);
        View view3 = findViewById(R.id.v_button3);

        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "안녕하세요", Toast.LENGTH_LONG).show();
            }
        });

        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();         // event.getAction()은 리턴 값이 int임을 알 수 있다.

                float curX = event.getX();
                float curY = event.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    println("포인트가 눌렸음" +"x좌표 : "+ curX + " , y좌표 : " + curY );
                } else if ( action == MotionEvent.ACTION_MOVE){
                    println("포인트가 움직임" +"x좌표 : "+ curX + " , y좌표 : " + curY );
                } else if ( action == MotionEvent.ACTION_UP){
                    println("포인트가 떼어짐" +"x좌표 : "+ curX + " , y좌표 : " + curY );
                }
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}

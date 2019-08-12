package com.gmail.hc.gwnoii.menubar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();

        switch (curId) {
            case R.id.menuStar : {
                Toast.makeText(this, "즐겨찾기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menuPlus : {
                Toast.makeText(this, "확대하기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menuMinus : {
                Toast.makeText(this, "축소하기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

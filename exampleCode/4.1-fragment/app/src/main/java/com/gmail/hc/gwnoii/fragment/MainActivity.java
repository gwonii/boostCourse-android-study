package com.gmail.hc.gwnoii.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mainButton;
    private Button menuButton;

    private MainFragment mainFragment;
    private MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        menuFragment = new MenuFragment();

        mainButton = findViewById(R.id.btMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flSub,mainFragment).commit();
            }
        });

        menuButton = findViewById(R.id.btMenu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flSub,menuFragment).commit();
            }
        });

    }

    public void changeFragment(int index) {
        if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flSub,menuFragment).commit();
        } else if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flSub,mainFragment).commit();
        }
    }
}

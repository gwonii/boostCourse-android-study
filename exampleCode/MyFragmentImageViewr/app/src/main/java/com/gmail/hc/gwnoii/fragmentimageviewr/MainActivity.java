package com.gmail.hc.gwnoii.fragmentimageviewr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        listFragment = (ListFragment) fragmentManager.findFragmentById(R.id.fgListFragment);
        viewerFragment = (ViewerFragment) fragmentManager.findFragmentById(R.id.fgViewerFragment);
    }

    public void onimageChange(int index) {
        viewerFragment.setImage(index);
    }

}

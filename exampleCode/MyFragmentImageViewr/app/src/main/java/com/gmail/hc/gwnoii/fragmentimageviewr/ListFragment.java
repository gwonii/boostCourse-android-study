package com.gmail.hc.gwnoii.fragmentimageviewr;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    private MainActivity mainActivity;

    private Button firstButton;
    private Button secondButon;
    private Button thirdButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list,container,false);

        firstButton = rootView.findViewById(R.id.btFirstImage);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onimageChange(0);
            }
        });

        secondButon = rootView.findViewById(R.id.btSecondImage);
        secondButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onimageChange(1);
            }
        });

        thirdButton = rootView.findViewById(R.id.btThirdImage);
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onimageChange(2);
            }
        });

        return rootView;
    }

}

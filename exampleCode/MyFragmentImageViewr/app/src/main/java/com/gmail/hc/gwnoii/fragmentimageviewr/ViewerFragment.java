package com.gmail.hc.gwnoii.fragmentimageviewr;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewerFragment extends Fragment {

    private MainActivity mainActivity;

    private ImageView imageView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewr,container,false);

        imageView = rootView.findViewById(R.id.ivMain);

        return rootView;
    }

    public void setImage(int index) {
        if (index == 0) {
            imageView.setImageResource(R.drawable.ic_12);
        } else if (index == 1) {
            imageView.setImageResource(R.drawable.ic_15);
        } else if (index == 2) {
            imageView.setImageResource(R.drawable.ic_19);
        }
    }
}

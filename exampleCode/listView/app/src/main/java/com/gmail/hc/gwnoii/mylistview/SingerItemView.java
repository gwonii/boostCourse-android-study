package com.gmail.hc.gwnoii.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    ImageView imageView1;

    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView1 = findViewById(R.id.tv_name);
        textView2 = findViewById(R.id.tv_phoneNum);
        imageView1 = findViewById(R.id.iv_profile);
    }

    public void setName(String string){
        textView1.setText(string);
    }
    public void setPhoneNum(String string){
        textView2.setText(string);
    }

    public void setImage(int imgResource){
        imageView1.setImageResource(imgResource);
    }
}

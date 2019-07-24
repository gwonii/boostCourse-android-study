package com.gmail.hc.gwnoii.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView1);

        SingerAdapter adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("소녀시대", "010-0000-1111", R.drawable.google));
        adapter.addItem(new SingerItem("걸스데이", "010-1111-2222", R.drawable.google));
        adapter.addItem(new SingerItem("다이아", "010-2222-3333", R.drawable.naver));
        adapter.addItem(new SingerItem("원더걸스", "010-3333-4444", R.drawable.google));
        adapter.addItem(new SingerItem("아이오아이", "010-4444-5555", R.drawable.kakao));
        adapter.addItem(new SingerItem("아이오아이", "010-4444-5555", R.drawable.kakao));
        adapter.addItem(new SingerItem("아이오아이", "010-4444-5555", R.drawable.kakao));
        adapter.addItem(new SingerItem("아이오아이", "010-4444-5555", R.drawable.kakao));

        listView.setAdapter(adapter);                           //리스트뷰가 내가 만들어 놓은 adapter를 알게되었음



    }

    class SingerAdapter extends BaseAdapter {

        ArrayList<SingerItem> items = new ArrayList<>();        // ArrayList에 저장해 놓을 데이터 묶음을 만든다.

        public void addItem(SingerItem item){
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view = new SingerItemView(getApplicationContext());

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setPhoneNum(item.getPhoneNum());
            view.setImage(item.getImgResource());

            return view;
        }
    }
}

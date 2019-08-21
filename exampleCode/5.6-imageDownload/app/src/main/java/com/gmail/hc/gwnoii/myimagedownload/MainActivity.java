package com.gmail.hc.gwnoii.myimagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button reqeustButton;
    ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainImage = findViewById(R.id.ivMain);
        reqeustButton = findViewById(R.id.btRequest);

        reqeustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendImageRequest();
            }
        });
    }

    private void sendImageRequest() {
        String url = "https://movie-phinf.pstatic.net/20190702_80/1562029642772pfVnC_JPEG/movie_image.jpg?type=m665_443_2";

        ImageLoadTask task = new ImageLoadTask(url,mainImage);
        task.execute();
    }
}

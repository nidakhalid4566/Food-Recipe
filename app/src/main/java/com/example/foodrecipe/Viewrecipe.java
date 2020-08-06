package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Viewrecipe extends AppCompatActivity {
    TextView t1, t2, t3, t4, t6;
    ImageView i1;
    String name, in, des, ct, link, imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrecipe);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t6 = findViewById(R.id.t6);
        i1 = findViewById(R.id.i1);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        in = i.getStringExtra("in");
        ct = i.getStringExtra("ct");
        des = i.getStringExtra("des");
        link = i.getStringExtra("link");
        imgurl = i.getStringExtra("imgurl");
        //link="https://www.youtube.com/";
        t1.setText(name);
        t2.setText(in);
        t3.setText(des);
        t4.setText(link);
        t6.setText(ct);
        Uri imageUri = Uri.parse(imgurl);
        i1.setImageURI(imageUri);

        t4.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(i);
            }
        });

    }
}
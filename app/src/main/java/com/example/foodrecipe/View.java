package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class View extends AppCompatActivity {
    TextView t1,t2,t3,t4,t6;
    ImageView i1;
    int pos;
    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    String resname,ingerdients,description,ct,link,username,imgurl,pass,nam,category;
    ArrayList<ResClass> arrayList;
    Button next,prev,share;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t6=findViewById(R.id.t6);
        i1=findViewById(R.id.i1);

        Intent i=getIntent();
        username=i.getStringExtra("uname");
        pass=i.getStringExtra("pass");
        category=i.getStringExtra("category");
        pos=i.getIntExtra("position",0);

        next=findViewById(R.id.next);
        prev=findViewById(R.id.prev);
        share=findViewById(R.id.share);

        dbhelper = new DatabaseHelper(this);
        arrayList=new ArrayList<>();

        if(category.equalsIgnoreCase("all")){
            arrayList=dbhelper.getAlldata();
            size=arrayList.size();


        }
        else {
            arrayList=dbhelper.getbycategory(category);
            size=arrayList.size();
        }
        start(pos);
        prev.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(pos!=0){
                    pos=(pos-1)%size;
                    start(pos);

                }


            }
        });

        next.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                pos=(pos+1)%size;
                start(pos);

            }
        });
        share.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String data="RESPIE NAME="+resname+"\n\n"+"INGREDIENTS="+ingerdients+"\n\n"+"DESCRIPTION="+description+"\n\n"+"LINK="+link;
                Intent i=new Intent();
                i.putExtra(Intent.EXTRA_TEXT,data);
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                startActivity(i);
            }
        });


        t4.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(i);
            }
        });

    }
    private void start(int pos) {
        db = dbhelper.getReadableDatabase();
        nam=String.valueOf(arrayList.get(pos).getName());
        Cursor c = db.query(DatabbaseContract.f2.TABLE_NAME, null,DatabbaseContract.f2.COL_RES_NAME+" = ? " , new String[]{(nam)}, null, null, null);

        while (c.moveToNext()) {
            //  name = c.getString(1);

            resname = c.getString(1);
            ingerdients = c.getString(2);
            description = c.getString(3);
            ct = c.getString(5);
            link = c.getString(4);
            imgurl = c.getString(6);

        }
        c.close();
        t1.setText(resname);
        t2.setText(ingerdients);
        t3.setText(description);
        t4.setText(link);
        t6.setText(ct);
        Uri imageUri = Uri.parse(imgurl);
        i1.setImageURI(imageUri);

    }

    public boolean onCreateOptionIsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.feedback:
                feedback();
                return true;
            case R.id.rating:
                rating();
                return true;
            case R.id.logout:
                logut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void feedback(){
        Intent intent=new Intent(this,Feedback.class);
        intent.putExtra("name",username);
        startActivity(intent);

    }
    public void rating(){
        Intent intent=new Intent(this,Rating.class);
        intent.putExtra("name",username);
        startActivity(intent);

    }
    public void logut(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}

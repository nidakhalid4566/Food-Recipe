package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;


public class User extends AppCompatActivity {
    String username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ImageSlider imageSlider=findViewById(R.id.slider);
        Intent intent=getIntent();
        username=intent.getStringExtra("name");
        pass=intent.getStringExtra("pass");
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel((R.drawable.i1),""));
        slideModels.add(new SlideModel((R.drawable.i2),""));
        slideModels.add(new SlideModel((R.drawable.i3),""));
        slideModels.add(new SlideModel((R.drawable.i4),""));
        imageSlider.setImageList(slideModels,true);


    }
    public void chinese(View view){
        String name="Chinese";
        start(name);
    }
    public void desi(View view){
        String name="Desi";
        start(name);
    }
    public void desert(View view){
        String name="Dessert";
        start(name);

    }
    public void all(View view){
        String name="all";
        start(name);

    }
    public void italian(View view){
        String name="italian";
        start(name);

    }
    public void sea(View view){
        String name="Sea food";
        start(name);

    }
    public void juices(View view){
        String name="juices";
        start(name);
    }
    public void fasting(View view){
        String name="fasting food";
        start(name);

    }
    public void start(String name){
        Intent intent=new Intent(getApplicationContext(), com.example.foodrecipe.List.class);
        intent.putExtra("uname",username);
        intent.putExtra("name",name);
        intent.putExtra("pass",pass);


        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
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


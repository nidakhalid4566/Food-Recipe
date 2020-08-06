package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.b1);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fad);
        b1.startAnimation(animation);


    }

    public void enter(android.view.View view) {
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}

package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tmovement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tmovement=findViewById(R.id.tmovement);
        tmovement.setSelected(true);
    }
    public void admin(View view){
        Intent intent=new Intent(this,Adminlogin.class);
        startActivity(intent);

    }
    public void user(View view){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);

    }
}

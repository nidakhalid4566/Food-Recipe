package com.example.foodrecipe;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    TextView t1;
    EditText e1,e2,e3;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        t1=findViewById(R.id.t1);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        dbHelper = new DatabaseHelper(this);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fad);
        t1.startAnimation(animation);


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }
    public void start(){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
    public void Register(View view) {
        db = dbHelper.getWritableDatabase();
        String val1 = e1.getText().toString();
        String val2 = e2.getText().toString();
        String val3 = e3.getText().toString();
        ContentValues values = new ContentValues();
        if ((TextUtils.isEmpty(e1.getText())) && (TextUtils.isEmpty(e2.getText())) && (TextUtils.isEmpty(e3.getText()))) {
            e1.setError("Please fill the required field");
            e2.setError("Please fill the required field");
            e3.setError("Please fill the required field");


        } else if ((TextUtils.isEmpty(e1.getText()))) {
            e1.setError("Please fill the required field");
        } else if (TextUtils.isEmpty(e2.getText())) {
            e2.setError("Please fill the required field");
        } else if (TextUtils.isEmpty(e3.getText())) {
            e2.setError("Please fill the required field");
        } else {
            if (val2.equalsIgnoreCase(val3)) {

                values.put(DatabbaseContract.f21.COL_NAME, val1);
                values.put(DatabbaseContract.f21.COL_Pass, val2);
            } else {
                e2.setError("password not same");
                e3.setError("password not same");
                Toast.makeText(this, "Password not same ", Toast.LENGTH_SHORT).show();

            }
            long newRowId = db.insert(DatabbaseContract.f21.TABLE_NAME, null, values);
            if (newRowId > 0) {
                Toast.makeText(this, "Register Succefully ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Login.class);
                startActivity(i);
            } else
                Toast.makeText(this, "not register ", Toast.LENGTH_SHORT).show();

            db.close();

        }
    }
}


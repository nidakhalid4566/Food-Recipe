package com.example.foodrecipe;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView t1;
    EditText e1,e2;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String Cname,pass;
    Integer count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=findViewById(R.id.t1);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
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
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }
    public void login(View view){
        db = dbHelper.getReadableDatabase();
        String val1 = e1.getText().toString();
        String val2 = e2.getText().toString();
        Cursor c = db.query(DatabbaseContract.f21.TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            Cname = c.getString(1);
            pass= c.getString(2);
            if (Cname.equalsIgnoreCase(val1)) {
                if (pass.equalsIgnoreCase(val2)) {
                    Toast.makeText(this, "Login Successfull " , Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,User.class);
                    intent.putExtra("name", Cname);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    count++;
                    break;

                }
            }
        }
        if ((TextUtils.isEmpty(e1.getText())) && (TextUtils.isEmpty(e2.getText()))) {
            e1.setError("Please fill the required field");
            e2.setError("Please fill the required field");


        } else if ((TextUtils.isEmpty(e1.getText()))) {
            e1.setError("Please fill the required field");
        } else if (TextUtils.isEmpty(e2.getText())) {
            e2.setError("Please fill the required field");
        }
        if(count==0){
            Toast.makeText(this, "Invalid username and password " , Toast.LENGTH_SHORT).show();
        }
        c.close();
    }
}
package com.example.foodrecipe;



import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
    EditText e1;
    String name;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Integer c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        e1=findViewById(R.id.e1);
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        dbHelper = new DatabaseHelper(this);

    }
    public void feedback(View view){
        db = dbHelper.getWritableDatabase();
        String val1 = e1.getText().toString();
        ContentValues args = new ContentValues();
        args.put(DatabbaseContract.f21.COL_FeedBack,val1);
        String[] wherearg={name};
        Integer count= db.update(DatabbaseContract.f21.TABLE_NAME, args, DatabbaseContract.f21.COL_NAME + "=?",wherearg);
        if (count > 0) {
            Toast.makeText(this, name+" **Thanks for feedback**  " , Toast.LENGTH_SHORT).show();
            c++;
        }
        if(c==0){
            Toast.makeText(this, " ****  " , Toast.LENGTH_SHORT).show();
        }
        db.close();
        e1.setText("");
        Intent intent=new Intent(Feedback.this,User.class);
        startActivity(intent);
    }
}

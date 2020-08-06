package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Rating extends AppCompatActivity {
    TextView t1;
    RatingBar rating;
    Button b1;
    String name;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Integer c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        t1=findViewById(R.id.t1);
        b1=findViewById(R.id.b1);
        rating=findViewById(R.id.rating);
        Intent intent = getIntent();
        name=intent.getStringExtra("name");
        dbHelper = new DatabaseHelper(this);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                t1.setText(""+rating);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getWritableDatabase();
                String val1 = t1.getText().toString();
                ContentValues args = new ContentValues();
                args.put(DatabbaseContract.f21.COL_Rating,val1);
                String[] wherearg={name};
                Integer count= db.update(DatabbaseContract.f21.TABLE_NAME, args, DatabbaseContract.f21.COL_NAME + "=?",wherearg);
                if (count > 0) {
                    Toast.makeText(Rating.this, name+" **Thanks for rating**  " , Toast.LENGTH_SHORT).show();
                    c++;
                }
                if(c==0){
                    Toast.makeText(Rating.this, " ****  " , Toast.LENGTH_SHORT).show();
                }
                db.close();
                t1.setText("");
                Intent intent=new Intent(Rating.this,User.class);
                startActivity(intent);
            }
        });
    }
}

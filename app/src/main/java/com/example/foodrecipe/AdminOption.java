package com.example.foodrecipe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AdminOption extends AppCompatActivity {
    SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_option);

        dbHelper = new DatabaseHelper(this);





    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
               logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void feedback(android.view.View view) {
        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DatabbaseContract.f21.TABLE_NAME, null, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext()) {
            String Cname = c.getString(1);
            String feedback = c.getString(3);
            buffer.append("Name : " + Cname + "\n");
            buffer.append("Feedback : " + feedback + "\n\n\n");

        }
        showMessage("Data",buffer.toString());
    }
    public void rating(View view) {
        db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DatabbaseContract.f21.TABLE_NAME, null, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while (c.moveToNext()) {
            String Cname = c.getString(1);
            String rating = c.getString(4);
            buffer.append("Name : " + Cname + "\n");
            buffer.append("Rating : " + rating+" STARS"+"\n\n\n");

        }
        showMessage("Data",buffer.toString());
    }
    public void  seelist(View view){
        Intent intent=new Intent(this,Seelist.class);
        startActivity(intent);
    }
    public void  delete(View view){
        Intent intent=new Intent(this,Deleterecipe.class);
        startActivity(intent);
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void addrecpie(View view){
        Intent i=new Intent(this,Admin.class);
        startActivity(i);

    }
    public void logout(){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }



}
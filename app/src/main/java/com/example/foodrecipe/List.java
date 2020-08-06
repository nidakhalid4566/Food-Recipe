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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    String category,name,username,pass;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    int size;
    SearchView search;
    ArrayList<ResClass> arrayList=new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent=getIntent();
        category=intent.getStringExtra("name");
        username=intent.getStringExtra("uname");
        pass=intent.getStringExtra("pass");
        dbHelper = new DatabaseHelper(this);
        search=findViewById(R.id.search);
        final ListView lv=(ListView) findViewById(R.id.list);
        db = dbHelper.getReadableDatabase();


        if(category.equalsIgnoreCase("all")){
                arrayList=dbHelper.getAlldata();
                myAdapter=new MyAdapter(this,arrayList);
                lv.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();


        }
        else {
            arrayList=dbHelper.getbycategory(category);
            myAdapter=new MyAdapter(this,arrayList);
            lv.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
                }
        size=arrayList.size();


        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, usersList);
        //lv.setAdapter(adapter);
       search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ResClass> result=new ArrayList<>();
                for (ResClass x: arrayList)
                {
                    if(x.name.contains(newText)){
                        result.add(x);
                    }
                }
                ((MyAdapter)lv.getAdapter()).update(result);
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(List.this, com.example.foodrecipe.View.class);
                i.putExtra("uname", username);
                i.putExtra("pass", pass);
                i.putExtra("category",category);
                i.putExtra("position",position);
                startActivity(i);


            }
        });




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
            case R.id.about:
                about();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void feedback(){
        Intent intent=new Intent(this,Feedback.class);
        intent.putExtra("uname",username);
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
    public void about(){
        opendialog();
    }

    private void opendialog() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("There are "+size+" recipies available in this menu of "+category+" category");
        showMessage("Data",buffer.toString());
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

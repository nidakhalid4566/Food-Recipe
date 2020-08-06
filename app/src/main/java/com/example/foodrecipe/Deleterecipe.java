package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.foodrecipe.DatabbaseContract.f2.TABLE_NAME;

public class Deleterecipe extends AppCompatActivity {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    int count=0;
    EditText e1;
    Button b1;
    ArrayList<ResClass> arrayList=new ArrayList<>();
    MyAdapter myAdapter;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleterecipe);
        e1=findViewById(R.id.e1);
        b1=findViewById(R.id.b1);


        dbHelper = new DatabaseHelper(this);
       //search = findViewById(R.id.search);
        db = dbHelper.getReadableDatabase();
        final java.util.List<String> usersList = new ArrayList();
        lv = (ListView) findViewById(R.id.l1);
        Cursor c = db.query(DatabbaseContract.f2.TABLE_NAME, null, null, null, null, null, null);
        load();




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<arrayList.size();i++){
                    String getname=e1.getText().toString();
                    if(String.valueOf(arrayList.get(i).getName()).equalsIgnoreCase(getname)){
                        db = dbHelper.getWritableDatabase();
                        Integer i1= db.delete(TABLE_NAME, "Name= ?",new String[] {getname});
                        if (i1 > 0) {
                            Toast.makeText(Deleterecipe.this, "Record deleted in data base", Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                        arrayList.remove(i);
                        myAdapter.notifyDataSetChanged();
                        break;
                    }
                    else {
                        Toast.makeText(Deleterecipe.this, "no item matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nam=String.valueOf(arrayList.get(position).getName());
                //String n = parent.getItemAtPosition(position).toString();
                e1.setText(nam);

            }
        });

    }

    private void load() {
        arrayList=dbHelper.getAlldata();
        myAdapter=new MyAdapter(this,arrayList);
        lv.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}


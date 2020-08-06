package com.example.foodrecipe;

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

public class Seelist extends AppCompatActivity {
    String  name, resname, ingerdients, description, ct, link, imgurl;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    String cn;
    SearchView search;
    ArrayList<ResClass> arrayList=new ArrayList<>();
    MyAdapter myAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seelist);


        dbHelper = new DatabaseHelper(this);
        search = findViewById(R.id.search);
        db = dbHelper.getReadableDatabase();
        lv = (ListView) findViewById(R.id.list);
        java.util.List<String> usersList = new ArrayList();

        load();


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
                String nam=String.valueOf(arrayList.get(position).getName());
                Toast.makeText(Seelist.this,"you selected"+ nam, Toast.LENGTH_SHORT).show();
                Cursor c = db.query(DatabbaseContract.f2.TABLE_NAME, null,DatabbaseContract.f2.COL_RES_NAME+" = ? " , new String[]{(nam)}, null, null, null);
                while (c.moveToNext()) {
                    resname=c.getString(1);
                    ingerdients= c.getString(2);
                    description= c.getString(3);
                    ct= c.getString(5);
                    link= c.getString(4) ;
                    imgurl=c.getString(6);
                }
                Intent i = new Intent(Seelist.this,Viewrecipe.class);
                i.putExtra("name", resname);
                i.putExtra("in", ingerdients);
                i.putExtra("des", description);
                i.putExtra("ct", ct);
                i.putExtra("link", link);
                i.putExtra("imgurl", imgurl);

                startActivity(i);
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
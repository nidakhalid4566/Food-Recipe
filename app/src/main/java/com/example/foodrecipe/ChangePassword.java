package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.foodrecipe.DatabbaseContract.f2.TABLE_NAME;

public class ChangePassword extends AppCompatActivity {
    String pass,uname;
    EditText e1, e2, e3;
    int count = 0;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        b1=findViewById(R.id.b1);


        Intent intent=getIntent();
        pass=intent.getStringExtra("pass");
        uname=intent.getStringExtra("uname");
        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1=findViewById(R.id.e1);
                e2=findViewById(R.id.e2);
                e3=findViewById(R.id.e3);
                String oldpass=e1.getText().toString();
                String newpass=e2.getText().toString();
                String renewpass=e3.getText().toString();

                if ((TextUtils.isEmpty(e1.getText())) && (TextUtils.isEmpty(e2.getText())) && (TextUtils.isEmpty(e3.getText()))) {
                    e1.setError("Please fill the required field");
                    e2.setError("Please fill the required field");
                    e3.setError("Please fill the required field");


                } else if ((TextUtils.isEmpty(e1.getText()))) {
                    e1.setError("Please fill the required field");
                } else if (TextUtils.isEmpty(e2.getText())) {
                    e2.setError("Please fill the required field");
                }
                else if (TextUtils.isEmpty(e3.getText())) {
                    e3.setError("Please fill the required field");
                }

                if(newpass.equalsIgnoreCase(renewpass)){
                    if(pass.equalsIgnoreCase(oldpass)){
                        Toast.makeText(ChangePassword.this, uname+newpass, Toast.LENGTH_SHORT).show();
                        ContentValues args = new ContentValues();
                        args.put(DatabbaseContract.f21.COL_Pass,newpass);
                        //String[] wherearg={uname};
                        Integer count= db.update(TABLE_NAME, args, DatabbaseContract.f21.COL_NAME + " = ? ",new String[]{uname});
                        if (count > 0) {
                            Toast.makeText(ChangePassword.this, "  Records updated: " , Toast.LENGTH_SHORT).show();
                        }
                        db.close();


                    }
                    else{
                        e1.setError("Please enter correct old password");
                        Toast.makeText(ChangePassword.this, " plz enter correct old password " , Toast.LENGTH_SHORT).show();
                    }
                }
                else {

                    e2.setError("password are not same");
                    e3.setError("password are not same");
                    Toast.makeText(ChangePassword.this, " password are not same " , Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}

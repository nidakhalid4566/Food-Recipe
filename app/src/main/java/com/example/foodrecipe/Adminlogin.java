package com.example.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {
    EditText e1,e2;
    String name,pass;
    Integer count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);

    }
    public void Adminlogin(android.view.View view) {
        name=e1.getText().toString();
        pass=e2.getText().toString();
        if ((TextUtils.isEmpty(e1.getText())) && (TextUtils.isEmpty(e2.getText()))) {
            e1.setError("Please fill the required field");
            e2.setError("Please fill the required field");


        } else if ((TextUtils.isEmpty(e1.getText()))) {
            e1.setError("Please fill the required field");
        } else if (TextUtils.isEmpty(e2.getText())) {
            e2.setError("Please fill the required field");
        }
        if(name.equalsIgnoreCase("NidaKhalid")){
            if(pass.equalsIgnoreCase("1234")){
                count++;
            }
        }
        if(count==0){
            Toast.makeText(getApplicationContext(), "Incorrect username or password",
                    Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getApplicationContext(), "Signin successful",
                    Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,AdminOption.class);
            startActivity(i);
        }
    }
}

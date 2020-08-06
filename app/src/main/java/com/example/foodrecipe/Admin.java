package com.example.foodrecipe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.foodrecipe.DatabbaseContract.f2.TABLE_NAME;

public class Admin extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText e1, e2,e3,e4;
    TextView e5;
    RadioGroup radioGroup;
    RadioButton radioButton;
    SQLiteDatabase db;
    ImageView btn_image;
    String picturePath,cat;
    Button b1;
    private static int RESULT_LOAD_IMG = 1;
    public final int REQUEST_CODE_FOR_PERMISSIONS = 654;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        dbHelper = new DatabaseHelper(this);
        btn_image= findViewById(R.id.pick_image);
        radioGroup=findViewById(R.id.radio);
        e5 = (TextView)findViewById(R.id.e5);
        b1=findViewById(R.id.b1);
        b1.setEnabled(false);

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMG);


            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOR_PERMISSIONS);
            }
        }
    }
    public void check(View view){
        int radioid=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioid);

        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
       e5.setText(radioButton.getText().toString());
       b1.setEnabled(true);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMG){
            Uri selectedImage = data.getData();

            picturePath = selectedImage.toString();
            btn_image.setImageURI(selectedImage);

            Log.i("uri.", ""+selectedImage);
            //getImage(bm);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_FOR_PERMISSIONS){
            //You need to handle permission results, if user didn't allow them.
        }
    }
    public void add(View view){
        db = dbHelper.getWritableDatabase();

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);






        String val1 = e1.getText().toString();
        String val2 = e2.getText().toString();
        String val3 = e3.getText().toString();
        String val4 = e4.getText().toString();
        String val5 = e5.getText().toString();

        ContentValues values = new ContentValues();
        values.put(DatabbaseContract.f2.COL_RES_NAME, val1);
        values.put(DatabbaseContract.f2.COL_INGREDIENTS, val2);
        values.put(DatabbaseContract.f2.COL_DESCRIPTION, val3);
        values.put(DatabbaseContract.f2.COL_LINK, val4);
        values.put(DatabbaseContract.f2.COL_CATEGORY, val5);
        values.put(DatabbaseContract.f2.Col_IMG, picturePath);



        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId > 0) {
            Toast.makeText(this, "New Record Inserted: " , Toast.LENGTH_SHORT).show();
            remove();
        }
        else
            Toast.makeText(this, "not Inserted: " + newRowId, Toast.LENGTH_SHORT).show();

        db.close();

    }
    public void remove(){
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        btn_image.setImageResource(R.drawable.imagee);
        int radioid=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioid);
        radioButton.setChecked(false);
    }


}

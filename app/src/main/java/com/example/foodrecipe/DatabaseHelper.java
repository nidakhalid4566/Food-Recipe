package com.example.foodrecipe;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "frecp.db";
    private static final String CREATE_TBL_Country = "CREATE TABLE "
            + DatabbaseContract.f2.TABLE_NAME + " ("
            + DatabbaseContract.f2._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabbaseContract.f2.COL_RES_NAME + " TEXT , "
            + DatabbaseContract.f2.COL_INGREDIENTS + " TEXT NOT NULL,"
            + DatabbaseContract.f2.COL_DESCRIPTION + " TEXT NOT NULL,"
            + DatabbaseContract.f2.COL_LINK + " TEXT NOT NULL,"
            + DatabbaseContract.f2.COL_CATEGORY + " TEXT NOT NULL,"
            + DatabbaseContract.f2.Col_IMG+ "  TEXT )";
    private static final String CREATE_TBL = "CREATE TABLE "
            + DatabbaseContract.f21.TABLE_NAME + " ("
            + DatabbaseContract.f21._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabbaseContract.f21.COL_NAME + " TEXT UNIQUE, "
            + DatabbaseContract.f21.COL_Pass + " TEXT NOT NULL,"
            + DatabbaseContract.f21.COL_FeedBack + " TEXT,"
            + DatabbaseContract.f21.COL_Rating + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_Country);
        db.execSQL(CREATE_TBL);

        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
    public ArrayList<ResClass> getAlldata(){
        ArrayList<ResClass> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT  * FROM Res",null);
        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            int id=cursor.getInt(0);
            String ingredients=cursor.getString(2);
            String description=cursor.getString(3);
            String link=cursor.getString(4);
            String category=cursor.getString(5);
            String img=cursor.getString(6);
            ResClass r = new ResClass( id,  name,  ingredients, description,  img,  link,category);
            arrayList.add(r);

        }
        return arrayList;
    }
    public ArrayList<ResClass> getbycategory(String c){
        ArrayList<ResClass> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Res WHERE category = '"+c+"'",null);
        while (cursor.moveToNext()){
            String name=cursor.getString(1);
            int id=cursor.getInt(0);
            String ingredients=cursor.getString(2);
            String description=cursor.getString(3);
            String link=cursor.getString(4);
            String category=cursor.getString(5);
            String img=cursor.getString(6);
            ResClass r = new ResClass( id,  name,  ingredients, description,  img,  link,category);
            arrayList.add(r);

        }
        return arrayList;
    }
}


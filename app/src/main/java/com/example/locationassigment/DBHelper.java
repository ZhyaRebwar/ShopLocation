package com.example.locationassigment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "resortDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table resorts " +
                "(id integer primary key, " +
                "name text, " +
                "phone text, " +
                "latitude text, " +
                "longitude text, " +
                "description text );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists resorts");
        onCreate(db);
    }

    public boolean insertValues(String name, String phone, String latitude, String longitude, String description){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues inVals = new ContentValues();
        inVals.put("name", name);
        inVals.put("phone", phone);
        inVals.put("latitude", latitude);
        inVals.put("longitude", longitude);
        inVals.put("description", description);

        db.insert("resorts", null, inVals);

        return true;
    }

    public int getData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor pointer = db.rawQuery("select * from resorts where name ="+name + "" ,null);
        int id = pointer.getColumnIndexOrThrow( "id" );

        return id;
    }

    public int numOfRow(){
        SQLiteDatabase db = this.getReadableDatabase();
        int noRows = (int) DatabaseUtils.queryNumEntries(db, "resorts");

        return noRows;
    }

    public void updateRecord(int id, String name, String phone, String latitude, String longitude, String description){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues upVals = new ContentValues();
        upVals.put("name", name);
        upVals.put("phone", phone);
        upVals.put("latitude", latitude);
        upVals.put("longitude", longitude);
        upVals.put("description", description);
        db.update("resorts", upVals, "id = ?", new String[]{ Integer.toString(id) } );
    }

    public Integer deleteRecord(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("resorts", "name=?",new String[]{ (name) } );
    }

    public ArrayList<String> getAllRecordsName(){
        ArrayList<String> records = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor pointer = db.rawQuery(" select * from resorts" +
                "", null);
        pointer.moveToFirst();

        while(pointer.isAfterLast() == false ){
            records.add( pointer.getString(pointer.getColumnIndexOrThrow("name" ) ) );
            pointer.moveToNext();
        }
        return records;
    }


    public ArrayList<ArrayList<String>> getRecords(){
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> phone = new ArrayList<>();
        ArrayList<String> latitude = new ArrayList<>();
        ArrayList<String> longitude = new ArrayList<>();
        ArrayList<String> description = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor pointer = db.rawQuery(" select * from resorts", null);
        pointer.moveToFirst();

        while( pointer.isAfterLast() == false ){
            name.add( pointer.getString(pointer.getColumnIndexOrThrow("name" ) ) );
            phone.add( pointer.getString(pointer.getColumnIndexOrThrow("phone" ) ) );
            latitude.add( pointer.getString(pointer.getColumnIndexOrThrow("latitude" ) ) );
            longitude.add( pointer.getString(pointer.getColumnIndexOrThrow("longitude" ) ) );
            description.add( pointer.getString(pointer.getColumnIndexOrThrow("description" ) ) );

            pointer.moveToNext();
        }
        //two dimensional array to hold both records
        ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
        records.add(name);
        records.add(phone);
        records.add(latitude);
        records.add(longitude);
        records.add(description);

        return records;
    }
}
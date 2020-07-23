package com.example.begard;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final int Version = 1;
    private static final String DatabaseName = "Begard.db";
    private static final String TableName = "User";

    private static final String TableName2 = "Data";
    private static final String UserID = "UserID";
    private static final String Username = "fullname";
    private static final String email = "email";
    private static final String password = "Password";
    private static final String number = "Number";
    private static final String PlaceID = "ID";
    private static final String Placename = "Name";
    private static final String  Address = "Address";
    private static final String  Price = "Price";
    private static final String  About = "About";


    public DatabaseManager (@Nullable Context context) {
        super(context, DatabaseName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " (" + UserID + " VARCHAR PRIMARY KEY ASC UNIQUE NOT NULL, "+
                Username +" VARCHAR, "+ email +" VARCHAR UNIQUE, "+ password +" VARCHAR, " + number+" VARCHAR);");
        db.execSQL("CREATE TABLE " + TableName2 + "(" + PlaceID + " INTEGER PRIMARY KEY ASC AUTOINCREMENT UNIQUE, " +
                Placename + " VARCHAR," + Address + "TEXT," + Price + "INTEGER," + About + "TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void  insertData (Data ida){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(PlaceID,ida.PlaceID);
        icv.put(Address,ida.Address);
        icv.put(Placename,ida.Placename);
        idb.insert(TableName2,null,icv);
        idb.close();
    }
    public void insertUser (User ius){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(UserID,ius.UserID);
        icv.put(Username,ius.fullName);
        icv.put(email,ius.email);
        icv.put(password,ius.password);
        icv.put(number,ius.number);
        idb.insert(TableName,null,icv);
        idb.close();
    }
    }

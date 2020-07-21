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
    private static final String UserID = "UserID";
    private static final String name = "fullname";
    private static final String email = "email";
    private static final String password = "Password";
    private static final String number = "Number";

    public DatabaseManager (@Nullable Context context) {
        super(context, DatabaseName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "CREATE TABLE " + TableName + " (" + UserID + " VARCHAR PRIMARY KEY ASC UNIQUE NOT NULL, "+
                name +" VARCHAR, "+ email +" VARCHAR UNIQUE, "+ password +" VARCHAR, " + number+" VARCHAR);";
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertUser (User ius){
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(UserID,ius.UserID);
        icv.put(name,ius.fullName);
        icv.put(email,ius.email);
        icv.put(password,ius.password);
        icv.put(number,ius.number);
        idb.insert(TableName,null,icv);
        idb.close();
    }
    }

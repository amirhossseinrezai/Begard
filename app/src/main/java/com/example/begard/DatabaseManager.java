package com.example.begard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final int Version = 1;
    private static final String DatabaseName = "Begard.db";
    private static final String TableName = "User";
    private static final String TableName2 = "Data";
    private static final String UserID = "UserID";
    private static final String fullname = "fullname";
    private static final String email = "email";
    private static final String password = "Password";
    private static final String number = "Number";
    private static final String PlaceID = "ID";
    private static final String Placename = "PlaceName";
    private static final String Address = "Address";
    private static final String Price = "Price";
    private static final String About = "About";
    private static final String Image = "Image";


    public DatabaseManager(@Nullable Context context) {
        super(context, DatabaseName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TableName + " (" + UserID + " VARCHAR PRIMARY KEY UNIQUE NOT NULL, " +
                fullname + " VARCHAR, " + email + " VARCHAR UNIQUE, " + password + " VARCHAR, " + number + " VARCHAR);");
        db.execSQL("CREATE TABLE " + TableName2 + "(" + PlaceID + " INTEGER PRIMARY KEY ASC AUTOINCREMENT UNIQUE NOT NULL, " +
                Placename + " VARCHAR," + Address + "TEXT," + Price + "VARCHAR," + About + "TEXT" + Image + "INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertData (Data ida) {
        SQLiteDatabase idb2 = this.getWritableDatabase();
        ContentValues icv2 = new ContentValues();
        icv2.put(PlaceID, ida.PlaceID);
        icv2.put(Address, ida.Address);
        icv2.put(Placename, ida.Placename);
        idb2.insert(TableName2, null, icv2);
        idb2.close();
    }

    public void insertUser(User ius) {
        SQLiteDatabase idb = this.getWritableDatabase();
        ContentValues icv = new ContentValues();
        icv.put(UserID, ius.UserID);
        icv.put(fullname, ius.fullName);
        icv.put(email, ius.email);
        icv.put(password, ius.password);
        icv.put(number, ius.number);
        idb.insert(TableName, null, icv);
        idb.close();
    }

    public User getUser(String gID) {

        User guser = new User(UserID,fullname,email,number,password);
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TableName + " WHERE " + UserID + "=" + gID;
        Cursor gCur = gdb.rawQuery(gQuery, null);
        if (gCur.moveToFirst()) {
            guser.fullName = gCur.getString(1);
            guser.number = gCur.getString(2);
            guser.email = gCur.getString(3);
        }
        return guser;
    }

    public Data getData (String gID) {

        Data gData = new Data (PlaceID,Placename,Price,Address,About);
        SQLiteDatabase gdb = this.getReadableDatabase();
        String gQuery = "SELECT * FROM " + TableName2 + " WHERE " + PlaceID + "=" + gID;
        Cursor gCur = gdb.rawQuery(gQuery, null);
        if (gCur.moveToFirst()) {
            gData.Placename = gCur.getString(1);
            gData.Price = gCur.getString(2);
           // gData. = gCur.getString(3);
        }
        return gData;
    }

    public void updateUser(User uUser) {

        SQLiteDatabase udb = this.getWritableDatabase();
        ContentValues ucv = new ContentValues();
        ucv.put(fullname, uUser.fullName);
        ucv.put(email, uUser.email);
        ucv.put(password, uUser.password);
        ucv.put(number, uUser.number);
        udb.update(TableName, ucv, UserID + " = ?", new String[]{String.valueOf(uUser.UserID)});
    }
    public boolean deletePerson(User dUser) {
        SQLiteDatabase ddb = this.getWritableDatabase();
        long dResult = ddb.delete(TableName, UserID + "=?", new String[] {String.valueOf(dUser.UserID)});

        if (dResult == 0)
            return false;
        else
            return true;

    }
}
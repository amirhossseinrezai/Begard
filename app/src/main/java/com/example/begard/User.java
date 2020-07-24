package com.example.begard;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {
    public String UserID;
    public String fullName;
    public String email;
    public String password;
    public String number;

    public User(String UserID, String fullName, String email, String number, String password) {
        this.email = email;
        this.number = number;
        this.UserID = UserID;
        this.password = password;
        this.fullName = fullName;
    }
}




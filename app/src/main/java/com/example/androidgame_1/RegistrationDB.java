package com.example.androidgame_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegistrationDB extends SQLiteOpenHelper {
    RegistrationDB(Context context)
    {
        super(context,"Registration.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Registered(name Text,email Text,dob Text,username Text primary key,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertindb(String name,String email,String dob,String username,String pw)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("dob",dob);
        values.put("username",username);
        values.put("password",pw);
        long result=db.insert("Registered",null,values);
        if(result==-1)
        {
            return false;
        }
        return  true;
    }
}

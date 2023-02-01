package com.example.cerocolas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String ury1="create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(ury1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register (String username, String email, String password){
        System.out.println("Here"+"  "+username+", "+email+", "+password);
        ContentValues cv =new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db= getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }
    public int loginIn(String username, String password){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where username =? and password=?", str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;

    }
}

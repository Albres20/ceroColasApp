package com.example.cerocolas;

import android.content.ContentValues;
import android.content.Context;
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
        ContentValues cv =new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db= getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }
}

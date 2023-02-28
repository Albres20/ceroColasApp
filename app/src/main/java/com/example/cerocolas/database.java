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
        String ury1="create table users(id INTEGER PRIMARY KEY AUTOINCREMENT, username text, email text, password text)";
        String ury2="create table citasPacientes(idCitasPacientes INTEGER PRIMARY KEY AUTOINCREMENT , paciente INTEGER, nameMedico text, direccion text," +
                "celular INTEGER, tarifa REAL , fecha text, hora text, FOREIGN KEY(paciente) REFERENCES users(id))";
        sqLiteDatabase.execSQL(ury1);
        sqLiteDatabase.execSQL(ury2);
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
    public void registerCita (int fkPaciente, String nameMedico, String direccion, int celular, String tarifa, String fecha, String hora){
        System.out.println("Here"+"  "+fkPaciente+", "+nameMedico+", "+direccion);
        ContentValues cv =new ContentValues();


        cv.put("paciente", 1);
        cv.put("nameMedico", nameMedico);
        cv.put("direccion", direccion);
        cv.put("celular", celular);
        cv.put("tarifa", tarifa);
        cv.put("fecha", fecha);
        cv.put("hora", hora);
        SQLiteDatabase db= getWritableDatabase();
        db.insert("citasPacientes", null, cv);
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

    public int returnIdUser(String alias){
        String[] argumentos = { alias };
        String consulta = "SELECT id FROM users WHERE username = ?";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, argumentos);
        int id = 0;
        if (cursor != null && cursor.moveToFirst()) {

            int columnIndex=cursor.getColumnIndex("id");
            id = cursor.getInt(columnIndex);
            return id;
            // hacer algo con la ID obtenida
        } else {
            // no se encontró una fila con el correo electrónico proporcionado
            return 0;
        }

    }
}

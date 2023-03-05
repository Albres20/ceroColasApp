package com.example.cerocolas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tablas relacionadas
        String ury1="create table users(id INTEGER PRIMARY KEY AUTOINCREMENT, username text, email text, password text)";
        String ury2="create table citasPacientes(idCitasPacientes INTEGER PRIMARY KEY AUTOINCREMENT , paciente INTEGER, nameMedico text, direccion text," +
                "celular INTEGER, tarifa REAL , fecha text, hora text, FOREIGN KEY(paciente) REFERENCES users(id))";
        String ury3="create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text )";
        sqLiteDatabase.execSQL(ury1);
        sqLiteDatabase.execSQL(ury2);
        sqLiteDatabase.execSQL(ury3);

        //tabla cart
        String qry2="create table cart(username text, product text, price float, otype text)";

        sqLiteDatabase.execSQL(qry2);

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
    public void addCart(String username, String product, float price, String otype){
        ContentValues cv=new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db= getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();

    }

    public int checkCart(String username, String product){
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from cart where username=? and product=?", str);

        if(c.moveToFirst()){
            result=1;

        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype){
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }
    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        String str[] = new String[2];
        str[0]=username;
        str[1]=otype;
        Cursor c=db.rawQuery("select * from cart where username=? and otype=?", str);

        if(c.moveToFirst()){
            do{
                String product=c.getString(1);
                String price=c.getString(2);
                arr.add(product+"S/."+price);
            }
            while (c.moveToNext());

        }
        db.close();

        return arr;
    }
    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float  price, String otype){
        ContentValues cv=new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);

        SQLiteDatabase db=getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();

    }
    public ArrayList getOrderData(String username){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[1];
        str[0]=username;
        Cursor c=db.rawQuery("select * from orderplace where username=?", str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }



}

package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE); //obtiene un objeto SharedPreferences llamado "shared_prefs". Este objeto permite a la aplicación guardar y recuperar información de configuración que persiste a través de sesiones.
        String username=sharedPreferences.getString("username", "").toString(); //obtiene un valor de la clave "username" almacenada en sharedPreferences y lo almacena en una variable llamada username. Si no se encuentra un valor para la clave "username", se devuelve una cadena vacía.
        Toast.makeText(getApplicationContext(), "Bienvenido"+username, Toast.LENGTH_SHORT).show(); //muestra un mensaje "Bienvenido" seguido del nombre de usuario en una notificación Toast durante un corto período de tiempo.

        //enviar a la ventana siguiente
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
        System.out.println("--->"+username+"hOME<---");

        //Salir hacia el login
        CardView salir=findViewById(R.id.cardSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();//borrar sesión
                editor.apply(); //aplicar cambios
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        //Hallar doctor
        CardView hallarDoctor=findViewById(R.id.cardHallarDoctor);
        hallarDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedPreferences.edit();

                //enviar a la ventana siguiente

                editor.putString("username", username);
                System.out.println("--->"+username+"hOME<---");
                //editor.clear();//borrar sesión
                editor.apply(); //aplicar cambios
                startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));
            }
        });

        //Prueba Laboratorio
        CardView cardLabTest=findViewById(R.id.cardLabTest);
        cardLabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, LabTestActivity.class));
            }
        });
        //cardorderdetails
        CardView orderDetails=findViewById(R.id.cardByMDtent);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, OrdersDetailsActivity.class));
            }
        });
        //comprarMedicinas
        CardView comprarMedicinas=findViewById(R.id.cardBASe);
        comprarMedicinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });

        //cardByMeDine
        CardView cardByMeDine=findViewById(R.id.cardByMeDine);
        cardByMeDine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));
            }
        });

    }
}
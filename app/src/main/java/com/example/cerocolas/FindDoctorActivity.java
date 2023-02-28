package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_find_doctor);
        CardView atras=findViewById(R.id.cardExit);

        //recibir
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE); //obtiene un objeto SharedPreferences llamado "shared_prefs". Este objeto permite a la aplicación guardar y recuperar información de configuración que persiste a través de sesiones.
        String username=sharedPreferences.getString("username", "").toString(); //obtiene un valor de la clave "username" almacenada en sharedPreferences y lo almacena en una variable llamada username. Si no se encuentra un valor para la clave "username", se devuelve una cadena vacía.
        //enviar

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();

        System.out.println("--->"+username+"findDoctor<---");
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView medicoFamilia=findViewById(R.id.cardMedicoFamilia);
        medicoFamilia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Médico de familia"); //Envia a otra actividad,la primera es una clave, en este caso "title" La segunda es el valor de la clave, en este caso "Doctor Familia".
                startActivity(it);

            }
        });
        CardView dietas=findViewById(R.id.cardFDDietician);
        dietas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Experto en Dietas");
                startActivity(it);
            }
        });
        CardView dentista=findViewById(R.id.cardDentist);
        dentista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Dentista");
                startActivity(it);            }
        });
        CardView cirujano=findViewById(R.id.cardSurgeon);
        cirujano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Cirujano");
                startActivity(it);             }
        });
        CardView cardiologo=findViewById(R.id.cardCardiologists);
        cardiologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Cardiólogo");
                startActivity(it);             }
        });
    }
}
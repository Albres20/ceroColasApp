package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
                it.putExtra("title", "Doctor Familia"); //Envia a otra actividad,la primera es una clave, en este caso "title" La segunda es el valor de la clave, en este caso "Doctor Familia".
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
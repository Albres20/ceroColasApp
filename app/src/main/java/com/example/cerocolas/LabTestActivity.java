package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packages=
    {
        {"Paquete 1: Chequeo completo", "", "", "", "50"},
        {"Paquete 2: Chequeo rapido de glucosa", "", "", "", "40"},
        {"Paquete 3: Chequeo Covid 19", "", "", "", "30"},
        {"Paquete 4: Chequeo de tiroide", "", "", "", "40"},
        {"Paquete 5: Chequeo de inmunidad", "", "", "", "54"}
    };
    private String[] package_details={
            "Blood Glucose Fasting\n" +
                    "Complete Hemograma\n"+
                    "HDAICVO\n" +
                    "Iron studiestn\n"+
                    "Kidney Function Testia\n"+
                    "LDH Lactate Dehydrogenasé\n"+
                    "Lipid ProfileWa\n"+
                    "Liver Function Test\n"+
                    "Blood Glucose Fasting\n"+
                    "COVID-19 Antibody - Tge\n"+
                    "Thyroid Profile-Total (13, T4 6 TSH ULtra-sensitive)\n"+
                    "Complete HemogramWn\n"+
                    "ERP (C Reactive Protein) Quantitative, Serumln\n"+
                    "Tron studies\n"+
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile"
    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, BtnBack;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart=findViewById(R.id.buttonButtonTGoCart);
        BtnBack=findViewById(R.id.buttonLTBack);
        listView=findViewById(R.id.listViewLT);

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });


    }
}
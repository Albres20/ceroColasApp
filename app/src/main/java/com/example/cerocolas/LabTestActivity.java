package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        btnGoToCart = findViewById(R.id.buttonBMCartCheckout);
        BtnBack = findViewById(R.id.buttonBMCartBack);
        listView = findViewById(R.id.listViewBMCart);

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });
        list = new ArrayList();
        System.out.println("longitud"+packages.length);
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Costo"+packages[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this, list,  R.layout.multi_lines, new String[]{"line1","line2" ,"line3" ,"line4" ,"line5" }, new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e} );

        listView.setAdapter(sa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_details[0]); ///colocar mas datos al array para otras especialidades
                it.putExtra("text3", packages[i][4]);
                startActivity(it);

            }
        });

       btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });

    }
}
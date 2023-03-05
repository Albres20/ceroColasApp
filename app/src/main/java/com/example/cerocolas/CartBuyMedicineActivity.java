package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckout, btnBack;
    private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);
        dateButton=findViewById(R.id.buttonBMCartDate);
        btnCheckout=findViewById(R.id.buttonBMCartCheckout);
        btnBack=findViewById(R.id.buttonBMCartBack);
        tvTotal=findViewById(R.id.textViewBMCartTotalCost);
        lst=findViewById(R.id.listViewBMCart);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String usernmame=sharedPreferences.getString("username", "").toString();
        database db= new database(getApplicationContext(), "ceroCdb", null, 1);

        float totalamount=0;
        ArrayList dbData=db.getCartData(usernmame, "lab");
        Toast.makeText(getApplicationContext(), ""+dbData, Toast.LENGTH_SHORT).show();

        packages=new String [dbData.size()][];
        for(int i=0; i<packages.length;i++){
            packages[i]=new String[5];
        }

        for(int i=0; i<dbData.size(); i++){
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("S/."));
            packages[i][0]=strData[0];
            packages[i][4]= "Costo: "+strData[1]+"/-";
            totalamount=totalamount+Float.parseFloat(strData[1]);
        }

        tvTotal.setText("Costo total: "+totalamount);

        list=new ArrayList();
        for(int i=0; i<packages.length; i++){
            item=new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);

        }

        sa=new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});


        lst.setAdapter(sa);





        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartLabActivity.this, LabTestActivity.class));

            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(CartLabActivity.this, LabTestBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date", dateButton.getText());
                it.putExtra("time", timeButton.getText());
                startActivity(it);


            }
        });


        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });
    }
}
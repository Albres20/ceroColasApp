package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnBack, btnAddtoCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvPackageName=findViewById(R.id.textViewBMDPackageName);
        edDetails=findViewById(R.id.editTextBMDMultilne);
        edDetails.setKeyListener(null);
        tvTotalCost=findViewById(R.id.textViewBMDTotalPrice);
        btnBack=findViewById(R.id.btnBack);
        btnAddtoCart=findViewById(R.id.buttonBMDAdCart);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Costo total: "+intent.getStringExtra("text3")+"/-");
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username", "").toString();
                String product= tvPackageName.getText().toString();
                float price= Float.parseFloat(intent.getStringExtra("text3").toString());
                //System.out.println("here");

                database db=new database(getApplicationContext(), "ceroCdb", null, 1);
                if(db.checkCart(username, product)==1){
                    Toast.makeText(getApplicationContext(), "Producto ya añadido", Toast.LENGTH_SHORT).show();

                }
                else{
                    db.addCart(username, product, price, "medicine");
                    Toast.makeText(getApplicationContext(), "Insertado al carrito", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));

                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

    }
}
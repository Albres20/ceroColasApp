package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {

    TextView tv;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        //Establecer titulo
        tv=findViewById(R.id.textDetallesDoctor);
        btn=findViewById(R.id.buttonBack);
        Intent it=getIntent();
        String title = it.getStringExtra("title");

        tv.setText(title);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
                    }
                }
        );
    }
}
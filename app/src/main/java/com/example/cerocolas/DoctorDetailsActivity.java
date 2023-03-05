package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1= {
            {"Médico: Pablo David", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "1000"},
            {"Médico: Ventura Moises", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "600"},
            {"Médico: Gonkales Daniel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "100"},
            {"Médico: Gil Orlando", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "520"},
            {"Médico: Gaviria Angel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "860"}

    };
    private String[][] doctor_details2= {
            {"Médico: Pablo David", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "1000"},
            {"Médico: Ventura Moises", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "600"},
            {"Médico: Gonkales Daniel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "100"},
            {"Médico: Gil Orlando", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "520"},
            {"Médico: Gaviria Angel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "860"}

    };
    private String[][] doctor_details3= {
            {"Médico: Pablo David", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "1000"},
            {"Médico: Ventura Moises", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "600"},
            {"Médico: Gonkales Daniel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "100"},
            {"Médico: Gil Orlando", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "520"},
            {"Médico: Gaviria Angel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "860"}

    };
    private String[][] doctor_details4= {
            {"Médico: Pablo David", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "1000"},
            {"Médico: Ventura Moises", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "600"},
            {"Médico: Gonkales Daniel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "100"},
            {"Médico: Gil Orlando", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "520"},
            {"Médico: Gaviria Angel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "860"}

    };
    private String[][] doctor_details5= {
            {"Médico: Pablo David", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "1000"},
            {"Médico: Ventura Moises", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "600"},
            {"Médico: Gonkales Daniel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "100"},
            {"Médico: Gil Orlando", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "520"},
            {"Médico: Gaviria Angel", "Dirección: Ventanilla01", "Exp: 15yrs", "Celular:987330113", "860"}

    };

    TextView tv;
    Button btn;

    String[][] doctor_details = {};

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        //Establecer titulo
        tv=findViewById(R.id.textDetallesDoctor);
        btn=findViewById(R.id.buttonBMCartBack);
        Intent it=getIntent();
        String title = it.getStringExtra("title");

        tv.setText(title);

        if(title.compareTo("Médico de familia")==0)
            doctor_details=doctor_details1;

        else
        if(title.compareTo("Experto en Dietas")==0)
            doctor_details=doctor_details2;

        else
        if(title.compareTo("Dentista")==0)
            doctor_details=doctor_details3;

        else
        if(title.compareTo("Cirujano")==0)
            doctor_details=doctor_details4;

        else
        if(title.compareTo("Cardiólogo")==0)
            doctor_details=doctor_details5;








        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
                    }
                }
        );
        list=new ArrayList();
        for(int i=0; i<doctor_details.length; i++){
            item=new HashMap<String, String>();

            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this, list,  R.layout.multi_lines, new String[]{"line1","line2" ,"line3" ,"line4" ,"line5" }, new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e} );
        ListView lst= findViewById(R.id.listViewBMCart);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //recibir
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE); //obtiene un objeto SharedPreferences llamado "shared_prefs". Este objeto permite a la aplicación guardar y recuperar información de configuración que persiste a través de sesiones.
                String usuario=sharedPreferences.getString("username", "").toString(); //obtiene un valor de la clave "username" almacenada en sharedPreferences y lo almacena en una variable llamada username. Si no se encuentra un valor para la clave "username", se devuelve una cadena vacía.
                //enviar

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username", usuario);
                editor.apply();
                System.out.println("-->"+usuario+"<--");


                Intent intent= new Intent(DoctorDetailsActivity.this, BoockAppointmentActivity.class);
                intent.putExtra("text1", title);
                intent.putExtra("text2", doctor_details[i][0]);
                intent.putExtra("text3", doctor_details[i][1]);
                intent.putExtra("text4", doctor_details[i][3]);
                intent.putExtra("text5", doctor_details[i][4]);
               startActivity(intent);

            }
        });
    }
}


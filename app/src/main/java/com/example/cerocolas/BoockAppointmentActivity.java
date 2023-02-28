package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BoockAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;

    int hrs, mins;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boock_appointment);

        //tv=findViewById(R.id.titleFindDoctor);
        ed1=findViewById(R.id.editTextAppFullName);
        ed2=findViewById(R.id.editTextAppAddress);
        ed3=findViewById(R.id.editTextAppContactNumber);
        ed4=findViewById(R.id.editTextAppFees);

        //Evitar ediciones
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        //botones
        dateButton=findViewById(R.id.buttonAppDate);
        timeButton=findViewById(R.id.buttonAppTime);
        btnBook=findViewById(R.id.buttonBookAppoinment);
        btnBack=findViewById(R.id.btnBack);


        Intent it=getIntent();
        String title= it.getStringExtra("text1");
        String fullname= it.getStringExtra("text2");
        String address= it.getStringExtra("text3");
        String contact= it.getStringExtra("text4");
        String fees= it.getStringExtra("text5");

        //tv.setText(tile);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Tarifa:"+fees+"/-");

        //datepicker
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
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String medico=ed1.getText().toString().substring(8);
                String direccion=ed2.getText().toString().substring(11);
                String numero=ed3.getText().toString().substring(8);

                //recibidor del parametro usuario
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE); //obtiene un objeto SharedPreferences llamado "shared_prefs". Este objeto permite a la aplicación guardar y recuperar información de configuración que persiste a través de sesiones.
                String username=sharedPreferences.getString("username", "").toString(); //obtiene un valor de la clave "username" almacenada en sharedPreferences y lo almacena en una variable llamada username. Si no se encuentra un valor para la clave "username", se devuelve una cadena vacía.


                int celular=Integer.valueOf(numero);
                String tarifa=ed4.getText().toString().substring(8);
                System.out.println("-->"+medico+"-->"+direccion+"-->"+numero+"-->"+ed4.getText()+"+"+datePickerDialog.getDatePicker().toString());
                database db=new database (getApplicationContext(), "ceroCdb", null, 1 );

                //Consultar id de base de datos
                int idUser=db.returnIdUser(username);
                //calendario a string
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePickerDialog.getDatePicker().getYear(), datePickerDialog.getDatePicker().getMonth(), datePickerDialog.getDatePicker().getDayOfMonth());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fechaString = sdf.format(calendar.getTime());
                // tiempo a string
                String hora=String.valueOf(hrs)+":"+String.valueOf(mins);



                //calendar.set(Calendar.HOUR_OF_DAY, timePickerDialog.getTimePicker().getCurrentHour());
                //calendar.set(Calendar.MINUTE, timePickerDialog.getTimePicker().getCurrentMinute());
                System.out.println("-->fecha: "+fechaString);
                System.out.println("hora"+"-->"+hora);
                db.registerCita(idUser, medico, direccion, celular, tarifa, "", "");
                Toast.makeText(getApplicationContext(), "Registro Correcto.", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoockAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoockAppointmentActivity.this, HomeActivity.class));
            }
        });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1++;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month= cal.get(Calendar.MONTH);
        int day= cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialog=new DatePickerDialog(this,style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                i1++;
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal= Calendar.getInstance();
        hrs = cal.get(Calendar.HOUR);
        mins= cal.get(Calendar.MINUTE);


        int style= AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        timePickerDialog=new TimePickerDialog(this,style, timeSetListener, hrs, mins, true);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

}
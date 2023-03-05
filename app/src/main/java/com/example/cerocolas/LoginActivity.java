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

public class LoginActivity extends AppCompatActivity {

    EditText edUsuario, edContraseña;
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsuario=findViewById(R.id.editTextLBMBullname);
        edContraseña=findViewById(R.id.editTextBMBPincode);
        btn=findViewById(R.id.buttonBMBBooking);
        textView=findViewById(R.id.textView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario=edUsuario.getText().toString();
                String contraseña=edContraseña.getText().toString();
                boolean pass=validarUsuario(usuario, contraseña);
                database db=new database (getApplicationContext(), "ceroCdb", null, 1 );
                if(pass){
                    if(db.loginIn(usuario, contraseña)==1){
                        Toast.makeText(getApplicationContext(), "Acceso satisfactorio...", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("username", usuario);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña invalido.", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    System.out.println("no ingresó...");
                }
            }

        });
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
    public boolean validarUsuario(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Error: nombre de usuario o contraseña vacíos.");
            Toast.makeText(getApplicationContext(), "Usuario o contraseña vacía.", Toast.LENGTH_SHORT).show();

            return false;
        }
        if (username.length() < 8 || password.length() < 8) {
            System.out.println("Error: nombre de usuario o contraseña debe tener al menos 8 caracteres.");
            Toast.makeText(getApplicationContext(), "Usuario o contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }
}
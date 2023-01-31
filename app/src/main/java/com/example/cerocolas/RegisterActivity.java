package com.example.cerocolas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView ReturnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername=findViewById(R.id.editTextUsuario);
        edEmail=findViewById(R.id.editTextCorreo);
        edPassword=findViewById(R.id.editTextContraseña);
        edConfirm=findViewById(R.id.editTextConfirmarContraseña);
        btn=findViewById(R.id.button);
        ReturnLogin=findViewById(R.id.ReturnLogin);

        ReturnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario=edUsername.getText().toString();
                String email=edEmail.getText().toString();
                String contraseña=edPassword.getText().toString();
                String confirmarContraseña=edConfirm.getText().toString();
                validarRegistro(usuario, email, contraseña, confirmarContraseña);
            }
        });
    }
    public boolean validarRegistro(String username, String email, String password, String confirmarPassword) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            System.out.println("Error: nombre de usuario o contraseña vacíos.");
            Toast.makeText(getApplicationContext(), "Complete todos los campos.", Toast.LENGTH_SHORT).show();

            return false;
        }
        else{
            if (username.length() < 8 || email.length() < 8 || password.length() < 8 || confirmarPassword.length() < 8) {
                System.out.println("Error: nombre de usuario o contraseña debe tener al menos 8 caracteres.");
                Toast.makeText(getApplicationContext(), "Los campos deben ser de 8 caracteres a más.", Toast.LENGTH_SHORT).show();

                return false;
            }
            else{
                if(password.compareTo(confirmarPassword)==0){
                    System.out.println("all good-delete");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Contraseña y confirmar contraseña no coinciden.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return true;
    }
}

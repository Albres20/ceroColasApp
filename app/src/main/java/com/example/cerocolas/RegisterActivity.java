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
                Boolean flag= validarRegistro(usuario, email, contraseña, confirmarContraseña);
                database db=new database (getApplicationContext(), "healthcare", null, 1 );
                if(flag){
                    db.register(usuario, email, contraseña);
                    Toast.makeText(getApplicationContext(), "Registro Correcto.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "No se creo en base de datos.", Toast.LENGTH_SHORT).show();
                }
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


                    if(isValid(password) && isValid(confirmarPassword)){


                        if(password.compareTo(confirmarPassword)==0){

                            Toast.makeText(getApplicationContext(), "Ingresa", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No coincide contraseña y confirmar contraseña@", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "La contraseña debe incluir caracteres especiales, letras y dígitos.", Toast.LENGTH_SHORT).show();
                        return false;
                    }



            }
        }

        return true;
    }
    public static boolean isValid(String passwordValidate){
        int f1=0, f2=0, f3=0;


            for(int p=0; p<passwordValidate.length();p++){
                if(Character.isLetter(passwordValidate.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0; r<passwordValidate.length();r++){
                if(Character.isDigit(passwordValidate.charAt(r))){
                    f2=1;
                }

            }for(int s=0; s<passwordValidate.length();s++){
                char c=passwordValidate.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f3=1;

                }

            }
            if(f1==1 && f2==1 && f3==1){

                return true;
            }
            return false;

    }
}

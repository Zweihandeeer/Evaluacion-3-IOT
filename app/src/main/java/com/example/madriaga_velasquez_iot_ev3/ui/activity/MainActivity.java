package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login, registrar, forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();

        Globals.instance().createDb(this);
    }

    //region eventos y referencias
    private void eventos(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = Globals.instance().getDb().checkusernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(MainActivity.this, "Login exitoso !", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), menu.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Credenciales inválidas.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registrousuario.class);
                startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgot = new Intent(MainActivity.this, recuperarpass.class);
                startActivity(forgot);
            }
        });
    }

    private void referencias(){
        username = (EditText) findViewById(R.id.etUsuario);
        password = (EditText) findViewById(R.id.etContrasena);
        login = (Button) findViewById(R.id.btnIngresar);
        registrar = (Button) findViewById(R.id.btnregistro);
        forgot = (Button) findViewById(R.id.btnForgot);
    }
    //endregion
}
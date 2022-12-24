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
import com.example.madriaga_velasquez_iot_ev3.domain.model.User;
import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;

import java.util.ArrayList;

public class registrousuario extends AppCompatActivity {
    EditText username, password, repassword, preguntasecreta, respuesta;
    Button signup, volverregistro;
    private final DBhelper DB = Globals.instance().getDb(); // Debe ser final pq no es mutable.

    //Eventos
    private ArrayList<User> losUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    //region eventos y referencias
    private void eventos() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String question = preguntasecreta.getText().toString();
                String answer = respuesta.getText().toString();

                if (name.equals("") || pass.equals("") || repass.equals("") || question.equals("") || answer.equals("")) {
                    showToast("Por favor ingrese todos los campos.");
                    return;
                }
                if(!pass.equals(repass)){
                    showToast("Las contraseñas no coinciden.");
                    return;
                }

                Boolean userExists = DB.checkusername(name);

                if(userExists){
                    showToast("El usuario ingresado ya existe, haga login !");
                    return;
                }

                User user = new User(name, pass, question, answer);

                Boolean insert = DB.insertData(user);

                if (insert) {
                    showToast("Registro exitoso !");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    showToast("Registro fállido.");
                }
            }
        });
        volverregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void showToast(String text){
        Toast.makeText(registrousuario.this, text, Toast.LENGTH_SHORT).show();
    }

    private void referencias() {
        username = (EditText) findViewById(R.id.etRegisterName);
        password = (EditText) findViewById(R.id.etRegisterPassword);
        repassword = (EditText) findViewById(R.id.etRegisterPasswordConfirm);
        preguntasecreta = (EditText) findViewById(R.id.etRegisterSecretQuestion);
        respuesta = (EditText) findViewById(R.id.etRegisterSecretAnswer);
        signup = (Button) findViewById(R.id.btnRegisterSignup);
        volverregistro = (Button) findViewById(R.id.btnRegisterBack);
    }
    //endregion
}
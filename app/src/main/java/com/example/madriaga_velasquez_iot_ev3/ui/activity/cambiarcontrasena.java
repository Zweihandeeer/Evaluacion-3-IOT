package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import static com.example.madriaga_velasquez_iot_ev3.R.id.btnChangePassBack;
import static com.example.madriaga_velasquez_iot_ev3.R.id.btnChangePassSave;
import static com.example.madriaga_velasquez_iot_ev3.R.id.etChangePassNewPass;
import static com.example.madriaga_velasquez_iot_ev3.R.id.etChangePassOldPass;
import static com.example.madriaga_velasquez_iot_ev3.R.id.etChangePassUsername;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;
import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;

public class cambiarcontrasena extends AppCompatActivity {
    private EditText usuario, oldpassword, newpassword;
    private Button guardar, volver;
    DBhelper DB = Globals.instance().getDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiarcontrasena);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    private void referencias() {
        usuario = findViewById(etChangePassUsername);
        oldpassword = findViewById(etChangePassOldPass);
        newpassword = findViewById(etChangePassNewPass);
        guardar = findViewById(btnChangePassSave);
        volver = findViewById(btnChangePassBack);
    }

    private void toast(String text) {
        Toast.makeText(cambiarcontrasena.this, text, Toast.LENGTH_SHORT).show();
    }

    private void eventos() {
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usuario.getText().toString();
                String oldpass = oldpassword.getText().toString();
                String newpass = newpassword.getText().toString();

                if (user.isEmpty() || oldpass.isEmpty() || newpass.isEmpty()) {
                    toast("Por favor ingrese todos los campos.");
                    return;
                }

                Boolean userExists = DB.checkusername(user);

                // Obtener el usuario y cambiar la clave
                /*
                if (!oldpass.equals(newpass)) { // Cambiar esta linea
                    toast("La contraseña del usuario indicado no coincide.");
                    return;
                }
                 */

                if (userExists) {
                    Boolean passwordUpdated = DB.updatepassworduser(user, newpass);
                    if (passwordUpdated) {
                        toast("Cambio de contraseña exitoso !");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        finish();
                    } else {
                        toast("No se pudo completar el cambio de contraseña.");
                    }
                }
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
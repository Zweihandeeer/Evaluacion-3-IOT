package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;

public class recuperarpass extends AppCompatActivity {
    private EditText confirmaruser;
    private Button validateuser, returnvalidate;
    private final DBhelper DB = Globals.instance().getDb(); // Debe ser final pq no es mutable.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarpass);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    private void eventos() {
        validateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String retrieveuser = confirmaruser.getText().toString();

                Boolean userExists = DB.checkusername(retrieveuser);

                if(userExists){
                    showToast("Usuario confirmado");
                    Intent intent = new Intent(recuperarpass.this, cambiarcontrasena.class);
                    startActivity(intent);
                } else {
                    showToast("El usuario indicado no existe.");
                }
            }
        });
        returnvalidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void showToast(String text){
        Toast.makeText(recuperarpass.this, text, Toast.LENGTH_SHORT).show();
    }

    private void referencias(){
        confirmaruser = findViewById(R.id.etForgotUser);
        validateuser = findViewById(R.id.btnRetrieveUser);
        returnvalidate = findViewById(R.id.btnRetrieveBack);
    }


}
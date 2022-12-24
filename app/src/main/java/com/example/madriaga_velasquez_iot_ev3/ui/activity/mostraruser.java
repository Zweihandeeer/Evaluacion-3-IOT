package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.domain.model.User;

public class mostraruser extends AppCompatActivity {
    private TextView usuario, contrasena, preguntasecreta, respuesta;
    private Button btnAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrareventos);

        referencias();
        eventos();
    }

    private void referencias(){
        usuario = findViewById(R.id.tvUsuariom);
        contrasena = findViewById(R.id.tvContrasenam);
        preguntasecreta = findViewById(R.id.tvPreguntaSecretam);
        respuesta = findViewById(R.id.tvRespuestam);
        btnAtras = findViewById(R.id.btnAtras);

        User user = (User) getIntent().getExtras().getSerializable("datosUser");

        usuario.setText(user.getPass());
        contrasena.setText(user.getRespuestasecreta());
        preguntasecreta.setText(String.valueOf(user.getPreguntasecreta()));
        respuesta.setText(user.getRespuestasecreta());

    }

    private void eventos(){

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { volver(); }
        });
    }
    //endregion
    private void volver(){
        finish();
    }
}
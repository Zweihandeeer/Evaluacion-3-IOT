package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;

public class menu extends AppCompatActivity {
    private Button cambiarpass, eliminaracc, agregarevento, mostrareventos, volver;
    private final DBhelper DB = Globals.instance().getDb(); // Debe ser final pq no es mutable.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    private void eventos(){
        cambiarpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, cambiarcontrasena.class);
                startActivity(intent);
            }
        });
        eliminaracc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, eliminarusuario.class);
                startActivity(intent);
            }
        });
        agregarevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, agregareventos.class);
                startActivity(intent);
            }
        });
        mostrareventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, mostrareventos.class);
                startActivity(intent);
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Método lambda: btnbus.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SegundaActividad.class)));
    }
    //endregion

    private void referencias(){
        cambiarpass = (Button) findViewById(R.id.btnCambiarPass);
        eliminaracc = (Button) findViewById(R.id.btnEliminarAcc);
        agregarevento = (Button) findViewById(R.id.btnAgregarEvento);
        mostrareventos = (Button) findViewById(R.id.btnMostrarEventos);
        volver = (Button) findViewById(R.id.btnVolver);
    }
    //endregion
}
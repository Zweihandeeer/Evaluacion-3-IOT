package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;

public class eliminarusuario extends AppCompatActivity {
    private EditText usernameverify;
    private Button confirmarborraruser, volvereliminar;
    private final DBhelper DB = Globals.instance().getDb(); // Debe ser final pq no es mutable.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarusuario);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    private void eventos() {
        // Nota: Usa lambda expressions, simplifica el código
        confirmarborraruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear método para buscar y eliminar.
                String userName;

                userName = usernameverify.getText().toString();

                DB.deleteuser(userName);

                AlertDialog.Builder alerta = new AlertDialog.Builder(eliminarusuario.this);
                alerta.setMessage("¿Está seguro que desea eliminar su cuenta?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                                Intent intent = new Intent(eliminarusuario.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Eliminar");
                titulo.show();
            }
        });
        volvereliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void referencias() {
        usernameverify = findViewById(R.id.etUsuarioConfirmar);
        confirmarborraruser = findViewById(R.id.btnConfirmarEliminar);
        volvereliminar = findViewById(R.id.btnVolverEliminar);
    }
}
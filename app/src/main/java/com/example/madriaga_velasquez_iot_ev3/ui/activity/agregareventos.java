package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;
import com.example.madriaga_velasquez_iot_ev3.domain.model.Evento;
import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.domain.model.User;

import java.util.ArrayList;

public class agregareventos extends AppCompatActivity {
    private TextView tvEvento;
    private Spinner importancia;
    private EditText titulo, lugar, observaciones, fecha, aviso;
    private Button btnGuardar, back;
    private final DBhelper DB = Globals.instance().getDb(); // Debe ser final pq no es mutable.

    //Eventos
    ArrayList<Evento> losEventos;
    ArrayAdapter<String> adapterImportancia;

    //Spinner
    String[] nivel = {"Baja","Media", "Alta", "Muy alta"};

/*    private int indiceActual = 0;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_eventos);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    private void referencias(){
        titulo = findViewById(R.id.etAddTitle);
        lugar = findViewById(R.id.etAddPlace);
        observaciones = findViewById(R.id.etAddObservations);
        fecha = findViewById(R.id.etAddDate);
        aviso = findViewById(R.id.etAddNotification);
        importancia = findViewById(R.id.spnAddImportance);
        btnGuardar = findViewById(R.id.btnAddSave);
        back = findViewById(R.id.btnAddBack);

        adapterImportancia = new ArrayAdapter<String>(agregareventos.this, android.R.layout.simple_spinner_dropdown_item, nivel);
        importancia.setAdapter(adapterImportancia);
    }

    //region eventos y referencias
    private void eventos() {
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloinput = titulo.getText().toString();
                String lugarinput = lugar.getText().toString();
                String observacionesinput = observaciones.getText().toString();
                String fechainput = fecha.getText().toString();
                String avisoinput = aviso.getText().toString();
                String importanciaspinner = importancia.getSelectedItem().toString();

                if (tituloinput.isEmpty() || lugarinput.isEmpty() || observacionesinput.isEmpty() || fechainput.isEmpty() || avisoinput.isEmpty() || importanciaspinner.isEmpty()) {
                    showToast("Por favor ingrese todos los campos.");
                    return;
                }

                Evento evento = new Evento(tituloinput, lugarinput, observacionesinput, fechainput, avisoinput, importanciaspinner);

                Boolean insert = DB.insertDataEventos(evento);

                if (insert) {
                    showToast("Evento agregado exitosamente !");
                    Intent intent = new Intent(getApplicationContext(), menu.class);
                    startActivity(intent);
                } else {
                    showToast("No se pudo agregar el evento, intente nuevamente.");
                }
            }
        });
        importancia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(agregareventos.this, "Ha saleccionado " + parent.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void showToast(String text){
        Toast.makeText(agregareventos.this, text, Toast.LENGTH_SHORT).show();
    }

    //endregion
}

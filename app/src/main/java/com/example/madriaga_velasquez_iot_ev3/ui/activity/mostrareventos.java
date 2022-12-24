package com.example.madriaga_velasquez_iot_ev3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madriaga_velasquez_iot_ev3.R;
import com.example.madriaga_velasquez_iot_ev3.data.database.DBhelper;
import com.example.madriaga_velasquez_iot_ev3.domain.di.Globals;
import com.example.madriaga_velasquez_iot_ev3.domain.model.Evento;

import java.util.ArrayList;

public class mostrareventos extends AppCompatActivity {
    private TextView tvTituloAgregar;
    private ListView eventosagregados;
    private Button volvermenu;
    private final DBhelper DB = Globals.instance().getDb(); // Debe ser final pq no es mutable.

    ArrayList<String> lista;
    ArrayList<Evento> listaeventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrareventos);
        getSupportActionBar().setTitle("Sistema ingreso de eventos");

        referencias();
        eventos();
    }

    private void eventos() {
/*        eventosagregados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "Titulo: " + listaeventos.get(pos).getTitulo() + "\n";
                informacion += "Lugar: " + listaeventos.get(pos).getLugar() + "\n";
                informacion += "Fecha: " + listaeventos.get(pos).getFecha() + "\n";
                informacion += "Aviso: " + listaeventos.get(pos).getAviso() + "\n";
                informacion += "Observacion: " + listaeventos.get(pos).getObservacion() + "\n";
                informacion += "Importancia: " + listaeventos.get(pos).getImportancia() + "\n";

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();
            }
        });*/
        volvermenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void referencias() {
        eventosagregados = (ListView) findViewById(R.id.lvEventoAgr);
        volvermenu = (Button) findViewById(R.id.btnVolmenu);

        //obtenemos todos lo datos registrados de la base de datos.
        ArrayList array_list = DB.getAllRegistros();
        /*creamos un arreglo de string e instanciamos la forma de lista*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array_list);
        /*a listview los pasamos el arreglo de string instanciado*/
        eventosagregados.setAdapter(arrayAdapter);
    }


}
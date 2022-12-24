package com.example.madriaga_velasquez_iot_ev3.data.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.madriaga_velasquez_iot_ev3.domain.model.Evento;
import com.example.madriaga_velasquez_iot_ev3.domain.model.User;

import java.util.ArrayList;

@SuppressLint("Recycle")
public class DBhelper extends SQLiteOpenHelper implements DBHelperInterface {

    public DBhelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.i("DB", "Creando DB");
    }

    // Creación de tablas al iniciar aplicación.
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        Log.i("DB", "Creando tablas");
        MyDB.execSQL("create Table users(username text primary key, password text, preguntasecreta text, respuesta text)");
        MyDB.execSQL("create Table eventos(titulo text primary key, lugar text, observacion text, fecha text, aviso text, importancia text)");
    }

    // Método para dropear tablas en caso de cambios a los esquemas. Se llama cuando una fila ya existe.
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        Log.i("DB", "Actualizando DB");
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists eventos");
        onCreate(MyDB);
    }

    // Insertar datos de usuarios registrados.
    @Override
    public Boolean insertData(User user) {
        Log.i("DB", "Insertando data");
        SQLiteDatabase MyDB = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("username", user.getName());
        contentValues.put("password", user.getPass());
        contentValues.put("preguntasecreta", user.getPreguntasecreta());
        contentValues.put("respuesta", user.getRespuestasecreta());

        return MyDB.insert("users", null, contentValues) != -1;
    }

    // Insertar datos de eventos.
    @Override
    public Boolean insertDataEventos(Evento evento) {
        Log.i("DB", "Insertando data");
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("titulo", evento.getTitulo());
        contentValues.put("lugar", evento.getLugar());
        contentValues.put("fecha", evento.getFecha());
        contentValues.put("aviso", evento.getAviso());
        contentValues.put("observacion", evento.getObservacion());
        contentValues.put("importancia", evento.getImportancia());

        return MyDB.insert("eventos", null, contentValues) != -1;
    }

    // Método para checkear la existencia de un usuario en la base de datos.
    @Override
    public Boolean checkusername(String username) {
        Log.i("DB username", username);
        SQLiteDatabase MyDB = getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    // Método para checkear la existencia de la contraseña de un usuario X existe en la base de datos.
    @Override
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    // Método para eliminar un usuario de la base de datos.
    @Override
    public Boolean deleteuser(String username) {
        Log.i("DB", "Eliminando usuario");
        SQLiteDatabase MyDB = getWritableDatabase();
        return MyDB.delete("users", "username=?", new String[]{username}) != -1;
    }

    // Método para actualizar una contraseña de un usuario de la base de datos.
    @Override
    public Boolean updatepassworduser(String username, String newPassword) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);
        return MyDB.update("users", cv, "username=?", new String[]{username}) != -1;
    }

    @Override
    public ArrayList<String> getAllRegistros()
    {
        /*creamos un arreglo de  string */
        ArrayList<String> lista = new ArrayList<String>();
        /*creamos un objeto de la base de datos y lo inicializamos
         con el metodo que abre la base de datos para lectura */
        SQLiteDatabase db = this.getReadableDatabase();
        /*creamos un objeto de cursor y lo pasamos la query del select*/
        Cursor res =  db.rawQuery( "select * from eventos", null );
        /*movemos el cursor a la primera fila*/
        res.moveToFirst();
        /*hacemos un bucle while para la busqueda de datos */
        while(res.isAfterLast() == false){
            /*miestras encuentre datos que capture id es el 0 curso es 1 */
            lista.add("Titulo: " + res.getString(0)
                    +" | "+"Lugar: " +" "+ res.getString(1)
                    + " | "+ " Fecha: " + res.getString(2)
                    +" | "+"Aviso: " +" "+ res.getString(3)
                    + " | "+ " Observacion: " +" "+ res.getString(4)
                    + " | "+ " Importancia: " + res.getString(5));
            /*movemos el cursor a la siguiente linea*/
            res.moveToNext();}
        /*retornamos el arreglo de string*/
        return lista;
    }
}
